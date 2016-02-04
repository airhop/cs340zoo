package client.proxy;


import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import shared.exceptions.*;
import shared.jsonobject.Login;
import shared.jsonobject.Resources;
import shared.jsonobject.User;

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
    public void robPlayer(int playerIdOne, int playerIdTwo, EdgeLocation El) {

    }

    @Override
    public void finishTurn(int playerId) {

    }

    @Override
    public void buyDevCard(int playerId) throws InsufficientResourcesException {

    }

    @Override
    public void playYearOfPlenty(int playerId, Resources r1, Resources r2) {

    }

    @Override
    public void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2) {

    }

    @Override
    public void playSoldier(int playerId, EdgeLocation El) {

    }

    @Override
    public void playMonopoly(int playerId, String card) {

    }

    @Override
    public void placeMonument(int playerId) {

    }

    @Override
    public void buildRoad(int playerId, EdgeLocation el) {

    }

    @Override
    public void buildSettlement(int playerId, EdgeLocation el) throws IllegalBuildException {

    }

    @Override
    public void buildCity(int playerId, EdgeLocation el) throws IllegalBuildException {

    }

    @Override
    public void offerTrade(int playerIdOne, int playerIdTwo, ResourceList rl) {

    }

    @Override
    public void tradeBank(int playerId, ResourceList rl) {

    }

    @Override
    public void discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException {

    }
}
