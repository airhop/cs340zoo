package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;

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
        ServerFacade.getInstance().buyDevCard(playerIndex);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.buyDevCard.toString();
    }
    public int getPID() {return playerIndex;}
}
