package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class Monopoly implements ICommand {
    private Type Monopoly;
    private int playerIndex;

    public Monopoly(Type monopoly, int playerIndex, String resource) {
        Monopoly = monopoly;
        this.playerIndex = playerIndex;
        this.resource = resource;
    }

    private String resource;

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
