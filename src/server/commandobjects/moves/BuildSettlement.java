package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildSettlement implements ICommand {
    private int playerIndex;
    private VertexLocation location;
    private boolean free;

    public BuildSettlement(int playerIndex, int x, int y, VertexDirection direction, boolean free) {
        this.playerIndex = playerIndex;
        this.location = new VertexLocation(new HexLocation(x, y), direction);
        this.free = free;
    }

    /**
     * Calls on server facade and builds a settlement
     * on the map if all necessary conditions are met
     */
    @Override
    public Object execute() {

        ServerFacade.getInstance().buildSettlement(playerIndex, location, free);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }
}
