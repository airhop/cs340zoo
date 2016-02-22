package client.MVC.communication;

import client.MVC.base.*;
import client.facade.Facade;

import java.util.Observable;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

    public ChatController(IChatView view) {

        super(view);
    }

    @Override
    public IChatView getView() {
        return (IChatView) super.getView();
    }

    @Override
    public void sendMessage(String message)
    {
        Facade f = Facade.getInstance();
        f.canSendChat(message, f.getPlayerID());

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

