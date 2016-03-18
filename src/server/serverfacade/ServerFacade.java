package server.serverfacade;

import client.MVC.data.GameInfo;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/10/2016.
 */
public class ServerFacade implements IServerFacade {

    private List<GameModel> gamesList;
    private List<GameInfo> gameInfoList;


    private static ServerFacade facade = null;

    public static ServerFacade getInstance(){
        if(facade == null){
            facade = new ServerFacade();
        }
        return facade;
    }

    public ServerFacade(){
        gamesList = new ArrayList<>();
        gameInfoList = new ArrayList<>();
    }

    /**
     * The command objects will call this method to run a server operation
     * @param username - the username the player is attempting
     * @param password - the password the player is attempting
     */
    @Override
    public void userLogin(String username, String password) {

    }

    /**
     * The command objects will call this method to run a server operation
     * @param username - the player chosen username
     * @param password - the player chosen password
     */
    @Override
    public void userRegister(String username, String password) {

    }

    /**
     * The command objects will call this method to run a server operation
     * @return GameModel -
     */
    @Override
    public List<GameInfo> getGamesList() {
        return null;
    }

    /**
     * The command objects will call this method to run a server operation of creating a game
     * @param title the title of the game
     * @param id the id of the game
     * @param players the players in the game
     */
    @Override
    public void createGame(String title, int id, Player[] players) {

    }

    /**
     * The command objects will call this method to run the server operation to join a game.
     * @param id - the id of the player joining the game
     * @param color - the color chosen by the player for the game.
     */
    @Override
    public void joinGame(int id, String color) {

    }

    /**
     * The command objects will call this method to run the server operation to save a game
     * @param id the id of the game being saved
     * @param name the name of the game being saved.
     */
    @Override
    public void save(int id, String name) {

    }

    /**
     * The command objects will call this method to load an existing game that has been saved
     * @param name the name of the game being loaded
     */
    @Override
    public void load(String name) {

    }

    /**
     * The command objects will call this method to run the server operation of getting the game model
     * @return the game model
     */
    @Override
    public GameModel getModel() {
        return null;
    }

    /**
     * The comand objects will call this method to run the server operation of adding an AI player to a game
     * @param AIType the type of AI that the player wants to play against
     */
    @Override
    public void addAI(String AIType) {

    }

    /**
     * The command objects will call this method to run the server operation listing the AI in a game
     */
    @Override
    public void listAI() {

    }

    /**
     * The command objects will call this method to run the server operation of sending a chat message
     * @param playerIndex the id of the player sending the message
     * @param content the content of the message being sent
     */
    @Override
    public void sendChat(int playerIndex, String content) {

    }

    /**
     * The command objects will call this method to run the server operation of rolling a number
     * @param playerIndex the id of the player rolling
     * @param number the number that was rolled which takes place in the client
     */
    @Override
    public void rollNumber(int playerIndex, int number) {

    }

    /**
     * The command objects will call this method to run the server operation of robbing a player
     * @param plyerIndex the id of the player doing the robbing
     * @param victimIndex the id of the player being robbed
     * @param location the location of the robber
     */
    @Override
    public void robPlayer(int plyerIndex, int victimIndex, HexLocation location) {

    }

    /**
     * The command ojects will call this method to run the server operation of finishing a turn
     * @param playerIndex the id of the player ending their turn
     */
    @Override
    public void finishTurn(int playerIndex) {

    }

    /**
     * Thee command objects will call this method to run the server operation of buying a developemnt card
     * @param playerIndex the id of the player buying the developement card
     */
    @Override
    public void buyDevCard(int playerIndex) {

    }

    /**
     * the command objects will call this method to tun the server operation of playing a Year of Plenty card
     * @param playerIndex the id of the player using the Year of Plenty card
     * @param res1
     * @param res2
     */
    @Override
    public void yearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2) {

    }

    /**
     * The command objects will call this method to run a server operation
     * @param playerIndex the index of the current player
     * @param spot1
     * @param spot2
     */
    @Override
    public void roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {

    }

    /**
     * The command objects will call this method to run the server operation of playing a soldier card
     * @param playerIndex the index of the player using the soldier card
     * @param victimIndex the index of the player being robbed
     * @param location the new location of the robber
     */
    @Override
    public void soldier(int playerIndex, int victimIndex, HexLocation location) {

    }

    /**
     * The command objects will call this method to run the server operation of playing a monopoly card
     * @param playerIndex the id of the player using the monopoly card
     * @param resource
     */
    @Override
    public void monopoly(int playerIndex, String resource) {

    }

    /**
     * The command objects will call this method to run the server operation of using a monument card
     * @param playerIndex the id of the player using the monument card
     */
    @Override
    public void monument(int playerIndex) {

    }

    /**
     * The command objects will call this method to run the server operation of building a road
     * @param playerIndex the id of the player building the road
     * @param roadLocation the location the player wishes to place the road
     * @param free whether or not the spot they are trying to build on is available
     */
    @Override
    public void buildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free) {

    }

    /**
     * The command objects will call this method to run the server operation of building a settlement
     * @param playerIndex the id of the player building the settlement
     * @param vertexLocation the location the player wishes to place the settlement
     * @param free whether or not the spot the player is attempting to build on is available
     */
    @Override
    public void buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) {

    }

    /**
     * The command objects will call this method to run the server operation of building a city
     * @param playerIndex the id of the player wishing to build a city
     * @param vertexLocation the location the player wants to build the city at (on an existing settlement)
     */
    @Override
    public void buildCity(int playerIndex, VertexLocation vertexLocation) {

    }

    /**
     * The command objects will call this method to run the server operation of making a trade offer
     * @param playerIndex the id of the player making an offer
     * @param offer the offered resources by the offeree
     * @param receiver the id of the player receiving the trade offer
     */
    @Override
    public void offerTrade(int playerIndex, ResourceList offer, int receiver) {

    }

    /**
     * The command objects will call this method to run the server operation of accepting a trade
     * @param playerIndex the id of the player who will accept a trade
     * @param willAccept whether or not the player did accept the trade (true = yes)
     */
    @Override
    public void acceptTrade(int playerIndex, boolean willAccept) {

    }

    /**
     * The command objects will call this method to run the server operation of conducting a maritime trade
     * @param playerIndex the id of the player wanting to maritime trade
     * @param ratio the ratio the player is able to trade at
     * @param inputResource the resource that the player is offering
     * @param outputResource the resource the player wants to receive
     */
    @Override
    public void maritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource) {

    }

    /**
     * The command objects will call this method to run the server operation of discarding cards
     * @param playerIndex the id of the player being forced to discard cards
     * @param discardedCards the resources the player is discarding
     */
    @Override
    public void discardCards(int playerIndex, ResourceList discardedCards) {

    }
}
