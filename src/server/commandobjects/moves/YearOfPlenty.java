package server.commandobjects.moves;

import client.model.bank.ResourceList;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.definitions.ResourceType;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class YearOfPlenty implements ICommand {
    private int playerIndex;
    private ResourceType resource1;
    private ResourceType resource2;

    public YearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
        this.playerIndex = playerIndex;
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    /**
     * Calls on server facade and allows the player to choose
     * two resource types to gain two cards of
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().yearOfPlenty(playerIndex, resource1, resource2);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.Year_Of_Plenty.toString();
    }
    public int getPID() {return playerIndex;}
}
