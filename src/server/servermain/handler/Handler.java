//GameModel needs a to JSON string method

package server.servermain.handler;

import client.MVC.data.GameInfo;
import client.model.GameModel;
import client.model.player.Player;
import client.proxy.Cookie;
import com.google.gson.*;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.ai.AITypes;
import server.factories.GamesFactory;
import server.factories.MovesFactory;
import server.factories.UserFactory;
import server.commandobjects.ICommand;
import server.plugincode.iplugin.IPersistencePlugin;
import server.serverfacade.ServerFacade;
import server.servermain.exceptions.ServerException;
import server.servermain.JsonConstructionInfo;
import server.shared.CommandType;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.CreatedGame;
import shared.jsonobject.Login;

import java.lang.reflect.Type;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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
    private boolean swagger = false;
    private boolean duplicate;  //big bandaid . . .
    private IPersistencePlugin IPP;
    private int commandsBeforeStorage;
    //First int is game model id, second is number of times a command has been done on that game
    private HashMap<Integer, Integer> gameUpdated = new HashMap<Integer, Integer>();
    public Handler(IPersistencePlugin ipp, int runs)
    {
        userFactory = new UserFactory();
        gamesFactory = new GamesFactory();
        movesFactory = new MovesFactory();
        IPP = ipp;
        commandsBeforeStorage = runs;
        duplicate = false;
        loadIntoServerFacade();
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
            System.out.println("Method " + method + " Path = " + path);
            //if ("GET".equals(method))
            //    Get(exchange);


                userCookie = new Cookie();
                gameCookie = new Cookie();
            try {
                Headers reqHeaders = exchange.getRequestHeaders();
                List<String> rh = reqHeaders.get("Cookie");
                if (rh != null) {
                 //   System.out.println("Cookie string " + rh.get(0));
                    scan = new Scanner(rh.get(0));
                    String first = scan.next();
                    if(first.length() > 10)
                    {
//                        System.out.println("in here?");
                        swagger = true;
                        //first = first.substring(11);
                        userCookie.createCookie(first);
//                        System.out.println("To stringed cookie! " + userCookie.toString());
                        if(scan.hasNext())
                            gameCookie.setFullCookie(scan.next());
                    }
                    else
                    {
                       // System.out.println("Why am I here?!");
                        swagger = false;
                        userCookie = new Cookie(first, scan.next(), scan.next());
                        if (scan.hasNext())
                        {
                            String game = scan.next();
                            if(game.length() > 2)
                            {
                                System.out.println("Stupid Swagger . . ."); // Swagger is giving me issues . . .
                                swagger = true;
                            }
                            else
                                gameCookie = new Cookie(Integer.parseInt(game));
                        }
                    }
                }
            } catch (NumberFormatException e)
            {
                System.out.println("Stupid Swagger . . .");
            }
            ServerFacade.getInstance().buildCurrentPlayer(userCookie, gameCookie);

            if (path.contains("model")) {
                Get(exchange);
                return;
            } else if (path.contains("/user")) {
                UserMethod(exchange);
                return;
            }


            if (userCookie.isActive()) {
                duplicate = false;  //big bandaid having to do with users . . .
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
            info = gson.create().toJson(gm);
            System.out.println(info);
//            private class DateTimeSerializer implements JsonSerializer<DateTime> {
//                public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
//                    return new JsonPrimitive(src.toString());
//                }
//            }
//            gson.registerTypeAdapter(TreeMap.class, new MapSerializer());
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
        Login login;
        String path = exchange.getRequestURI().getPath();
//        System.out.println("User Method " + path + " " + path.contains("/login"));
        if(duplicate)
            path = "/login";
//        System.out.println("path again " + path + " " + duplicate);
        if (path.contains("/login"))
        {
            duplicate = false;
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.login, requestBody));
            login = (Login) current.execute();
        }
        else if (path.contains("/register"))
        {
//            System.out.println("in here!");
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.register, requestBody));
            login = (Login)current.execute();
            if(login.getID() != -1)
            {
                duplicate = true;
                IPP.startTransaction();
                IPP.getPlayerDAO().addPlayer(login);
                IPP.endTransaction(true);
            }
        }
        else
            throw new ServerException("Not a valid user request");

        System.out.println("LoginObject " + login.toString());
        if (login.getID() == -1) {
            throw new ServerException("False user");
        }

        userCookie = new Cookie(login);
        ArrayList<String> cookies = new ArrayList<String>();
        System.out.println("userCookie.toString() = " + userCookie.toString());
        if(swagger)
        {
            cookies.add(userCookie.encode());

            //cookies.add(userCookie.toString());
            exchange.getResponseHeaders().put("Cookie", cookies);
            exchange.getResponseHeaders().put("Set-Cookie", cookies);
        }
        else
        {
            cookies.add(userCookie.toString());
            exchange.getResponseHeaders().put("Set-Cookie", cookies);
        }
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
            IPP.startTransaction();
            IPP.getGameDAO().addGame(new GameModel(cg.getTitle()), cg.getId());
            IPP.endTransaction(true);
            gameUpdated.put(cg.getId(), 0);
        } else if (path.contains("games/join")) {
            if(gameCookie.isActive())
            {
                System.out.println("oops");
                ArrayList<String> cookies = new ArrayList<String>();
                cookies.add(gameCookie.toString());
                exchange.getResponseHeaders().put("Set-Cookie", cookies);
                exchange.sendResponseHeaders(200, 0);
                exchange.getResponseBody().close();
                return;
            }
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, requestBody));
            Object o = current.execute();

            int gameId = ((CreatedGame)o).getId();
            IPP.startTransaction();
            if(IPP.getGameDAO().readGame(gameId) == null)
            {
                System.out.println("in here");
                int i = (((CreatedGame)o).getId());
                IPP.getGameDAO().deleteGame((i+1)*-1);
                GameModel last = ServerFacade.getInstance().retrieveFinal();
                IPP.getGameDAO().addGame(last, last.getID());
                gameId = last.getID();
            }
            IPP.endTransaction(true);

            gameCookie = new Cookie(gameId);
            ArrayList<String> cookies = new ArrayList<String>();
//            System.out.println("gameCookie.toString() " + gameCookie.toString());
            cookies.add(gameCookie.toString());
            exchange.getResponseHeaders().put("Set-Cookie", cookies);
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
//            System.out.println(ServerFacade.getInstance().getModel(gameId));
            IPP.startTransaction();
            IPP.getGameDAO().updateGame(((CreatedGame)o).getId(), ServerFacade.getInstance().getModel(gameId));
            IPP.getCommandDAO().clearGame(((CreatedGame)o).getId());
            IPP.endTransaction(true);

            System.out.println("Finish Game Join " + gameId + " " + gameCookie.toString());

            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
            System.out.println(gson.create().toJson(ServerFacade.getInstance().getModel(gameId)));

        } else if (path.contains("games/list")) {
            List<GameInfo> gameInfo = ServerFacade.getInstance().getGamesList();
            String info = new com.google.gson.Gson().toJson(gameInfo);
            System.out.println("Game Info Here . .  ." + gameInfo.size() + " " + gameInfo.get(0).toString());
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        } else if(path.contains("/game/listAI")){
            List<String> aiList = ServerFacade.getInstance().listAI();
            String info = new com.google.gson.Gson().toJson(aiList);
//            System.out.println("Game Info . .  ." + gameInfo.size() + " " + gameInfo.get(0).toString());
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        } else if(path.contains("/game/addAI")){
            ServerFacade.getInstance().addAI(AITypes.LONGESTROAD.toString());
//            System.out.println("Game Info . .  ." + gameInfo.size() + " " + gameInfo.get(0).toString());
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        } else if(path.contains("save")){
            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();

            JsonParser myParse = new JsonParser();
            JsonElement myEle = myParse.parse(requestBody.toString());
            JsonTreeReader myTree = new JsonTreeReader(myEle);
            try {
                myTree.beginObject();
                myTree.nextName();
                String gsonedGame = myTree.nextString();
                GameModel gm = gson.create().fromJson(gsonedGame, GameModel.class);
                int id = ServerFacade.getInstance().saveGame(gm);
                System.out.println("Id " + id);
                exchange.sendResponseHeaders(200, "success".length());
                exchange.getResponseBody().write("success".getBytes());
                exchange.getResponseBody().close();
                gm.setID(id);
                IPP.startTransaction();
                IPP.getGameDAO().addGame(gm, id);
                IPP.endTransaction(true);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        } else {
            throw new ServerException("Not a valid game request");
        }


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

        if (type == CommandType.listAI)
            throw new ServerException("Unknown Command Type");

        System.out.println(tokens[tokens.length - 1]);
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(type, requestBody));

        IPP.startTransaction();
        IPP.getCommandDAO().addCommand(current, ServerFacade.getInstance().getModel().getID());
        IPP.endTransaction(true);
        int currentGameID = ServerFacade.getInstance().getModel().getID();
        if (gameUpdated.get(currentGameID) == commandsBeforeStorage) {
            System.out.println(ServerFacade.getInstance().getModel());
            IPP.startTransaction();
            IPP.getGameDAO().updateGame(currentGameID, ServerFacade.getInstance().getModel());
            IPP.endTransaction(true);
            gameUpdated.put(currentGameID, 0);
        } else {
            gameUpdated.put(currentGameID, gameUpdated.get(currentGameID) + 1);
        }

        System.out.println("here!");
        Object o = current.execute();


        ArrayList<String> content = new ArrayList<String>();
        content.add("application/json");
        exchange.getResponseHeaders().put("Content-Type", content);
        //if current doesn't return anything

        GameModel gm = (GameModel) o;
        GsonBuilder gson = new GsonBuilder();
        gson.enableComplexMapKeySerialization();
        String info = gson.create().toJson(gm);

        System.out.println("still here!");
        exchange.sendResponseHeaders(200, info.length());
        exchange.getResponseBody().write(info.getBytes());
        exchange.getResponseBody().close();
    }

    public void loadIntoServerFacade() {
        IPP.startTransaction();
        List<Login> players = IPP.getPlayerDAO().readAllPlayers();
        List<GameModel> games = IPP.getGameDAO().readAllGames();

        if (games.size() == 0 || players.size() == 0) {
            TreeMap<String, Login> newPlayers = ServerFacade.getInstance().getPlayers();
            List<GameModel> newGames = ServerFacade.getInstance().getGameInfoList();
            IGameDAO game = IPP.getGameDAO();
            IPlayerDAO player = IPP.getPlayerDAO();
            for (GameModel g : newGames) {
                game.addGame(g, g.getID());
            }
            for (String s : newPlayers.keySet()) {
                player.addPlayer(newPlayers.get(s));
            }
        }

        players = IPP.getPlayerDAO().readAllPlayers();
        games = IPP.getGameDAO().readAllGames();
        IPP.endTransaction(true);

        List<GameModel> saved = new ArrayList<GameModel>();
        List<GameModel> normal = new ArrayList<GameModel>();

        for(GameModel model: games)
        {
            if(model.getID() < 0)
                saved.add(model);
            else
                normal.add(model);
        }
        ServerFacade.getInstance().loadInData(players, normal, saved);

        for (GameModel model : games) {
            if(model.getID() >= 0)
            {
                IPP.startTransaction();
                List<ICommand> commands = IPP.getCommandDAO().readAllCommands(model.getID());
                IPP.endTransaction(true);
                if(commands.size() != 0) {
                    updateGameModel(model.getID());
                    gameUpdated.put(model.getID(), 0);
                }
                else
                    gameUpdated.put(model.getID(), 0);
            }
        }

    }


    public void updateGameModel(int gameID)
    {
        GameModel current = ServerFacade.getInstance().getModel(gameID);
        Cookie gc = new Cookie(gameID);
        List<Player> players = current.getPlayers();

        IPP.startTransaction();
        List<ICommand> commands = IPP.getCommandDAO().readAllCommands(gameID);
        IPP.endTransaction(true);

        for(int i = 0; i < commands.size(); i++)
        {
            ICommand command = commands.get(i);
            int pid = command.getPID();
            Cookie uc = new Cookie(players.get(pid).getUsername(), players.get(pid).getPassword(), Integer.toString(pid));
            ServerFacade.getInstance().buildCurrentPlayer(uc, gc);
            command.execute();
        }

        IPP.startTransaction();
        IPP.getGameDAO().updateGame(gameID, ServerFacade.getInstance().getModel(gameID));
        IPP.getCommandDAO().clearGame(gameID);
        IPP.endTransaction(true);
    }

}

//reference
//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.HttpHandler
//Example 3
