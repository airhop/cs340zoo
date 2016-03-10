package server.commandobjects.moves;

import client.model.bank.ResourceList;
import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class DiscardCards implements ICommand {
    private Type DiscardCards;
    private int playerIndex;
    private ResourceList discardedCards;

    public DiscardCards(Type discardCards, int playerIndex, ResourceList discardedCards) {
        DiscardCards = discardCards;
        this.playerIndex = playerIndex;
        this.discardedCards = discardedCards;
    }

    @Override
    public void execute() {

    }
}
