package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class AcceptTrade implements ICommand {
    private Type AcceptTrade;
    private int playerIndex;
    private boolean willAccept;

    public AcceptTrade(Type acceptTrade, int playerIndex, boolean willAccept) {
        AcceptTrade = acceptTrade;
        this.playerIndex = playerIndex;
        this.willAccept = willAccept;
    }

    @Override
    public void execute() {

    }
}
