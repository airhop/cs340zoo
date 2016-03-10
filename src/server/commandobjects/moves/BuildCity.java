package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.VertexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildCity implements ICommand {
    private Type BuildCity;
    private int playerIndex;
    private VertexLocation location;

    public BuildCity(Type buildCity, int playerIndex, VertexLocation location) {
        BuildCity = buildCity;
        this.playerIndex = playerIndex;
        this.location = location;
    }

    @Override
    public void execute() {

    }
}
