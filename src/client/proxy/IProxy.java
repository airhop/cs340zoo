package client.proxy;



import client.model.bank.ResourceList;
import client.model.history.MessageList;
import shared.definitions.ResourceType;
import shared.exceptions.*;
import shared.jsonobject.User;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public interface IProxy {

    /**
     * This is used to log a player in that has already registered
     * @param u - This is the Login or identification for the user
     */
    void userLogin(User u) throws InvalidUserException;

    /**
     * This is used to register a new player
     * @param u - This is the Login or identification for the user
     */
    void userRegister(User u) throws InvalidUserException;

    /**
     * Calls this method to get a list of the games to join
     * @return The names of all the gamse
     */
    String[] gamesList();

    /**
     * You are making a game
     * @param s - The name of the game
     */
    void gamesCreate(String s) throws FailedCreateGameException;

    /**
     * The player wants to join a game so they use this method
     * @param s - The game that they want to join
     * @param playerId - The player that wants to join
     */
    void gamesJoin(String color, int playerId) throws InvalidUserException;


    void gamesSave();

    void gamesLoad();

    /**
     * Returns the ClientModel when called
     */
    void getGameModel();

    void resetCommands();

    void runCommand();

    void listCommands();

    /**
     * When you want to add an AI to the game
     * @return True or False
     */
    boolean gameAddAI();

    /**
     * Game list for the AI's to join
     * @return String[] returned
     */
    String[] gameListAI();

    /**
     * Messages to be sent to all the players
     * @param content - The messages to be sent to the players
     */
    boolean sendChat(String content, int id);
    /**
     * Rolls a number and shows the affect on the server
     * @param numRoled - Number that is rolled
     */
    void rollNumber(int numRoled);
    /**
     * This method is used by the player when they either move the robber or they roll a seven
     * @param playerIdOne - playerIdOne passed to the server
     * @param playerIdTwo - playerIdTwo being robbed
     */
    void robPlayer(int playerIdOne, int playerIdTwo, HexLocation Hl);
    /**
     * Used when a player finishes his turn
     * @param playerId - the player that is ending their turn
     */
    void finishTurn(int playerId);
    /**
     * Parameters are passed for the buying of the DevCard on the PassObject
     * a clientModel is returned
     * @param playerId - PlayerId passed to the server
     */
    void buyDevCard(int playerId) throws InsufficientResourcesException;
    /**
     * This method finalizes the playing of the Year of plenty card from a player on the server
     * @param playerId - PlayerId passed to the server
     */
    void playYearOfPlenty(int playerId, ResourceType r1, ResourceType r2);
    /**
     * To play the Card road building you finally get here in the proxy and this finalizes the
     * playing of the card on the server
     * @param playerId - PlayerId passed to the server
     * @param e1 - Location on the board to build first road
     * @param e2 - Location on the board to build second road
     */
    void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2);
    /**
     * This finalizes the playing or placing of a soldier card which will increase the players count
     * towards the largest army
     * @param playerId - PlayerId passed to the server
     */
    void playSoldier(int playerId, int victimId, HexLocation El);
    /**
     * To play a Monopoly Card you call this function which contacts the Server proxy
     * @param playerId - PlayerId passed to the server
     * @param r1 - Selected card to take from players
     */
    void playMonopoly(int playerId, ResourceType r1);
    /**
     * This method is used to play a victory od monument card finalizing the move on the server
     * @param playerId - PlayerId passed to the server
     */
    void placeMonument(int playerId);
    /**
     * Given paramaters of the playerID and where to place the road in the PassObject
     * we send the server the information needed to place the road
     * @param playerId - PlayerId passed to the server
     * @param el - Location on the board to build
     */
    void buildRoad(int playerId, EdgeLocation el);
    /**
     * Player builds a settlement
     * @param playerId - Player that wants to build the settlement
     * @param vl - Edge/Vertex that it is going to be built to
     */
    void buildSettlement(int playerId, VertexLocation vl) throws IllegalBuildException;

    /**
     * Player that wants to build a city pushes it to the server
     * @param playerId - Player that wants to build
     * @param vl - Location to build the City
     */
    void buildCity(int playerId, VertexLocation vl) throws IllegalBuildException;
    /**
     * This is to propose a trade to another player which will update a list
     * in the server model of trades taht can be made
     * @param playerIdOne - playerIdOne passed to the server
     * @param playerIdTwo - playerIdTwo being traded with
     * @param rl - Traded between the players
     */
    void offerTrade(int playerIdOne, int playerIdTwo, ResourceList rl);
    /**
     * This is to propose a trade to another player which will update a list
     * in the server model of trades taht can be made
     * @param playerIdOne - playerIdOne passed to the server
     * @param accept - Traded between the players
     */
    void acceptTrade(int playerIdOne, boolean accept);
    /**
     * This is when you are going to trade with the bank this is fairly simple
     * so it only requires that you send information to finalize the trade on the server
     * @param playerId - playerIdOne passed to the server
     * @param ratio - Traded with the bank
     */
    void meritimeTrade(int playerId, int ratio, ResourceList in, ResourceList out);
    /**
     * Discards cards
     * @param playerId - Player to discard
     * @param rl - Resources to discard
     */
    void discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException;
}
