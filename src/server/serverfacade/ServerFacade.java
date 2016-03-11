package server.serverfacade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

/**
 * Created by Josh on 3/10/2016.
 */
public class ServerFacade implements IServerFacade {

    /**
     * The command objects will call this method to run a server operation
     * @param username -
     * @param password -
     */
    @Override
    public void userLogin(String username, String password) {

    }

    /**
     * The command objects will call this method to run a server operation
     * @param username -
     * @param password -
     */
    @Override
    public void userRegister(String username, String password) {

    }

    /**
     * The command objects will call this method to run a server operation
     * @return GameModel -
     */
    @Override
    public GameModel getList() {
        return null;
    }

    /**
     * The command objects will call this method to run a server operation
     * @param title
     * @param id
     * @param players
     */
    @Override
    public void createGame(String title, int id, Player[] players) {

    }

    /**
     *
     * @param id
     * @param color
     */
    @Override
    public void joinGame(int id, String color) {

    }

    /**
     *
     * @param id
     * @param name
     */
    @Override
    public void save(int id, String name) {

    }

    /**
     *
     * @param name
     */
    @Override
    public void load(String name) {

    }

    /**
     *
     * @return
     */
    @Override
    public GameModel getModel() {
        return null;
    }

    /**
     *
     * @param AIType
     */
    @Override
    public void addAI(String AIType) {

    }

    /**
     *
     */
    @Override
    public void listAI() {

    }

    /**
     *
     * @param playerIndex
     * @param content
     */
    @Override
    public void sendChat(int playerIndex, String content) {

    }

    /**
     *
     * @param playerIndex
     * @param number
     */
    @Override
    public void rollNumber(int playerIndex, int number) {

    }

    /**
     *
     * @param plyerIndex
     * @param victimIndex
     * @param location
     */
    @Override
    public void robPlayer(int plyerIndex, int victimIndex, HexLocation location) {

    }

    /**
     *
     * @param playerIndex
     */
    @Override
    public void finishTurn(int playerIndex) {

    }

    /**
     *
     * @param playerIndex
     */
    @Override
    public void buyDevCard(int playerIndex) {

    }

    /**
     *
     * @param playerIndex
     * @param res1
     * @param res2
     */
    @Override
    public void yearOfPlenty(int playerIndex, ResourceList res1, ResourceList res2) {

    }

    /**
     *
     * @param playerIndex
     * @param spot1
     * @param spot2
     */
    @Override
    public void roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {

    }

    /**
     *
     * @param playerIndex
     * @param victimIndex
     * @param location
     */
    @Override
    public void soldier(int playerIndex, int victimIndex, HexLocation location) {

    }

    /**
     *
     * @param playerIndex
     * @param resource
     */
    @Override
    public void monopoly(int playerIndex, String resource) {

    }

    /**
     *
     * @param playerIndex
     */
    @Override
    public void monument(int playerIndex) {

    }

    /**
     *
     * @param playerIndex
     * @param roadLocation
     * @param free
     */
    @Override
    public void buildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free) {

    }

    /**
     *
     * @param playerIndex
     * @param vertexLocation
     * @param free
     */
    @Override
    public void buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) {

    }

    /**
     *
     * @param playerIndex
     * @param vertexLocation
     */
    @Override
    public void buildCity(int playerIndex, VertexLocation vertexLocation) {

    }

    /**
     *
     * @param playerIndex
     * @param offer
     * @param receiver
     */
    @Override
    public void offerTrade(int playerIndex, ResourceList offer, int receiver) {

    }

    /**
     *
     * @param playerIndex
     * @param willAccept
     */
    @Override
    public void acceptTrade(int playerIndex, boolean willAccept) {

    }

    /**
     *
     * @param playerIndex
     * @param ratio
     * @param inputResource
     * @param outputResource
     */
    @Override
    public void maritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource) {

    }

    /**
     *
     * @param playerIndex
     * @param discardedCards
     */
    @Override
    public void discardCards(int playerIndex, ResourceList discardedCards) {

    }
}
