package server.servermain.exceptions;

public class ServerException extends Exception {
    String message;

    public ServerException(String msg) {
        message = msg;
    }

    /**
     * Retrieve the message that was saved when the exception was created
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

}

