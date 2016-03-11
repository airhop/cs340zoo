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

    /**
     * This will run the server creating the handler
     */
    private void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }


        server.setExecutor(null); // use the default executor

        server.createContext("/DownloadBatchHandler", mainHandler);
//        logger.info("Starting HTTP Server");
        server.start();
    }

    private HttpHandler mainHandler = new Handler();

    /**
     * The main function
     * @param args - args passed to the main
     */
    public static void main(String[] args) {
        new Server().run();
    }
}
