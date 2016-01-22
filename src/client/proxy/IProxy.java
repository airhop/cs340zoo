package client.proxy;


import client.model.ClientModel;
import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import shared.jsonobject.Login;
import shared.jsonobject.PassObject;

public interface IProxy {

    boolean userLogin(Login l);
    boolean userRegister(Login l);
    String[] gamesList();
    boolean gamesCreate(String s);
    boolean gamesJoin(String s, int playerId);
    boolean gameAddAI();
    String[]  gameListAI();
    void sendChat(MessageList msg);
    void finishTurn(int playerId);
    void buildCity(int playerId, EdgeLocation el);
    void buildSettlement(int playerId, EdgeLocation el);

    /**
     * Discards cards
     * @param playerId -
     * @param rl
     */
    void discardCards(int playerId, ResourceList rl);

    /**
     * Rolls a number and shows the affect on the server
     * @param numRoled - Numebr that is rolled
     */
    void rollNumber(int numRoled);
    /**
     * Returns the ClientModel when called
     */
    void getGameModel();

    /**
     * Given paramaters of the playerID and where to place the road in the PassObject
     * we send the server the information needed to place the road
     * @param playerId - PlayerId passed to the server
     * @param el - Location on the board to build
     */
    void placeRoad(int playerId, EdgeLocation el);

    /**
     * Given paramaters of the playerID and where to place the Settlement in the PassObject
     * we send the server the information needed to place the Settlement
     * @param playerId - PlayerId passed to the server
     * @param el - Location on the board to build
     */
    void placeSettlement(int playerId, EdgeLocation el);

    /**
     * Given paramaters of the playerID and where to place the City in the PassObject
     * we send the server the information needed to place the City
     * @param playerId - PlayerId passed to the server
     * @param el - Location on the board to build
     */
    void placeCity(int playerId, EdgeLocation el);

    /**
     * Parameters are passed for the buying of the DevCard on the PassObject
     * a clientModel is returned
     * @param playerId - PlayerId passed to the server
     */
    void buyDevCard(int playerId);

    /**
     * To play a Monopoly Card you call this function which contacts the Server proxy
     * @param playerId - PlayerId passed to the server
     * @param card - Selected card to take from players
     */
    void playMonopoly(int playerId, String card);

    /**
     * To play the Card road building you finally get here in the proxy and this finalizes the
     * playing of the card on the server
     * @param playerId - PlayerId passed to the server
     * @param e1 - Location on the board to build first road
     * @param e2 - Location on the board to build second road
     */
    void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2);

    /**
     * This method is used to play a victory od monument card finalizing the move on the server
     * @param playerId - PlayerId passed to the server
     */
    void placeMonument(int playerId);

    /**
     * This method finalizes the playing of the Year of plenty card from a player on the server
     * @param playerId - PlayerId passed to the server
     */
    void playYearOfPlenty(int playerId);
    /**
     * This finalizes the playing or placing of a soldier card which will increase the players count
     * towards the largest army
     * @param playerId - PlayerId passed to the server
     */
    void playSoldier(int playerId);

    /**
     * This method is used by the player when they either move the robber or they roll a seven
     * @param playerIdOne - playerIdOne passed to the server
     * @param playerIdTwo - playerIdTwo being robbed
     */
    void robPlayer(int playerIdOne, int playerIdTwo);

    /**
     * This is used by the player to move the robberer to a new location
     * @param playerId - PlayerId passed to the server
     * @param hl - Location on the board to move the robber
     */
    void moveRobber(int playerId, HexLocation hl);

    /**
     * This is to propose a trade to another player which will update a list
     * in the server model of trades taht can be made
     * @param playerIdOne - playerIdOne passed to the server
     * @param playerIdTwo - playerIdTwo being traded with
     * @param rl - Traded between the players
     */
    void tradePlayer(int playerIdOne, int playerIdTwo, ResourceList rl);

    /**
     * This is when you are going to trade with the bank this is fairly simple
     * so it only requires that you send information to finalize the trade on the server
     * @param playerId - playerIdOne passed to the server
     * @param rl - Traded with the bank
     */
    void tradeBank(int playerId, ResourceList rl);

    /**
     * This is used by the player to accept the trade that a player offer to them
     * updating their resources and the other players resources
     * @param playerIdOne - playerIdOne passed to the server
     * @param playerIdTwo - playerIdTwo being traded with
     * @param rl - Traded between the players
     */
    void acceptTrade(int playerIdOne, int playerIdTwo, ResourceList rl);

    /**
     * This is when a player wins the game!!! Wohooo!!!
     * @param playerId -Passed Object
     */
    void win(int playerId);
}
