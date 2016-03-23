//GameModel needs a to JSON string method

package server.servermain.handler;

import client.MVC.data.GameInfo;
import client.model.GameModel;
import client.proxy.Cookie;
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
import shared.serialization.HttpURLResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Joshua on 3/9/2016.
 */
public class Handler implements HttpHandler {
    private UserFactory userFactory;
    private GamesFactory gamesFactory;
    private MovesFactory movesFactory;
    private String requestBody;
    private static Cookie Usercookie;
    private static Cookie Gamecookie;

    public Handler() {
        userFactory = new UserFactory();
        gamesFactory = new GamesFactory();
        movesFactory = new MovesFactory();
    }

    /**
     * This method will grab the initial incoming exchange and parse the incoming Request Method
     * If it is a GET request it will pass it to the GetMethod.  Otherwise it will parse the RequestURI
     * and pass it to the User, Game, or Moves method
     * @param exchange - incoming request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException //probably change to be a ServerException
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        Usercookie = new Cookie();
        Gamecookie = new Cookie();
        Headers reqHeaders = exchange.getRequestHeaders();
        List<String> rh = reqHeaders.get("Cookie");
        if(rh.size() > 0)
        {
            Scanner scan = new Scanner(rh.get(0));
            Usercookie = new Cookie(scan.next(), scan.next(), scan.next());
            if(rh.size() > 1)
                Gamecookie = new Cookie(Integer.parseInt(rh.get(1)));
        }
        ServerFacade.getInstance().buildCurrentPlayer(Usercookie, Gamecookie);

        Scanner scan = new Scanner(exchange.getRequestBody());
        requestBody = "";
        while(scan.hasNext())
            requestBody += scan.nextLine();

        Headers test = exchange.getRequestHeaders();
//        Object something = exchange.getAttribute("Cookie");
//        System.out.println("Something  = " + something.getClass() + " " + something.toString());

        try {
            //Get doesn't work so I moves game/model to another method and games/list to gamemethod
            System.out.println("Method " + method);
            //if ("GET".equals(method))
            //    Get(exchange);
            if(path.contains("model"))
                Get(exchange);
//            System.out.println(path);
            else if (path.contains("/user"))
                UserMethod(exchange);
            else if (Usercookie.isActive()) {
                if (path.contains("/game"))
                    GameMethod(exchange);
                else if (Gamecookie.isActive()) {
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

        System.out.println("Exchange response Body " + exchange.getResponseBody().toString().length());
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
            info = new com.google.gson.Gson().toJson(gameInfo);
        } else if (path.contains("game/model")) {
            GameModel gm = ServerFacade.getInstance().getModel();
            info = new com.google.gson.Gson().toJson(gm);
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
        if(login.getID() == -1){
            throw new ServerException("False user");
        }

        Usercookie = new Cookie(login);
        ArrayList<String> cookies = new ArrayList<String>();
        System.out.println("Usercookie.toString() = " + Usercookie.toString());
        cookies.add(Usercookie.toString());
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

            InputStream is = exchange.getRequestBody();
            int i = 0;
            String input = "";
            while((i = is.read()) != -1)
                input += (char)i;

            System.out.println(input);
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.create, requestBody));
//     System.out.println("success in getting to creation " + exchange.getRequestBody().toString());
            Object o = current.execute();
//     System.out.println("success in getting to creation");
            CreatedGame cg = ((CreatedGame)current.execute());
            String info = new com.google.gson.Gson().toJson(cg);
//     System.out.println("Game created = " + info);
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();

        }
        else if (path.contains("games/join")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, requestBody));
            Object o = current.execute();

            Gamecookie = new Cookie(((CreatedGame)o).getId());
            ArrayList<String> cookies = new ArrayList<String>();
//            System.out.println("Gamecookie.toString() " + Gamecookie.toString());
            cookies.add(Gamecookie.toString());
            exchange.getResponseHeaders().put("Set-Cookie", cookies);

            exchange.sendResponseHeaders(200, 1);
            exchange.getResponseBody().write(((int) o));
            exchange.getResponseBody().close();
        }
        else if (path.contains("games/list")) {
            List<GameInfo> gameInfo = ServerFacade.getInstance().getGamesList();
            String info = new com.google.gson.Gson().toJson(gameInfo);
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        }
        else
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
        String info = ((GameModel)o).toString();
        exchange.sendResponseHeaders(200, info.length());
        exchange.getResponseBody().write(info.getBytes());
        exchange.getResponseBody().close();
    }

    public static Cookie getUserCookie() {
        return Usercookie;
    }

    public static Cookie getGamecookie() {
        return Gamecookie;
    }
}

//reference
//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.HttpHandler
//Example 3
