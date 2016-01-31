package shared.exceptions;

/**
 * Created by Josh on 1/31/2016.
 */
public class ClientException extends Exception{

    public ClientException() {
        return;
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable throwable) {
        super(throwable);
    }

    public ClientException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
