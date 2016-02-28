package client.proxy;

import client.MVC.data.GameInfo;
import client.model.GameModel;
import client.model.bank.ResourceList;
import com.google.gson.*;
import com.sun.prism.paint.Stop;
import shared.definitions.ResourceType;
import shared.exceptions.*;
import shared.extra.StopWatch;
import shared.jsonobject.User;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.serialization.CreateGamePassObject;
import shared.serialization.Deserializer;
import shared.serialization.GameListDeserialize;
import shared.serialization.HttpURLResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Proxy implements IProxy {
    private String SERVER_HOST;
    private int SERVER_PORT;
    private String URL_PREFIX;
    private final String HTTP_GET = "GET";
    private final String HTTP_POST = "POST";
    private Cookie userCookie;
    private Cookie gameCookie;
    private Deserializer myDeSer;
    private Gson myGson;
    private String cookiesList;
    private GameModel myGameModel;
    private int playerId;
    private HttpURLResponse lastResponse;

    public Proxy(GameModel givenGameModel) {
        SERVER_HOST = "localhost";
        SERVER_PORT = 8081;
        URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT;
        userCookie = new Cookie();
        gameCookie = new Cookie();
        myGson = new Gson();
        cookiesList = "";
        myDeSer = new Deserializer();
        myGameModel = givenGameModel;
        playerId = -1;
        lastResponse = new HttpURLResponse();
    }

    public int getPlayerId() {
        if (playerId == -1) {
            playerId = Integer.parseInt(userCookie.getPlayerId());
            return playerId;
        } else {
            return playerId;
        }
    }


    public HttpURLResponse doGet(String urlPath) throws ClientException {
        HttpURLResponse result = new HttpURLResponse();
        try {
            URL url = new URL(URL_PREFIX + urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_GET);
            connection.setDoOutput(true);

            if (userCookie.isActive()) {
                cookiesList = userCookie.getCookieName() + "=" + userCookie.getCookieValue();
                if (gameCookie.isActive()) {
                    cookiesList = cookiesList + "; " + gameCookie.getCookieName() + "=" + gameCookie.getCookieValue();
                //    System.out.println(cookiesList);
                    connection.setRequestProperty("Cookie", cookiesList);
                } else {
                    connection.setRequestProperty("Cookie", cookiesList);
                }
            }


           // System.out.println(url.toString());
            connection.connect();
            OutputStreamWriter myOut = new OutputStreamWriter(connection.getOutputStream());
            myOut.flush();

//            System.out.println(connection.getResponseCode());

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (connection.getContentLength() != 0) {
                    result.setResponseCode(connection.getResponseCode());
                    result.setResponseLength(connection.getContentLength());
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    result.setResponseBody(myReader.readLine());
                    result.setCookie(connection.getHeaderField("Set-cookie"));
                    connection.disconnect();
                }
            } else {
            //    System.out.println(connection.getResponseMessage());
                int code = connection.getResponseCode();
                connection.disconnect();
                throw new ClientException(String.format("doGet failed: %s (http code %d)", urlPath, code));
            }
        } catch (IOException e) {
            throw new ClientException(String.format("doGet failed: %s", e.getMessage()), e);
        }
        lastResponse = result;
        return result;
    }

    public HttpURLResponse doPost(String urlPath, JsonObject myObj) throws ClientException {

        HttpURLResponse result = new HttpURLResponse();
        try {
            URL url = new URL(URL_PREFIX + urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setDoOutput(true);

            if (userCookie.isActive()) {
                cookiesList = userCookie.getCookieName() + "=" + userCookie.getCookieValue();
                if (gameCookie.isActive()) {
                    cookiesList = cookiesList + "; " + gameCookie.getCookieName() + "=" + gameCookie.getCookieValue();
                //    System.out.println(cookiesList);
                    connection.setRequestProperty("Cookie", cookiesList);
                } else {
                    connection.setRequestProperty("Cookie", cookiesList);
                }

            }

            //System.out.println(url.toString());
            connection.connect();
            OutputStreamWriter myOut = new OutputStreamWriter(connection.getOutputStream());
            myOut.write(myObj.toString());
            myOut.flush();
//            System.out.println(connection.getResponseCode());

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if (connection.getContentLength() != 0) {
                    result.setResponseCode(connection.getResponseCode());
                    result.setResponseLength(connection.getContentLength());
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    result.setResponseBody(myReader.readLine());
                    result.setCookie(connection.getHeaderField("Set-cookie"));
                    connection.disconnect();
                }
            } else {
              //  System.out.println(connection.getResponseMessage());
                int code = connection.getResponseCode();
                connection.disconnect();
                throw new ClientException(String.format("doPost failed: %s (http code %d)", urlPath, code));
            }
        } catch (IOException e) {
            throw new ClientException(String.format("doPost failed: %s", e.getMessage()), e);
        }
        lastResponse = result;
        return result;
    }


    public int getResponseCode() {
        return lastResponse.getResponseCode();
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
    public boolean userLogin(User u) throws InvalidUserException {
        JsonObject myObjOne = new JsonObject();
        String url = "/user/login";
        myObjOne.addProperty("username", u.getUsername());
        myObjOne.addProperty("password", u.getPassword());
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
            if(myResponse.getResponseCode() == 200){
                userCookie.setFullCookie(myResponse.getCookie());
                userCookie.getPlayerId();
                userCookie.getDecode();
                return true;
            }else{
                return false;
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void userRegister(User u) throws InvalidUserException {
        JsonObject myObjOne = new JsonObject();
        String url = "/user/register";
        myObjOne.addProperty("username", u.getUsername());
        myObjOne.addProperty("password", u.getPassword());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
            userCookie.setFullCookie(myResponse.getCookie());
            userCookie.getPlayerId();
            userCookie.getDecode();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new InvalidUserException("Server hates you :)");
        }
    }

    @Override
    public List<GameInfo> gamesList() {
        String url = "/games/list";
        List<GameInfo> games = new ArrayList<>();
        HttpURLResponse myResponse;
        try {
            myResponse = doGet(url);
            GameListDeserialize listDeserialize = new GameListDeserialize(myResponse.getResponseBody());
            games = listDeserialize.deserialize();
            //This is when i am going to create the deSerialization later
        } catch (ClientException e) {
            e.printStackTrace();
        }


        return games;
    }

    /**
     * {
     * "randomTiles": "boolean",
     * "randomNumbers": "boolean",
     * "randomPorts": "boolean",
     * "name": "string"
     * }
     *
     * @param gameName - The name of the game
     * @throws FailedCreateGameException
     */
    @Override
    public void gamesCreate(CreateGamePassObject gameName) throws FailedCreateGameException {
        JsonObject myObjOne = new JsonObject();
        String url = "/games/create";
        myObjOne.addProperty("randomTiles", gameName.isRandomTiles());
        myObjOne.addProperty("randomNumbers", gameName.isRandomNumbers());
        myObjOne.addProperty("randomPorts", gameName.isRandomPorts());
        myObjOne.addProperty("name", gameName.getGameName());
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
            //This is when i am going to create the deSerialization later
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gamesJoin(String color, int gameId) throws InvalidUserException {
        JsonObject myObjOne = new JsonObject();
        String url = "/games/join";
        myObjOne.addProperty("id", "" + gameId);
        myObjOne.addProperty("color", color.toLowerCase());
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
            gameCookie.setFullCookie(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gamesSave() {

    }

    @Override
    public void gamesLoad() {

    }

    @Override
    public GameModel getGameModel() {
        String url = "/game/model";
        HttpURLResponse myResponse;
        GameModel gm = null;
        try {
            myResponse = doGet(url);
            gm = myDeSer.deserialize(myResponse.getResponseBody(), myGameModel);
            // System.out.println("\n Heyo!!\n" + myGameModel.toString() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (gm != null)
            myGameModel = gm;
        return gm;
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
    public boolean gameAddAI()
    {
        StopWatch myWatch = new StopWatch();
        JsonObject myObjOne = new JsonObject();
        String url = "/game/addAI";
        myObjOne.addProperty("AIType", "LARGEST_ARMY");
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myWatch.start();
            myResponse = doPost(url, myObjOne);
            myWatch.stop();
            System.out.println("Took this long to do POST = " + myWatch.getElapsedTime());
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String[] gameListAI() {
        return new String[0];
    }

    @Override
    public void sendChat(String content, int playerIndex) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/sendChat";
        myObjOne.addProperty("type", "sendChat");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("content", "" + content);
 //       System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
 //           System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollNumber(int numRoled, int playerIndex) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/rollNumber";
        myObjOne.addProperty("type", "rollNumber");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("number", "" + numRoled);
   //     System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void robPlayer(int playerIndexOne, int playerIndexTwo, HexLocation hl) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/robPlayer";
        myObjOne.addProperty("type", "robPlayer");
        myObjOne.addProperty("playerIndex", playerIndexOne);
        myObjOne.addProperty("victimIndex", playerIndexTwo);
        myObjOne.add("location", locationObject(hl));
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finishTurn(int playerIndex) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/finishTurn";
        myObjOne.addProperty("type", "finishTurn");
        myObjOne.addProperty("playerIndex", playerIndex);
        System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buyDevCard(int playerIndex) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/buyDevCard";
        myObjOne.addProperty("type", "buyDevCard");
        myObjOne.addProperty("playerIndex", playerIndex);
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playYearOfPlenty(int playerIndex, ResourceType r1, ResourceType r2) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/Year_of_Plenty";
        myObjOne.addProperty("type", "Year_of_Plenty");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("resource1", r1.name());
        myObjOne.addProperty("resource2", r2.name());
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playRoadBuilding(int playerIndex, EdgeLocation e1, EdgeLocation e2) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/Road_Building";
        myObjOne.addProperty("type", "Road_Building");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.add("spot1", edgeLocationObject(e1));
        myObjOne.add("spot2", edgeLocationObject(e2));
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playSoldier(int playerIndex, int victimIndex, HexLocation hl) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/Soldier";
        myObjOne.addProperty("type", "Soldier");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("victimIndex", victimIndex);
        myObjOne.add("location", locationObject(hl));
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playMonopoly(int playerIndex, ResourceType r1) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/Monopoly";
        myObjOne.addProperty("type", "Monopoly");
        myObjOne.addProperty("resource", "" + r1.name());
        myObjOne.addProperty("playerIndex", playerIndex);
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void placeMonument(int playerIndex) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/Monument";
        myObjOne.addProperty("type", "Monument");
        myObjOne.addProperty("playerIndex", playerIndex);
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildRoad(int playerIndex, EdgeLocation el, boolean free) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/buildRoad";
        myObjOne.addProperty("type", "buildRoad");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.add("roadLocation", edgeLocationObject(el));
        myObjOne.addProperty("free", "" + free);
     //   System.out.println(myObjOne.toString());
        //
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildSettlement(int playerIndex, VertexLocation vl, boolean free) throws IllegalBuildException {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/buildSettlement";
        myObjOne.addProperty("type", "buildSettlement");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.add("vertexLocation", vertexLocationObject(vl));
        myObjOne.addProperty("free", free);
        System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildCity(int playerIndex, VertexLocation vl) throws IllegalBuildException {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/buildCity";
        myObjOne.addProperty("type", "buildCity");
        myObjOne.addProperty("playerIndex", "" + playerIndex);
        myObjOne.add("vertexLocation", vertexLocationObject(vl));
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
      //      System.out.println(myResponse.getResponseBody());
      //      System.out.println(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void offerTrade(int playerIndexOne, int playerIndexTwo, ResourceList rl) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/offerTrade";
        myObjOne.addProperty("type", "offerTrade");
        myObjOne.addProperty("playerIndex",playerIndexOne);
        myObjOne.add("offer", RLO(rl));
        myObjOne.addProperty("receiver",playerIndexTwo);
//        System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
//            System.out.println(myResponse.getResponseBody());
//            System.out.println(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptTrade(int playerIndex, boolean accept) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/acceptTrade";
        myObjOne.addProperty("type", "acceptTrade");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("willAccept", accept);
   //     System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
    //        System.out.println(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void maritimeTrade(int playerIndex, int ratio, ResourceType in, ResourceType out) {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/maritimeTrade";
        myObjOne.addProperty("type", "maritimeTrade");
        myObjOne.addProperty("playerIndex", playerIndex);
        myObjOne.addProperty("ratio", ratio);
        myObjOne.addProperty("inputResource", in.name());
        myObjOne.addProperty("outputResource", out.name());
    //    System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
    //        System.out.println(myResponse.getResponseBody());
    //        System.out.println(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void discardCards(int playerIndex, ResourceList rl) throws InsufficientResourcesException {
        JsonObject myObjOne = new JsonObject();
        String url = "/moves/discardCards";
        myObjOne.addProperty("type", "discardCards");
        myObjOne.addProperty("playerIndex", "" + playerIndex);
        JsonObject RLO = RLO(rl);
        myObjOne.add("dicardedCards", RLO);
     //   System.out.println(myObjOne.toString());
        HttpURLResponse myResponse;
        try {
            myResponse = doPost(url, myObjOne);
     //       System.out.println(myResponse.getResponseBody());
     //       System.out.println(myResponse.getCookie());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //resourcelistobject
    public JsonObject RLO(ResourceList rl) {
        JsonObject RLO = new JsonObject();
        RLO.addProperty("brick", "" + rl.getBrick());
        RLO.addProperty("ore", "" + rl.getOre());
        RLO.addProperty("sheep", "" + rl.getSheep());
        RLO.addProperty("wheat", "" + rl.getWheat());
        RLO.addProperty("wood", "" + rl.getWood());
        return RLO;
    }

    public JsonObject edgeLocationObject(EdgeLocation el) {
        JsonObject LO = new JsonObject();
        LO.addProperty("x", "" + el.getHexLoc().getX());
        LO.addProperty("y", "" + el.getHexLoc().getY());
        LO.addProperty("direction", el.getDir().toString());
        return LO;
    }

    //locationobject
    public JsonObject vertexLocationObject(VertexLocation vl) {
        JsonObject LO = new JsonObject();
        LO.addProperty("x", "" + vl.getHexLoc().getX());
        LO.addProperty("y", "" + vl.getHexLoc().getY());
        LO.addProperty("direction", vl.getDir().toString());
        return LO;
    }

    public JsonObject locationObject(HexLocation hl) {
        JsonObject LO = new JsonObject();
        LO.addProperty("x", "" + hl.getX());
        LO.addProperty("y", "" + hl.getY());
        return LO;
    }
}
