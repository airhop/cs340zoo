package server.commandobjects.game;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

/**
 * Created by airho on 3/9/2016.
 */
public class AddAI implements ICommand {
    String name;

    public AddAI(String name) {
        this.name = name;
    }

    /**
     * Calls on server facade and adds a computer player to
     * the game
     */
    @Override
    public Object execute() {
        return null;
    }

    @Override
    public void unexecute() {

    }
}
