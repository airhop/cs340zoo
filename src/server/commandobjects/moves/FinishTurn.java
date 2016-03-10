package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class FinishTurn implements ICommand {
    private Type FinishTurn;
    private int playerIndex;

    public FinishTurn(Type finishTurn, int playerIndex) {
        this.FinishTurn = finishTurn;
        this.playerIndex = playerIndex;
    }

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
