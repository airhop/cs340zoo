package server.servermain;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import server.servermain.handler.Handler;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Joshua on 3/11/2016.
 */
public class Server {
    private static final int SERVER_PORT_NUMBER = 8081;
    private static final int MAX_WAITING_CONNECTIONS = 10;

    private HttpServer server;

    private Server() {
    }

    private HttpHandler mainHandler = new Handler();

    /**
     * This will run the server creating the handler
     */
    public void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.setExecutor(null); // use the default executor




        server.createContext("/user/login", mainHandler);
        server.createContext("/user/register", mainHandler);

        server.createContext("/games/list", mainHandler);
        server.createContext("/games/create", mainHandler);
        server.createContext("/games/join", mainHandler);
        server.createContext("/games/save", mainHandler);
        server.createContext("/games/load", mainHandler);

        server.createContext("/game/model", mainHandler);

        server.createContext("/moves/sendChat", mainHandler);
        server.createContext("/moves/rollNumber", mainHandler);
        server.createContext("/moves/robPlayer", mainHandler);
        server.createContext("/moves/finishTurn", mainHandler);
        server.createContext("/moves/buyDevCard", mainHandler);
        server.createContext("/moves/Year_of_Plenty", mainHandler);
        server.createContext("/moves/Road_Building", mainHandler);
        server.createContext("/moves/Soldier", mainHandler);
        server.createContext("/moves/Monopoly", mainHandler);
        server.createContext("/moves/Monument", mainHandler);
        server.createContext("/moves/buildRoad", mainHandler);
        server.createContext("/moves/buildsettlement", mainHandler);
        server.createContext("/moves/buildCity", mainHandler);
        server.createContext("/moves/offerTrade", mainHandler);
        server.createContext("/moves/acceptTrade", mainHandler);
        server.createContext("/moves/maritimeTrade", mainHandler);
        server.createContext("/moves/discardCards", mainHandler);


//        logger.info("Starting HTTP Server");
        server.start();
    }



    /**
     * The main function
     * @param args - args passed to the main
     */
    public static void main(String[] args) {
        new Server().run();
    }
}
