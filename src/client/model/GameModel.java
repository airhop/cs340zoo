/**
 * Bank - needs an add ResourceList for robberdiscard
 *      - need an empty constructor
 *      - get a lazy canGive(enumtype), return type.  Same with addtype
 *      - given a tradeOffer can we trade?
 *
 * TradeOffer - can you seperate the TradeOffer into 2 resourcelists?  1 positive and 1 negative.
 *
 * Player - need a constructor with only a name and an id, all items
 *        - canrobberdiscard - player method discard cards and return the resourcelist of discarded cards
 *        - canPlaceMonument - if VP == 10, return true, else false
 *        trading?
`*            - tradePlayer = return a TradeOffer to set up tradeoffer
 *            - need 2 acceptTrade methods.  1 for reciever and 1 for sender.
 *        - canTradeBank() will be its job to make sure the port is correct as well
 *
 * Roll = resourceList/player what are we returning and passing around?
 *      - could return an array of TradeOffers and that can be passed to players
 *
 * canDiscardCards - what is the point?  Can discard a list of cards, of has a hand that can discard cards?  etc.
 * canSendChat - what would be stopping you?
 *
 * Robber - CanMoveRobber calls canRelocateRobber, move it to a valid hex location (can't be4 where it was and can't be an ocean tile
 *
 * Monopoly - send a player a resource type, return the number they have (delete those resources)
 *              send a player a resource type and an int, increase the resource by that
 * RoadBuilding - canRB - make sure there are 2 roads to be played and the card
 *
 */


package client.model;

import client.model.bank.Bank;
import client.model.bank.ResourceList;
import client.model.map.*;
import client.model.misc.*;
import client.model.player.Player;
import org.omg.CORBA.DynAnyPackage.Invalid;
import client.model.history.*;
import shared.exceptions.*;
import shared.definitions.*;

public class GameModel {
    private Map map;
    Bank bank;
    Player[] players;
    TurnTracker tt;
    TradeOffer to;
    int version = 0;
    int winner = -1;
    Dice dice;
    Chat chat;
    Log log;


    public GameModel(String[] names) {
        map = new Map();
        bank = new Bank();
        tt = new TurnTracker();
        to = new TradeOffer();
        dice = new Dice();
        players = new Player[4];
        for (int i = 0; i < names.length; i++)
            players[i] = new Player(names[i], i);
    }

    public void reinitialize(GameModel game) {
        map = game.getMap();
        tt = game.getTurnTracker();
        to = game.getTradeOffer();
        version = game.getVersion();
        winner = game.getWinner();
        players = game.getPlayers();
        bank = game.getBank();
//        dice = new Dice();
    }

    public Map getMap() {
        return map;
    }

    public TurnTracker getTurnTracker() {
        return tt;
    }

    public TradeOffer getTradeOffer() {
        return to;
    }

    public int getVersion() {
        return version;
    }

    public int getWinner() {
        return winner;
    }

    public Bank getBank() {
        return bank;
    }

    public Player[] getPlayers() {
        return players;
    }

    /**
     * updates version of the game model
     */
    public void updateVersion() {
        version++;
    }


    /**
     * Checks to see if the player can win the game
     *
     * @return boolean whether or not the player can win (have 10 victory points)
     */
    public boolean canWin() {
        //need to check if it is finish turn?
        int cp = tt.getCurrentPlayer();
        if (players[cp].canWin())
            return true;
        return false;
    }

    /**
     * decides game winner and sets the winner to the id of the winning player
     */
    public void decideWinner() throws InvalidWinnerException {
        int cp = tt.getCurrentPlayer();
        if (players[cp].canWin())
            winner = cp;
        if (winner != -1)
            players[winner].win();
        else
            throw new InvalidWinnerException("No winner yet!");

    }

    /**
     * This is used to see when the robber moves if the player can discard
     *
     * @return
     */
    public void RobberDiscard() {
        for (int i = 0; i < players.length; i++)
            bank.add(players[i].canRobberDiscard());
    }

    /**
     * Places a the robber at a specific location on the map
     *
     * @return boolean whether or not the player moved the robber
     */
    public void moveRobber(HexLocation hl) {
        map.placeRobber(hl);
    }

    /**
     * Checks to see if moving the robber is a legal move for the player
     *
     * @return boolean whether or not the player can move the robber
     */
    public boolean canMoveRobber(HexLocation hl) {
        return map.canRelocateRobber(hl);
    }


    /**
     * Checks to see if building a road is a legal move for the player
     *
     * @return boolean whether or not the player can build a road
     */
    public boolean canBuildRoad() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canBuildRoad();
    }

    /**
     * Checks to see if placing a road is a legal move for the player
     *
     * @return boolean whether or not the player can place a road
     */
    public boolean canPlaceRoad() {
        return map.canAddRoad();
    }

    /**
     * Places a Road at a given location on the map
     *
     * @return boolean whether or not the player built the road (perhaps placeholder return values for all of the do methods)
     */
    public void placeRoad(EdgeLocation el) throws InvalidPositionException {
        int cp = tt.getCurrentPlayer();
        if (players[cp].canBuildRoad() && map.canAddRoad(el))
            map.addRoad(el, cp);
        else
            throw new InvalidPositionException("Error Building a Road");
    }

    /**
     * Checks to see if building a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can build a settlement
     */
    public boolean canBuildSettlement() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canBuildSettlement();
    }

    /**
     * Checks to see if placing a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can place a settlement
     */
    public boolean canPlaceSettlement(EdgeLocation el) {
        return map.canAddSettlement(el, tt.getCurrentPlayer());
    }

    /**
     * Places a Settlement at a given location on the map
     *
     * @return boolean whether or not the player placed a settlement
     */
    public void placeSettlement(EdgeLocation el) throws InvalidPositionException {
        int cp = tt.getCurrentPlayer();
        if (players[cp].canBuildSettlement() && map.canAddSettlement(el))
            map.addSettlement(el, cp);
        else
            throw new InvalidPositionException("Error Building a Settlement");
    }

    /**
     * Checks to see if building a city is a legal move for the player
     *
     * @return boolean whether or not the player can build a city
     */
    public boolean canBuildCity() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canBuildCity();
    }

    /**
     * Checks to see if placing a city is a legal move for the player
     *
     * @return boolean whether or not the player can place a city
     */
    public boolean canPlaceCity() {
        return map.canAddCity();
    }

    /**
     * Places a City at a given location on the map
     *
     * @return boolean whether or not the player placed the city
     */
    public void placeCity(EdgeLocation el) throws InvalidPositionException {
        int cp = tt.getCurrentPlayer();
        if (players[cp].canBuildCity() && map.canAddCity(el))
            map.addCity(el, cp);
        else
            throw new InvalidPositionException("Error building a City");
    }

    /**
     * Checks to see if buying a Developement Card is a legal move for the player
     *
     * @return boolean whether or not the player can buy a Developement card
     */
    public boolean canBuyDevcard() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canBuyDevcard();
    }

    /**
     * Checks to see if playing a Developement Card is a legal move for the player
     *
     * @return boolean whether or not the player can play a Developement card
     */
    public boolean canPlayDevcard() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canPlayDevcard();
    }

    /**
     * Checks to see if Montoply is a legal move for the player
     *
     * @return boolean whether or not the player can monopoly
     */
    public boolean canMonopoly() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canMonopoly();
    }

    /**
     * Checks to see if building a road in a specific place is a legal move for the player
     *
     * @return boolean whether or not the player can road building
     */
    public boolean canRoadBuilding() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canRoadBuilding();
    }

    /**
     * Checks to see if placing a Monument Card(?) is a legal move for the player
     *
     * @return boolean whether or not the player can place a monument
     */
    public boolean canUseMonument() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canPlaceMonument();
    }

    /**
     * Checks to see if placing a Year Of Plenty card is a legal move for the player
     *
     * @return boolean whether or not the player can play the Year of Plenty card
     */
    public boolean canYearOfPlenty() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canYearOfPlenty();
    }

    /**
     * plays the year of plenty card for a given player
     *
     * @return boolean whether or not the player played the year of plenty card
     */
    public void playYearOfPlenty(ResourceType r) throws IllegalMoveException, InsufficientResourcesException {
        if (!bank.canGive(r, 2))
            throw new InsufficientResourcesException("Bank doesn't have enough resources");
        bank.add(r, 2);
        int cp = tt.getCurrentPlayer();
        players[cp].playYearOfPlenty();
        players[cp].add(r, 2);
    }

    /**
     * Checks to see if placing a Soldier card is a legal move for the player
     *
     * @return boolean whether or not the player can place the Soldier card
     */
    public boolean canPlaceSoldier() {
        int cp = tt.getCurrentPlayer();
        return players[cp].canPlaceSoldier();
    }

    public boolean canFinishTurn() {
        return (tt.getStatus() == 3);
    }

    public void FinishTurn()
    {   tt.updateStatus();   }


    public boolean canDiscardCards()
    {
        return players[tt.getCurrentPlayer()].canDiscardCards();
    }
    /**
     * Checks to see if robbing another player is a legal move for the player
     * @return boolean whether or not the player can rob another player
     */
    public int[] canRob()
    {
        //find who can be robbed
        int[] ids = new int[3];
        int currId = 0;
        for(int i = 0; i < players.length; i++)
            if(i != tt.getCurrentPlayer() && players[i].canRob())
                ids[currId++] = i;
        if(currId == 3)
            return ids;

        //if not all players are possibilities then make a smaller array with the possibilities
        int[] possibilities = new int[currId+1];
        for(int i = 0; i < possibilities.length; i++)
            possibilities[i] = ids[i];
        return possibilities;
    }

   /**
     *Set up the TradeOffer
     */
    public void TradePlayer() throws IllegalMoveException, InsufficientResourcesException
    {
        if(tt.getStatus() != 1)
            throw new IllegalMoveException("not the trading phase");

        to =  players[tt.getCurrentPlayer()].tradePlayer();
    }

    /**
     * Checks to see if trading resources with the bank is a legal move for the player
     *
     * @return boolean whether or not the player can trade with the bank
     */
    public boolean canTradeBank(ResourceList rl)
    {
        int cp = tt.getCurrentPlayer();
        to = players[cp].TradeBank();
        if(to == null)
            return false;
        return bank.canTrade(to);
    }
    /**
     * completes a transaction of resources with the bank
     *
     * @return boolean whether or not the player traded with the bank
     */
    public void tradeBank(ResourceList rl)
    {
        bank.trade(to);
        players[tt.getCurrentPlayer()].trade(to);
    }

    /**
     * Checks to see accepting a trade request is a legal move for the player
     *
     * @return boolean whether or not the player can accept a trade offer from another player
     */
    public boolean canAcceptTrade()
    {
        int pid = to.getReciever();
        return players[pid].canAcceptTrade(to);
    }

    /**
     * accepts the trade offer of another player
     *
     * @return boolean whether or not the player accepted a trade offer
     */
    public void acceptTrade()
    {
        int pid = to.getReciever();
        int cp = tt.getCurrentPlayer();
        players[pid].trade(to);
        players[cp].trade(to);
    }
    /**
     * Checks to see if the player can roll the dice
     *
     * @return boolean whether or not the player can roll the dice
     */
    public boolean canRoll()
    {
        if(tt.getStatus() == 0)
            return true;
        return false;
    }

    //do methods
    /**
     * Buys a developement card and increases the amount for the purchasing player
     *
     * @return boolean whether or not the player bought the dev card
     */
    public void buyDevCard() throws IllegalMoveException
    {
        //be in the purchasing phase
        int cp = tt.getCurrentPlayer();
        if(tt.getStatus() != 2)
            throw new IllegalMoveException("Not a purchasing phase...");
        if(!players[cp].canBuyDevcard())
            throw new IllegalMoveException("Not enough resources");
        DevCardType dct = bank.BuyDevCard();
        players[cp].drawDevCard(dct);
    }

    /**
     * uses Monopoly
     *
     * @return boolean whether or not the player played a monopoly
     */
    public void playMonopoly(ResourceType r)
    {
        //use the ResourceList.merge method
        int cp = tt.getCurrentPlayer();

        int given = 0;
        for(int i = 0; i < players.length; i++)
            if(i != cp)
                given += players[i].Monopolize(r);
        players[cp].addResources(r, given);
    }

    /**
     * plays the road build card
     *
     * @return boolean
     */
    public void playRoadBuilding()
    {
        int cp = tt.getCurrentPlayer();
        players[cp].playRoadBuilding();
    }

    /**
     * plays a monument card
     *
     * @return boolean whether or not the player placed a monument
     */
    public boolean placeMonument() {
        return false;
    }

    /**
     * Places a Soldier and grants the effects he brings
     *
     * @return boolean whether or not the player played the soldier card
     */
    public boolean placeSoldier() {
        return false;
    }

    /**
     * Robs a player of one resource card
     *
     * @return boolean whether or not the player chose to rob
     */
    public boolean rob() {
        return false;
    }

    /**
     * enacts the trade offer of the specified player
     *
     * @return boolean whether or not the player traded with another player
     */
    public boolean tradePlayer() {
        return false;
    }

    /**
     * rolls the dice for a number 1-12
     *
     * @return boolean whether or not the player rolled the dice
     */
    public boolean roll()
    {
        if(tt.getStatus() != 0)
            return false;
        int cp = tt.getCurrentPlayer();
        int diceRoll = dice.rollDice();

        if(diceRoll == 7)
            RobberDiscard();
        else
        {
            ResourceList[] pids = map.rollingDice(diceRoll);
            for (int i = 0; i < pids.length; i++)
                players[i].addResources(pids[i]);
        }

        tt.updateStatus();
        return true;
    }

    public boolean canSendChat(String msg, int pid)
    {
        chat.addMessage(players[pid].getName(), msg);
        return true;
    }
}
