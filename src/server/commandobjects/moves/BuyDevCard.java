package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuyDevCard implements ICommand {
    private int playerIndex;

    public BuyDevCard(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * Calls on server facade and distributes a development card
     * to the player if all necessary conditions are met
     */
    @Override
    public Object execute() {
        return null;
    }

    @Override
    public void unexecute() {

    }
}
