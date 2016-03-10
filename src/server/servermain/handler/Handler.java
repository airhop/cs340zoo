//Do we want to throw an exception when something goes wrong as well as
//send back an error code for the server to deal with?

package server.servermain.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by Joshua on 3/9/2016.
 */
public class Handler implements HttpHandler
{

    /**
     * This method will grab the initial incoming exchange and parse the incoming Request Method
     * If it is a GET request it will pass it to the GetMethod.  Otherwise it will parse the RequestURI
     * and pass it to the User, Game, or Moves method
     * @param exchange
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException //probably change to be a ServerException
    {

    }

    /**
     * This method will determine whether this is a game/model or games/list method and will
     * set the exchange in preperation to send it back
     * @param exchange
     */
    public void Get(HttpExchange exchange)
    {

    }

    /**
     * This method will determine whether this is a user/login or user/register method before
     * passing the JSON information to the commandfactory to be processed
     * @param exchange
     */
    public void UserMethod(HttpExchange exchange)
    {

    }

    /**
     * This method will handle game and games methods that will be implemented.
     * It will pass the JSON information to the commandfactory to be processed
     * @param exchange
     */
    public void GameMethod(HttpExchange exchange)
    {

    }

    /**
     * This method will parse the RequestURI and send the necessary information to the
     * commandfactory to be made into commandObjects
     * @param exchange
     */
    public void MoveMethod(HttpExchange exchange)
    {

    }
}

//reference
//http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.HttpHandler
