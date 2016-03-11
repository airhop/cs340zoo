package server.commandobjects.moves;

import server.commandobjects.ICommand;
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

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
