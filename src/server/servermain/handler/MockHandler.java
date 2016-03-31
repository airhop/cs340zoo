package server.servermain.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

//yes very unprofessional and messy, but easiest way to communicate with the mockserverfacade.


public class MockHandler implements HttpHandler {

    public MockHandler()
    {
    }

    public void handle(HttpExchange exchange) throws IOException //probably change to be a ServerException
    {
        String info = "success!";
        exchange.sendResponseHeaders(200, info.length());
        exchange.getResponseBody().write(info.getBytes());
        exchange.getResponseBody().close();
    }
}
