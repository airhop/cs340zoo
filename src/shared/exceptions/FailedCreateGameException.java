package shared.exceptions;

/**
 * Created by Joshua on 1/23/2016.
 */
public class FailedCreateGameException extends Exception
{
    String message;
    public FailedCreateGameException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}

