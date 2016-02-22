package client.MVC.base;

import java.util.Observable;
import java.util.Observer;

/**
 * Base controller interface
 */
public interface IController extends Observer
{

    /**
     * View getter
     *
     * @return The controller's view
     */
    IView getView();

}

