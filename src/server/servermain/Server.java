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
    private static final int SERVER_PORT_NUMBER = 8080;
    private static final int MAX_WAITING_CONNECTIONS = 10;

    private HttpServer server;

    private Server() {
        return;
    }

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

    public static void main(String[] args) {
        new Server().run();
    }
}
