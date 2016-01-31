package shared.exceptions;

public class InvalidWinnerException extends Exception
{
    String message;
    public InvalidWinnerException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}
