package client.proxy;

import client.model.bank.ResourceList;
import client.model.history.MessageList;
import client.model.map.EdgeLocation;
import client.model.map.HexLocation;
import shared.exceptions.*;
import shared.jsonobject.Login;
import shared.serialization.Deserializer;
import shared.serialization.Serializer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy implements IProxy{
    private String SERVER_HOST = "localhost";
    private int SERVER_PORT = 8081;
    private String URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT;
    private final String HTTP_GET = "GET";
    private final String HTTP_POST = "POST";
    private Deserializer deSer = new Deserializer();
    private Serializer Ser = new Serializer();
    private Cookie userCookie = new Cookie();
    private Cookie gameCookie = new Cookie();

    public Proxy(){

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

    private Object doGet(String urlPath) throws ClientException {
        try {
            URL url = new URL(URL_PREFIX + urlPath);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HTTP_GET);
            connection.setRequestProperty(userCookie.getCookieName(), userCookie.getCookieValue());
            if(){

            }
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Object result = xmlStream.fromXML(connection.getInputStream());
                return result;
            }
            else {
                throw new ClientException(String.format("doGet failed: %s (http code %d)", urlPath, connection.getResponseCode()));
            }
        }
        catch (IOException e) {
            throw new ClientException(String.format("doGet failed: %s", e.getMessage()), e);
        }
    }

    private void doPost(String urlPath, Object postData) throws ClientException {
        try {
            URL url = new URL(URL_PREFIX + urlPath);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setDoOutput(true);
            connection.connect();
            xmlStream.toXML(postData, connection.getOutputStream());
            connection.getOutputStream().close();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new ClientException(String.format("doPost failed: %s (http code %d)", urlPath, connection.getResponseCode()));
            }
        }
        catch (IOException e) {
            throw new ClientException(String.format("doPost failed: %s", e.getMessage()), e);
        }
    }

}
