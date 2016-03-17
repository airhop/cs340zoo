package server.commandobjects.moves;

import client.model.bank.ResourceList;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import shared.definitions.ResourceType;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class YearOfPlenty implements ICommand {
    private int playerIndex;
    private ResourceList resource1;
    private ResourceList resource2;

    public YearOfPlenty(int playerIndex, ResourceList resource1, ResourceList resource2) {
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
}
