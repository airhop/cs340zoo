package server.commandobjects.moves;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class AcceptTrade implements ICommand {
    private int playerIndex;
    private boolean willAccept;

    public AcceptTrade(int playerIndex, boolean willAccept) {
        this.playerIndex = playerIndex;
        this.willAccept = willAccept;
    }

    /**
     * Calls on server facade and either confirms
     * or denies the trade that was offered from one
     * player to the other
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().acceptTrade(playerIndex, willAccept);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }
}
