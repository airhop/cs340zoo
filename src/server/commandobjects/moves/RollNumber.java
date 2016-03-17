package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class RollNumber implements ICommand {
    private int playerIndex;
    private int number;

    public RollNumber(int playerIndex, int number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }

    /**
     * Calls on server facade and rolls two numbers between
     * 1 and 6. Adds them up and applies that number to the
     * players roll.
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().rollNumber(playerIndex, number);
        return ServerFacade.getInstance().getModel();
    }
    @Override
    public void unexecute() {

    }
}
