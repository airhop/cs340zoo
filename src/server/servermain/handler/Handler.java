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

import java.io.IOException;
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

        Scanner scan = new Scanner(exchange.getRequestBody());
        requestBody = "";
        while(scan.hasNext())
            requestBody += scan.nextLine();

        Headers test = exchange.getRequestHeaders();

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

        //if cookie == null awesome, else throw serverexception...

        exchange.sendResponseHeaders(200, 1);
        exchange.getResponseBody().write(login.toString().getBytes());
        exchange.getResponseBody().close();

//if successful set-cookie
//if failed, current.execute should throw a ServerException
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
            exchange.sendResponseHeaders(200, 1);
            exchange.getResponseBody().write(((CreatedGame) o).toString().getBytes());
            exchange.getResponseBody().close();
        } else if (path.contains("games/join")) {
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, exchange.getRequestBody().toString()));
            Object o = current.execute();
            exchange.sendResponseHeaders(200, 1);
            exchange.getResponseBody().write(((int) o));
            //don't know if an int will write over correctly
            exchange.getResponseBody().close();
//set cookie for gameID
//and return the Game Model in the ResponseBody
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
        exchange.sendResponseHeaders(200, 1);
        exchange.getResponseBody().write(((GameModel) o).toString().getBytes());
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
