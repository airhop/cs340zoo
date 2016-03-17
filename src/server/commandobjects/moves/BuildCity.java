package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import shared.locations.VertexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildCity implements ICommand {
    private int playerIndex;
    private VertexLocation location;

    public BuildCity(int playerIndex, VertexLocation location) {
        this.playerIndex = playerIndex;
        this.location = location;
    }

    /**
     * Calls on server facade and builds a city
     * on the map if all necessary conditions are met
     */
    @Override
    public Object execute() {

        ServerFacade.getInstance().buildCity(playerIndex, location);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }
}
