package server.servermain;

import client.proxy.MockFunctionProxy;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import server.plugincode.TextPlugin.TextPersistencePlugin;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.registry.PluginDescriptor;
import server.plugincode.registry.PluginRegistry;
import server.plugincode.sql.SqlPersistencePlugin;
import server.servermain.handler.Handler;
import server.servermain.handler.Handlers;
import server.servermain.handler.MockHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;


/**
 * Created by Joshua on 3/11/2016.
 */
public class Server {
    private static final int SERVER_PORT_NUMBER = 8081;
    private static final int MAX_WAITING_CONNECTIONS = 10;

    private static HttpServer server;

    private Server() {
    }

    private static HttpHandler mainHandler;

    /**
     * This will run the server creating the handler
     */
    public void run(int serverNum) {
        try {
            server = HttpServer.create(new InetSocketAddress(serverNum), MAX_WAITING_CONNECTIONS);
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
        server.createContext("/game/listAI", mainHandler);
        server.createContext("/game/addAI", mainHandler);

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
        server.createContext("/moves/buildSettlement", mainHandler);
        server.createContext("/moves/buildCity", mainHandler);
        server.createContext("/moves/offerTrade", mainHandler);
        server.createContext("/moves/acceptTrade", mainHandler);
        server.createContext("/moves/maritimeTrade", mainHandler);
        server.createContext("/moves/discardCards", mainHandler);

        server.createContext("/docs/api/data", new Handlers.JSONAppender(""));
        server.createContext("/docs/api/view", new Handlers.BasicFile(""));

//        logger.info("Starting HTTP Server");
        server.start();
    }

    public static void kill()
    {
        if(server != null)
            server.stop(1);
    }


    public static void Mockmain()
    {
        mainHandler = new MockHandler();
        new Server().run(SERVER_PORT_NUMBER);
    }
    /**
     * The main function
     * @param args - args passed to the main363
     */
    public static void main(String[] args)
    {
        System.out.println(args.length);
        for(int i = 0; i <args.length; i++)
            System.out.println((args[0]));

        Scanner scan = new Scanner(args[0]);
        String type = scan.next();
        int revisions = scan.nextInt();
        PluginDescriptor plugin = new PluginDescriptor();
        PluginRegistry register = new PluginRegistry();
        register.loadConfig();

        if(type.equals("SQL")){
            plugin.setName("SQL");
            mainHandler = new Handler(register.createPlugin(plugin), revisions);
        }

        if(type.equals("TXT")){
            plugin.setName("TXT");
            mainHandler = new Handler(register.createPlugin(plugin), revisions);
        }
        new Server().run(SERVER_PORT_NUMBER);
//        if(args.length == 1)
//        {
//            mainHandler = new MockHandler();
//            new Server().run(SERVER_PORT_NUMBER);
//        }
//        else {
//            mainHandler = new Handler();
//
//            if (args.length > 0) {
//                new Server().run(Integer.parseInt(args[0]));  //ignore the host :)
//            } else {
//                new Server().run(SERVER_PORT_NUMBER);
//            }
//        }
    }

}
