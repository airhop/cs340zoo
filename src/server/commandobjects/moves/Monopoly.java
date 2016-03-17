package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class Monopoly implements ICommand {
    private int playerIndex;

    public Monopoly(int playerIndex, String resource) {
        this.playerIndex = playerIndex;
        this.resource = resource;
    }

    private String resource;

    /**
     * Calls on server facade and allows the player to pick
     * one resource, and have all other players give that player
     * all of that resource type in their possesion
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().monopoly(playerIndex, resource);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }
}
