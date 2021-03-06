package server.commandobjects.moves;

import client.model.bank.ResourceList;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class OfferTrade implements ICommand {
    private int playerIndex;
    private int recieverIndex;
    private ResourceList offer;

    public OfferTrade(int playerIndex, int recieverIndex, ResourceList offer) {
        this.playerIndex = playerIndex;
        this.recieverIndex = recieverIndex;
        this.offer = offer;
    }

    /**
     * Calls on server facade and allows one player to
     * send a trade request to another player that they
     * may either accept or deny
     */
    @Override
    public Object execute() {

        ServerFacade.getInstance().offerTrade(playerIndex, offer, recieverIndex);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.offerTrade.toString();
    }

    public int getPID() {return playerIndex;}
}
