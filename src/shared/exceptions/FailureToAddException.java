package shared.exceptions;

/**
 * Created by Joshua on 1/23/2016.
 */
public class FailureToAddException extends Exception {

    String message;
    public FailureToAddException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}
