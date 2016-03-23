package server.servermain.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handlers {
    public abstract static class BaseFile implements HttpHandler{

    public BaseFile(String rootPath) {
        this.rootPath = rootPath;
    }

    protected String rootPath;

    protected String getRequestPath(HttpExchange exchange) {
        return exchange.getRequestURI().getPath().substring(1);
    }

    protected void sendFile(HttpExchange exchange, String filepath) throws IOException {
        try {
            byte[] response = FileUtils.readFile(filepath);
            ArrayList<String> mimetypes = new ArrayList<String>();
            mimetypes.add(FileUtils.getMimeType(filepath));
            exchange.getResponseHeaders().put("Content-type", mimetypes);
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        } catch (IOException ioe) {
            exchange.sendResponseHeaders(404, -1);
            OutputStream os = exchange.getResponseBody();
            os.close();
            ioe.printStackTrace();
        }
    }
}


    public static class BasicFile extends BaseFile{
        public BasicFile(String rootPath){ super(rootPath); }
        public void handle(HttpExchange exchange) throws IOException{
            String filepath = this.rootPath + this.getRequestPath(exchange);
            this.sendFile(exchange, filepath);
        }
    }

    public static class JSONAppender extends BaseFile{
        public JSONAppender(String rootPath){super(rootPath);}
        public void handle(HttpExchange exchange) throws IOException{
            this.sendFile(exchange, this.rootPath + this.getRequestPath(exchange) + ".json");
        }
    }
}