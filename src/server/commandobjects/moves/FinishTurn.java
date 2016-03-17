package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class FinishTurn implements ICommand {
    private int playerIndex;

    public FinishTurn(int playerIndex) {
        this.playerIndex = playerIndex;
    }


    /**
     * Calls on server facade and changes the state
     * of playing from one player to the next
     */
    @Override
    public Object execute() {

        ServerFacade.getInstance().finishTurn(playerIndex);
        return ServerFacade.getInstance().getModel();
    }


    @Override
    public void unexecute() {

    }
}
