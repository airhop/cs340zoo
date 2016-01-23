package client.proxy;


import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import shared.exceptions.*;
import shared.jsonobject.Login;

public class MockProxy implements IProxy{

    public MockProxy(){

    }

    /**
     * Sets up a mock Proxy
     */
    public void runMock(){

    }


    @Override
    public void userLogin(Login l) throws InvalidUserException {

    }

    @Override
    public void userRegister(Login l) throws InvalidUserException {

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
    public void finishTurn(int playerId) {

    }

    @Override
    public void buildRoad(int playerId, EdgeLocation el) {

    }

    @Override
    public void buildCity(int playerId, EdgeLocation el) throws IllegalBuildException {

    }

    @Override
    public void buildSettlement(int playerId, EdgeLocation el) throws IllegalBuildException {

    }

    @Override
    public void discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException {

    }

    @Override
    public void rollNumber(int numRoled) {

    }

    @Override
    public void getGameModel() {

    }

    @Override
    public void buyDevCard(int playerId) throws InsufficientResourcesException {

    }

    @Override
    public void playMonopoly(int playerId, String card) {

    }

    @Override
    public void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2) {

    }

    @Override
    public void placeMonument(int playerId) {

    }

    @Override
    public void playYearOfPlenty(int playerId) {

    }

    @Override
    public void playSoldier(int playerId) {

    }

    @Override
    public void robPlayer(int playerIdOne, int playerIdTwo) {

    }

    @Override
    public void moveRobber(int playerId, HexLocation hl) {

    }

    @Override
    public void tradePlayer(int playerIdOne, int playerIdTwo, ResourceList rl) {

    }

    @Override
    public void tradeBank(int playerId, ResourceList rl) {

    }

    @Override
    public void acceptTrade(int playerIdOne, int playerIdTwo, ResourceList rl) {

    }

    @Override
    public void win(int playerId) throws InvalidWinnerException {

    }
}
