package server.servermain.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.commandfactories.GamesFactory;
import server.commandfactories.MovesFactory;
import server.commandfactories.UserFactory;
import server.commandobjects.ICommand;
import server.commandobjects.game.ListAI;
import server.serverfacade.ServerFacade;
import server.servermain.Exceptions.ServerException;
import server.servermain.JsonConstructionInfo;
import server.servermain.Server;
import server.shared.CommandType;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Joshua on 3/9/2016.
 */
public class Handler implements HttpHandler
{
    UserFactory userFactory;
    GamesFactory gamesFactory;
    MovesFactory movesFactory;

    public Handler()
    {
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

        try
        {
            if ("GET".equals(method))
                exchange = Get(exchange);

            if (path.contains("/moves"))
                MoveMethod(exchange);
            else if (path.contains("/user"))
                UserMethod(exchange);
            else
                GameMethod(exchange);
        }
        catch(ServerException e)
        {
            //return 400 error
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        }
        catch(IOException e)
        {
            exchange.sendResponseHeaders(400, -1);
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.getResponseBody().close();
        }
    }


    /**
     * This method will determine whether this is a game/model or games/list method and will
     * set the exchange in preperation to send it back
     * @param exchange - incoming request passed by the handle method
     */
    public HttpExchange Get(HttpExchange exchange) throws ServerException, IOException
    {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        String JSonString = "";
        if(path.contains("games/list"))
            JSonString = ServerFacade.getInstance().getGamesList();
        else if(path.contains("game/model"))
            JSonString = ServerFacade.getInstance().getGameModel();
        else
            throw new ServerException("Not a valid get request");


        exchange.sendResponseHeaders(200, 0);
//        xmlStream.toXML(result, exchange.getResponseBody());
        exchange.getResponseBody().write(JSonString.getBytes());
        exchange.getResponseBody().close();

        return exchange;
    }

    /**
     * This method will determine whether this is a user/login or user/register method before
     * passing the JSON information to the commandfactory to be processed
     * @param exchange - incoming request passed by the handle method
     */
    public void UserMethod(HttpExchange exchange) throws ServerException
    {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        if(path.contains("user/login"))
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.login, exchange.getRequestBody().toString()));
        else if(path.contains("user/register"))
            current = userFactory.getCommand(new JsonConstructionInfo(CommandType.register, exchange.getRequestBody().toString()));
        else
            throw new ServerException("Not a valid get request");

        current.execute();
    }

    /**
     * This method will handle games Post methods that will be implemented.
     * It will pass the JSON information to the commandfactory to be processed
     * @param exchange - incoming request passed by the handle method
     */
    public void GameMethod(HttpExchange exchange) throws ServerException
    {
        ICommand current;
        String path = exchange.getRequestURI().getPath();
        if(path.contains("games/create"))
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.create, exchange.getRequestBody().toString()));
        else if(path.contains("games/join"))
            current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, exchange.getRequestBody().toString()));
        else
            throw new ServerException("Not a valid get request");

        current.execute();
    }

    /**
     * This method will parse the RequestURI and send the necessary information to the
     * commandfactory to be made into commandObjects
     * @param exchange - incoming request passed by the handle method
     */
    public void MoveMethod(HttpExchange exchange) throws ServerException
    {
        String path = exchange.getRequestURI().getPath();
        String[] tokens = path.split("/"); //split the URL path into tokens
        CommandType type = CommandType.convert(tokens[tokens.length - 1]); //convert the final token into a CommandType

        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(type, exchange.getRequestBody().toString()));

        if(type == CommandType.listAI)
            throw new ServerException("Unknown Command Type");

        current.execute();
    }
}

//reference
//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.HttpHandler
//Example 3
