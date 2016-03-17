package server.commandobjects.moves;

import client.model.bank.ResourceList;
import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class DiscardCards implements ICommand {
    private int playerIndex;
    private ResourceList discardedCards;

    public DiscardCards(int playerIndex, ResourceList discardedCards) {
        this.playerIndex = playerIndex;
        this.discardedCards = discardedCards;
    }

    /**
     * Calls on server facade and makes the player
     * discard half of their cards if their current
     * hand contains more that 7 resources
     */
    @Override
    public Object execute() {
        return null;
    }

    @Override
    public void unexecute() {

    }
}
