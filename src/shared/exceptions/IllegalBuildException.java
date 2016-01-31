package shared.exceptions;

import javax.lang.model.util.ElementScanner6;

/**
 * Created by Joshua on 1/23/2016.
 */
public class IllegalBuildException extends Exception {

    String message;
    public IllegalBuildException(String msg)
    {
        message = msg;
    }

    public String getMessage()
    {
        return message;
    }
}
