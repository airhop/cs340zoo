package server.commandobjects.game;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.servermain.Server;

/**
 * Created by airho on 3/9/2016.
 */
public class ListAI implements ICommand {
    public ListAI() {

    }

    /**
     * Calls on server facade and shows a list of
     * all the current computer players
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().listAI();
        return null;
    }

    @Override
    public void unexecute() {

    }
}
