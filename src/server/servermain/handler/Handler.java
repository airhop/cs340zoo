//GameModel needs a to JSON string method

package server.servermain.handler;

import client.MVC.data.GameInfo;
import client.model.GameModel;
import client.model.map.Hex;
import client.model.map.Map;
import client.proxy.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.factories.GamesFactory;
import server.factories.MovesFactory;
import server.factories.UserFactory;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.servermain.exceptions.ServerException;
import server.servermain.JsonConstructionInfo;
import server.shared.CommandType;
import shared.jsonobject.CreatedGame;
import shared.jsonobject.Login;
import shared.locations.HexLocation;
import shared.serialization.GameListDeserialize;
import shared.serialization.MapSerializer;

import java.lang.reflect.Type;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Joshua on 3/9/2016.
 */
public class Handler implements HttpHandler {
    private UserFactory userFactory;
    private GamesFactory gamesFactory;
    private MovesFactory movesFactory;
    private String requestBody;
    private Cookie userCookie;
    private Cookie gameCookie;

    public Handler() {
        userFactory = new UserFactory();
        gamesFactory = new GamesFactory();
        movesFactory = new MovesFactory();
    }

    /**
     * This method will grab the initial incoming exchange and parse the incoming Request Method
     * If it is a GET request it will pass it to the GetMethod.  Otherwise it will parse the RequestURI
     * and pass it to the User, Game, or Moves method
     *
     * @param exchange - incoming request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException //probably change to be a ServerException
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        Scanner scan = new Scanner(exchange.getRequestBody());
        requestBody = "";
        while (scan.hasNext())
            requestBody += scan.nextLine();

        Headers test = exchange.getRequestHeaders();

        try {
            //Get doesn't work so I moved game/model to another method and games/list to gamemethod
            System.out.println("Method " + method);
            //if ("GET".equals(method))
            //    Get(exchange);


            userCookie = new Cookie();
            gameCookie = new Cookie();
            Headers reqHeaders = exchange.getRequestHeaders();
            List<String> rh = reqHeaders.get("Cookie");
            if (rh != null) {
                scan = new Scanner(rh.get(0));
                if (scan.hasNext())
                    userCookie = new Cookie(scan.next(), scan.next(), scan.next());
                if (scan.hasNext())
                    gameCookie = new Cookie(Integer.parseInt(scan.next()));
            }
            ServerFacade.getInstance().buildCurrentPlayer(userCookie, gameCookie);

            if (path.contains("model")){
                Get(exchange);
                return;
            } else if (path.contains("/user")) {
                UserMethod(exchange);
                return;
            }


            if (userCookie.isActive()) {
                if (path.contains("/game"))
                    GameMethod(exchange);
                else if (gameCookie.isActive()) {
                    if (path.contains("/moves"))
                        MoveMethod(exchange);
                } else
                    throw new ServerException("Not joined a game yet . . .");
            } else
                throw new ServerException("Not logged in yet...");

        } catch (ServerException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        }

    }


    /**
     * This method will determine whether this is a game/model or games/list method and will
     * set the exchange in preperation to send it back
     *
     * @param exchange - incoming request passed by the handle method
     */
    public void Get(HttpExchange exchange) throws ServerException, IOException {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        System.out.println("Path " + path);
        String info;
        if (path.contains("games/list")) {
            List<GameInfo> gameInfo = ServerFacade.getInstance().getGamesList();
            Type listOfTestObject = new TypeToken<ArrayList<GameInfo>>() {}.getType();
            info = new com.google.gson.Gson().toJson(gameInfo, listOfTestObject);
            System.out.println("Game Info . .  ." + gameInfo.size() + " " + gameInfo.get(0).toString());
        } else if (path.contains("game/model")) {
            GameModel gm = ServerFacade.getInstance().getModel();
            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
//            private class DateTimeSerializer implements JsonSerializer<DateTime> {
//                public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
//                    return new JsonPrimitive(src.toString());
//                }
//            }
//            gson.registerTypeAdapter(TreeMap.class, new MapSerializer());
            info = gson.create().toJson(gm);
        } else
            throw new ServerException("Not a valid get request + " + path);


        ArrayList<String> content = new ArrayList<String>();
        content.add("application/json");
        exchange.getResponseHeaders().put("Content-Type", content);
        exchange.sendResponseHeaders(200, info.length());
        exchange.getResponseBody().write(info.getBytes());
        exchange.getResponseBody().close();

    }

    /**
     * This method will determine whether this is a user/login or user/register method before
     * passing the JSON information to the commandfactory to be processed
     *
     * @param exchange - incoming request passed by the handle method
     */
    public void UserMethod(HttpExchange exchange) throws ServerException, IOException {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        System.out.println("User Method " + path + " " + path.contains("/login"));
        if (path.contains("/login"))
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.login, requestBody));
        else if (path.contains("/register"))
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.register, requestBody));
        else
            throw new ServerException("Not a valid user request");

        Object o = current.execute();
        Login login = (Login) o;

        System.out.println("LoginObject " + login.toString());
        if (login.getID() == -1) {
            throw new ServerException("False user");
        }

        userCookie = new Cookie(login);
        ArrayList<String> cookies = new ArrayList<String>();
        System.out.println("userCookie.toString() = " + userCookie.toString());
        cookies.add(userCookie.toString());
        exchange.getResponseHeaders().put("Set-Cookie", cookies);
        cookies = new ArrayList<String>();
        cookies.add("text/html");
        exchange.getResponseHeaders().put("Content-Type", cookies);
        String success = "success";
        exchange.sendResponseHeaders(200, success.length());

        System.out.println("Heyo!");
        OutputStream out = exchange.getResponseBody();
        out.write(success.getBytes());
        out.flush();

        System.out.println("Response Body = " + exchange.getResponseBody());
        exchange.getResponseBody().close();

    }

    /**
     * This method will handle games Post methods that will be implemented.
     * It will pass the JSON information to the commandfactory to be processed
     *
     * @param exchange - incoming request passed by the handle method
     */
    public void GameMethod(HttpExchange exchange) throws ServerException, IOException {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        System.out.println("Path " + path);

        if (path.contains("games/create")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.create, requestBody));
            CreatedGame cg = ((CreatedGame) current.execute());
            String info = new com.google.gson.Gson().toJson(cg);
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        } else if (path.contains("games/join")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, requestBody));
            Object o = current.execute();

            gameCookie = new Cookie(((CreatedGame) o).getId());
            ArrayList<String> cookies = new ArrayList<String>();
            System.out.println("gameCookie.toString() " + gameCookie.toString());
            cookies.add(gameCookie.toString());
            exchange.getResponseHeaders().put("Set-Cookie", cookies);
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        } else if (path.contains("games/list")) {
            List<GameInfo> gameInfo = ServerFacade.getInstance().getGamesList();
            String info = new com.google.gson.Gson().toJson(gameInfo);
//            System.out.println("Game Info . .  ." + gameInfo.size() + " " + gameInfo.get(0).toString());
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        } else
            throw new ServerException("Not a valid game request");

        ArrayList<String> content = new ArrayList<String>();
        content.add("application/json");
        exchange.getResponseHeaders().put("Content-Type", content);

    }

    /**
     * This method will parse the RequestURI and send the necessary information to the
     * commandfactory to be made into commandObjects
     *
     * @param exchange - incoming request passed by the handle method
     */
    public void MoveMethod(HttpExchange exchange) throws ServerException, IOException {
        String path = exchange.getRequestURI().getPath();
        String[] tokens = path.split("/"); //split the URL path into tokens
        CommandType type = CommandType.convert(tokens[tokens.length - 1]); //convert the final token into a CommandType

        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(type, requestBody));

        if (type == CommandType.listAI)
            throw new ServerException("Unknown Command Type");

        Object o = current.execute();


        ArrayList<String> content = new ArrayList<String>();
        content.add("application/json");
        exchange.getResponseHeaders().put("Content-Type", content);
        //if current doesn't return anything
        String info = ((GameModel) o).toString();

        exchange.sendResponseHeaders(200, info.length());
        exchange.getResponseBody().write(info.getBytes());
        exchange.getResponseBody().close();
    }

}

//reference
//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.HttpHandler
//Example 3
