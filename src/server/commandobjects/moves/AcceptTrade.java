package server.commandobjects.moves;

import server.commandobjects.ICommand;

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

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
