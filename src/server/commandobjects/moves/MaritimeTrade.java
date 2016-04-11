package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class MaritimeTrade implements ICommand {
    private int playerIndex;
    private int ratio;
    private String inputResource;
    private String outputResource;

    public MaritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource) {
        this.playerIndex = playerIndex;
        this.ratio = ratio;
        this.inputResource = inputResource;
        this.outputResource = outputResource;
    }

    /**
     * Calls on server facade and allows the player to
     * trade resources with the bank depending on the
     * conditions of placed settlements/cities, and as
     * long as all other conditions are met
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().maritimeTrade(playerIndex, ratio, inputResource, outputResource);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.maritimeTrade.toString();
    }
}
