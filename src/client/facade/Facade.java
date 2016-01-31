package client.facade;

import shared.exceptions.*;
import client.model.*;
import client.model.map.*;

public class Facade
{
    GameModel game;

    public Facade()
    {
        game = null;
    }

    /**
     * This is used to see when the robber moves if the player can discard
     * @return
     */
    public boolean canRobberDiscard()
    {
        if(game == null)
            return false;

        return game.canRobberDiscard();
    }
    //can methods
    /**
     * Checks to see if building a road is a legal move for the player
     * @return boolean whether or not the player can build a road
     */
    public boolean canBuildRoad()
    {
        if(game == null)
            return false;
        return game.canBuildRoad();
    }
    /**
     * Checks to see if placing a road is a legal move for the player
     * @return boolean whether or not the player can place a road
     */
    public boolean canPlaceRoad()
    {
        if(game == null)
            return false;
        return game.canPlaceRoad();
    }
    /**
     * Checks to see if building a settlement is a legal move for the player
     * @return boolean whether or not the player can build a settlement
     */
    public boolean canBuildSettlement()
    {
        if(game == null)
            return false;
        return game.canBuildSettlement();
    }
    /**
     * Checks to see if placing a settlement is a legal move for the player
     * @return boolean whether or not the player can place a settlement
     */
    public boolean canPlaceSettlement()
    {
        if(game == null)
            return false;
        return game.canPlaceSettlement();
    }
    /**
     * Checks to see if building a city is a legal move for the player
     * @return boolean whether or not the player can build a city
     */
    public boolean canBuildCity()
    {
        if(game == null)
            return false;
        return game.canBuildCity();
    }
    /**
     * Checks to see if placing a city is a legal move for the player
     * @return boolean whether or not the player can place a city
     */
    public boolean canPlaceCity()
    {
        if(game == null)
            return false;
        return game.canPlaceCity();
    }
    /**
     * Checks to see if buying a Developement Card is a legal move for the player
     * @return boolean whether or not the player can buy a Developement card
     */
    public boolean canBuyDevcard()
    {
        if(game == null)
            return false;
        return game.canBuyDevcard();
    }
    /**
     * Checks to see if playing a Developement Card is a legal move for the player
     * @return boolean whether or not the player can play a Developement card
     */
    public boolean canPlayDevcard()
    {
        if(game == null)
            return false;
        return game.canPlayDevcard();
    }
    /**
     * Checks to see if Montoply is a legal move for the player
     * @return boolean whether or not the player can monopoly
     */
    public boolean canMonopoly()
    {
        if(game == null)
            return false;
        return game.canMonopoly();
    }
    /**
     * Checks to see if building a road in a specific place is a legal move for the player
     * @return boolean whether or not the player can road building
     */
    public boolean canRoadBuilding()
    {
        if(game == null)
            return false;
        return game.canRoadBuilding();
    }
    /**
     * Checks to see if placing a Monument Card(?) is a legal move for the player
     * @return boolean whether or not the player can place a monument
     */
    public boolean canPlaceMonument()
    {
        if(game == null)
            return false;
        return game.canPlaceMonument();
    }
    /**
     * Checks to see if placing a Year Of Plenty card is a legal move for the player
     * @return boolean whether or not the player can play the Year of Plenty card
     */
    public boolean canYearOfPlenty()
    {
        if(game == null)
            return false;
        return game.canYearOfPlenty();
    }
    /**
     * Checks to see if placing a Soldier card is a legal move for the player
     * @return boolean whether or not the player can place the Soldier card
     */
    public boolean canPlaceSoldier()
    {
        if(game == null)
            return false;
        return game.canPlaceSoldier();
    }
    /**
     * Checks to see if robbing another player is a legal move for the player
     * @return boolean whether or not the player can rob another player
     */
    public boolean canRob()
    {
        if(game == null)
            return false;
        return game.canRob();
    }
    /**
     * Checks to see if moving the robber is a legal move for the player
     * @return boolean whether or not the player can move the robber
     */
    public boolean canMoveRobber()
    {
        if(game == null)
            return false;
        return game.canMoveRobber();
    }
    /**
     * Checks to see if trading resource cards with another player is a legal move for the player
     * @return boolean whether or not the player can trade with another player
     */
    public boolean canTradePlayer()
    {
        if(game == null)
            return false;
        return game.canTradePlayer();
    }
    /**
     * Checks to see if trading resources with the bank is a legal move for the player
     * @return boolean whether or not the player can trade with the bank
     */
    public boolean canTradeBank()
    {
        if(game == null)
            return false;
        return game.canTradeBank();
    }
    /**
     * Checks to see accepting a trade request is a legal move for the player
     * @return boolean whether or not the player can accept a trade offer from another player
     */
    public boolean canAcceptTrade()
    {
        if(game == null)
            return false;
        return game.canAcceptTrade();
    }
    /**
     * Checks to see if the player can win the game
     * @return boolean whether or not the player can win (have 10 victory points)
     */
    public boolean canWin()
    {
        if(game == null)
            return false;
        return game.canWin();
    }
    /**
     * Checks to see if the player can roll the dice
     * @return boolean whether or not the player can roll the dice
     */
    public boolean canRoll()
    {
        if(game == null)
            return false;
        return game.canRoll();
    }

    public boolean canFinishTurn()
    {
        if(game == null)
            return false;
        return game.canFinishTurn();
    }

    public boolean canDiscardCards()
    {
        if(game == null)
            return false;
        return game.canDiscardCards();
    }

    //do methods
    /**
     * Places a Road at a given location on the map
     * @return boolean whether or not the player built the road (perhaps placeholder return values for all of the do methods)
     */
    public void placeRoad(EdgeLocation el)
    {
        game.placeRoad(el);
    }
    /**
     * Places a Settlement at a given location on the map
     * @return boolean whether or not the player placed a settlement
     */
    public void placeSettlement() throws InvalidPositionException {}
    /**
     * Places a City at a given location on the map
     * @return boolean whether or not the player placed the city
     */
    public void placeCity() throws InvalidPositionException{}
    /**
     * Buys a developement card and increases the amount for the purchasing player
     * @return boolean whether or not the player bought the dev card
     */
    public void buyDevCard() throws IllegalMoveException
    {
        game.buyDevCard();
    }
    /**
     * uses a specific development card
     * @return boolean whether or not the player played a devcard
     */
    public void playDevCard() throws IllegalMoveException{}
    /**
     * uses Monopoly
     * @return boolean whether or not the player played a monopoly
     */
    public void playMonopoly() throws IllegalMoveException{}
    /**
     * plays the road build card
     * @return boolean
     */
    public void playRoadBuilding() throws IllegalMoveException{}
    /**
     * plays a monument card
     * @return boolean whether or not the player placed a monument
     */
    public void placeMonument() throws IllegalMoveException{}
    /**
     * plays the year of plenty card for a given player
     * @return boolean whether or not the player played the year of plenty card
     */
    public void playYearOfPlenty() throws IllegalMoveException{}
    /**
     * Places a Soldier and grants the effects he brings
     * @return boolean whether or not the player played the soldier card
     */
    public void placeSoldier() throws IllegalMoveException{}
    /**
     * Robs a player of one resource card
     * @return boolean whether or not the player chose to rob
     */
    public void rob()throws IllegalMoveException{}
    /**
     * Places a the robber at a specific location on the map
     * @return boolean whether or not the player moved the robber
     */
    public void moveRobber() throws IllegalMoveException{}
    /**
     * enacts the trade offer of the specified player
     * @return boolean whether or not the player traded with another player
     */
    public void tradePlayer() throws IllegalMoveException, IllegalMoveException{}
    /**
     * completes a transaction of resources with the bank
     * @return boolean whether or not the player traded with the bank
     */
    public void tradeBank() throws InsufficientResourcesException, IllegalMoveException{}
    /**
     * accepts the trade offer of another player
     * @return boolean whether or not the player accepted a trade offer
     */
    public void acceptTrade() throws InsufficientResourcesException, IllegalMoveException{}
    /**
     * ends the game and congratulates the player with 10 victory points
     * @return boolean whether or not the player has sufficient victory points to win
     */
    public void win() throws InvalidWinnerException {}
    /**
     * rolls the dice for a number 1-12
     * @return boolean whether or not the player rolled the dice
     */
    public void roll() throws IllegalMoveException{}
}
