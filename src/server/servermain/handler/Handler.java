//GameModel needs a to JSON string method

package server.servermain.handler;

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
import java.io.OutputStream;
import java.util.ArrayList;
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
     *
     * @param exchange - incoming request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException //probably change to be a ServerException
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        Headers reqHeaders = exchange.getRequestHeaders();
         //reqHeaders.get("Cookie");
          //for(String cookie ; cookies) if(!cookie.contains(username)/(gameID)

        Scanner scan = new Scanner(exchange.getRequestBody());
        requestBody = "";
        while(scan.hasNext())
            requestBody += scan.nextLine();

        Headers test = exchange.getRequestHeaders();
//        Object something = exchange.getAttribute("Cookie");
//        System.out.println("Something  = " + something.getClass() + " " + something.toString());

        try {
            //how can I get the cookies from the server facade?
            if ("GET".equals(method))
                Get(exchange);

//            System.out.println(path);
            if (path.contains("/user"))
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
            //return 400 error
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        }

        System.out.println("Exchange response Body " + exchange.getResponseBody().toString());
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
        String JSonString = "";
        if (path.contains("games/list")) {
            JSonString = "";
            ServerFacade.getInstance().getGamesList();
        } else if (path.contains("game/model")) {
            JSonString = "";
            ServerFacade.getInstance().getModel();
        } else
            throw new ServerException("Not a valid get request");


        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write(JSonString.getBytes());
        exchange.getResponseBody().close();

//does exchange need to be returned?
//        return exchange;
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
            throw new ServerException("Not a valid get request");

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
        if (path.contains("games/create")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.create, exchange.getRequestBody().toString()));
            Object o = current.execute();
            String info = ((CreatedGame)o).toString();
            exchange.sendResponseHeaders(200, info.length());
            exchange.getResponseBody().write(info.getBytes());
            exchange.getResponseBody().close();
        } else if (path.contains("games/join")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, exchange.getRequestBody().toString()));
            Object o = current.execute();

            Gamecookie = new Cookie(((CreatedGame)o).getId());
            ArrayList<String> cookies = new ArrayList<String>();
            System.out.println("Gamecookie.toString() " + Gamecookie.toString());
            cookies.add(Gamecookie.toString());
            exchange.getResponseHeaders().put("Set-Cookie", cookies);

            exchange.sendResponseHeaders(200, 1);
            exchange.getResponseBody().write(((int) o));
            exchange.getResponseBody().close();
        } else
            throw new ServerException("Not a valid get request");

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

        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(type, exchange.getRequestBody().toString()));

        if (type == CommandType.listAI)
            throw new ServerException("Unknown Command Type");

        Object o = current.execute();

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
