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

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
