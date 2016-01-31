package shared.exceptions;

/**
 * Created by Joshua on 1/23/2016.
 */
public class InvalidUserException extends Exception
{
    String message;
    public InvalidUserException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}
