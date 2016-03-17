package server.commandobjects.game;

import server.commandobjects.ICommand;

/**
 * Created by airho on 3/9/2016.
 */
public class AddAI implements ICommand {
    public AddAI() {

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
