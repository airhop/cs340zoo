package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildSettlement implements ICommand {
    private Type BuildSettlement;
    private int playerIndex;
    private VertexLocation location;
    private boolean free;

    public BuildSettlement(Type buildSettlement, int playerIndex, int x, int y, VertexDirection direction, boolean free) {
        BuildSettlement = buildSettlement;
        this.playerIndex = playerIndex;
        this.location = new VertexLocation(new HexLocation(x, y), direction);
        this.free = free;
    }

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
