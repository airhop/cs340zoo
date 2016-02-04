package client.proxy;

import client.model.bank.ResourceList;
import client.model.history.MessageList;
import com.google.gson.*;
import shared.exceptions.*;
import shared.jsonobject.Resources;
import shared.jsonobject.User;
import shared.locations.EdgeLocation;
import shared.serialization.HttpURLResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy implements IProxy{
    private String SERVER_HOST;
    private int SERVER_PORT;
    private String URL_PREFIX;
    private final String HTTP_GET = "GET";
    private final String HTTP_POST = "POST";
    private Cookie userCookie;
    private Cookie gameCookie;
    private Gson myGson;

    public Proxy(){
        SERVER_HOST = "localhost";
        SERVER_PORT = 8081;
        URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT;
        userCookie = new Cookie();
        gameCookie = new Cookie();
        myGson = new Gson();
    }


    public HttpURLResponse doGet(String commandName) throws ClientException {

        HttpURLResponse result = new HttpURLResponse();
        try {
            URL url = new URL(URL_PREFIX + "/" + commandName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HTTP_GET);
            connection.connect();

            result.setResponseCode(connection.getResponseCode());
            result.setResponseLength(connection.getContentLength());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if(connection.getContentLength() == 0) {
//                    result.setResponseBody(xmlStream.fromXML(connection.getInputStream()));
                }
            } else {
                throw new ClientException(String.format("doGet failed: %s (http code %d)", commandName, connection.getResponseCode()));
            }
        }
        catch (IOException e) {
            throw new ClientException(String.format("doGet failed: %s", e.getMessage()), e);
        }
        return result;
    }

    public HttpURLResponse doPost(String commandName, Object postData) throws ClientException {

        HttpURLResponse result = new HttpURLResponse();

        try {
            URL url = new URL(URL_PREFIX + commandName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setDoOutput(true);
            connection.connect();


//            xmlStream.toXML(postData, connection.getOutputStream());
            connection.getOutputStream().close();

            result.setResponseCode(connection.getResponseCode());
            result.setResponseLength(connection.getContentLength());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if(connection.getContentLength() == 0) {
//                    result.setResponseBody(xmlStream.fromXML(connection.getInputStream()));
                }
            } else {
                throw new ClientException(String.format("doPost failed: %s (http code %d)", commandName, connection.getResponseCode()));
            }
        }
        catch (IOException e) {
            throw new ClientException(String.format("doPost failed: %s", e.getMessage()), e);
        }
        return result;
    }


//    private Object doGet(String urlPath, Class myClass) throws ClientException {
//        try {
//            URL url = new URL(URL_PREFIX + urlPath);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestMethod(HTTP_GET);
//
//            if(userCookie.isActive()){
//                connection.setRequestProperty(userCookie.getCookieName(), userCookie.getCookieValue());
//            }
//            if(gameCookie.isActive()){
//                connection.setRequestProperty(gameCookie.getCookieName(), gameCookie.getCookieValue());
//            }
//            connection.connect();
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                JsonElement myArray = new JsonArray();
//                JsonObject myObj = new JsonObject();
//                JsonParser myParser = new JsonParser();
//                JsonObject obj = new JsonObject();
//
//                myArray = myParser.parse(connection.getInputStream().toString());
//                System.out.println(connection.getInputStream().toString());
//
//                return myGson.fromJson(connection.getInputStream().toString(), myClass);
//
//            }
//            else {
//                throw new ClientException(String.format("doGet failed: %s (http code %d)", urlPath, connection.getResponseCode()));
//            }
//        }
//        catch (IOException e) {
//            throw new ClientException(String.format("doGet failed: %s", e.getMessage()), e);
//        }
//    }
//
//    private Object doPost(String urlPath, Object postData) throws ClientException {
//        try {
//            URL url = new URL(URL_PREFIX + urlPath);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            String temp;
//            connection.setRequestMethod(HTTP_POST);
//
//            if(userCookie.isActive()){
//                connection.setRequestProperty(userCookie.getCookieName(), userCookie.getCookieValue());
//            }
//            if(gameCookie.isActive()){
//                connection.setRequestProperty(gameCookie.getCookieName(), gameCookie.getCookieValue());
//            }
//
//            connection.setDoOutput(true);
//            connection.connect();
//            temp = myGson.toJson(postData);
//            ObjectOutputStream myOut = new ObjectOutputStream(connection.getOutputStream());
//            myOut.writeBytes(temp);
//
////            myOut.close();
////            connection.getOutputStream().close();
//            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                throw new ClientException(String.format("doPost failed: %s (http code %d)", urlPath, connection.getResponseCode()));
//            }else{//i need to grab cookies and the json
//
//                return myGson.fromJson(connection.getInputStream().toString(),);
//            }
//        }
//        catch (IOException e) {
//            throw new ClientException(String.format("doPost failed: %s", e.getMessage()), e);
//        }
//    }


    @Override
    public void userLogin(User u) throws InvalidUserException {
        JsonElement myArray = new JsonArray();
        JsonObject myObjOne = new JsonObject();
        JsonObject myObjTwo = new JsonObject();
        myObjOne.addProperty("username", u.getUsername());
        myObjOne.addProperty("password", u.getPassword());
        System.out.println(myObjOne.toString());
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
