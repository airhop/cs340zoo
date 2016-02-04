package client.proxy;


import client.model.bank.ResourceList;
import client.model.history.MessageList;
import shared.definitions.ResourceType;
import shared.exceptions.*;
import shared.jsonobject.Resources;
import shared.jsonobject.User;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class MockProxy implements IProxy{

    public MockProxy(){

    }

    /**
     * Sets up a mock Proxy
     */
    public void runMock(){

    }


    @Override
    public void userLogin(User u) throws InvalidUserException {

    }

    @Override
    public void userRegister(User u) throws InvalidUserException {

    }

    @Override
    public String[] gamesList() {
        return new String[0];
    }

    @Override
    public void gamesCreate(String s) throws FailedCreateGameException {

    }

    @Override
    public void gamesJoin(String s, int playerId) throws InvalidUserException {

    }

    @Override
    public void gamesSave() {

    }

    @Override
    public void gamesLoad() {

    }

    @Override
    public void getGameModel() {

    }

    @Override
    public void resetCommands() {

    }

    @Override
    public void runCommand() {

    }

    @Override
    public void listCommands() {

    }

    @Override
    public boolean gameAddAI() {
        return false;
    }

    @Override
    public String[] gameListAI() {
        return new String[0];
    }

    @Override
    public void sendChat(MessageList msg) {

    }

    @Override
    public void rollNumber(int numRoled) {

    }

    @Override
    public void robPlayer(int playerIdOne, int playerIdTwo, HexLocation Hl) {

    }

    @Override
    public void finishTurn(int playerId) {

    }

    @Override
    public void buyDevCard(int playerId) throws InsufficientResourcesException {

    }

    @Override
    public void playYearOfPlenty(int playerId, ResourceType r1, ResourceType r2) {

    }

    @Override
    public void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2) {

    }

    @Override
    public void playSoldier(int playerId, HexLocation El) {

    }

    @Override
    public void playMonopoly(int playerId, ResourceType r1) {

    }

    @Override
    public void placeMonument(int playerId) {

    }

    @Override
    public void buildRoad(int playerId, EdgeLocation el) {

    }

    @Override
    public void buildSettlement(int playerId, VertexLocation vl) throws IllegalBuildException {

    }

    @Override
    public void buildCity(int playerId, VertexLocation vl) throws IllegalBuildException {

    }

    @Override
    public void offerTrade(int playerIdOne, int playerIdTwo, ResourceList rl) {

    }

    @Override
    public void acceptTrade(int playerIdOne, int playerIdTwo, ResourceList rl) {

    }

    @Override
    public void meritimeTrade(int playerId, int ratio, ResourceList in, ResourceList out) {

    }

    @Override
    public void discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException {

    }
}
