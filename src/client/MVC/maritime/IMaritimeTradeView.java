package client.MVC.maritime;

import client.MVC.base.*;

/**
 * Interface for the maritime trade view, which displays the "Maritime Trade"
 * button
 */
public interface IMaritimeTradeView extends IView {

    /**
     * Enables or disables the maritime trade button.
     *
     * @param value Whether or not the maritime trade button is enabled
     */
    void enableMaritimeTrade(boolean value);

}

