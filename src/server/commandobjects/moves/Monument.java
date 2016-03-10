package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class Monument implements ICommand {
    private Type Monument;
    private int playerIndex;

    public Monument(Type monument, int playerIndex) {
        Monument = monument;
        this.playerIndex = playerIndex;
    }

    @Override
    public void execute() {

    }
}
