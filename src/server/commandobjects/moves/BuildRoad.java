package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildRoad implements ICommand {
    private Type BuildRoad;
    private int playerIndex;
    private EdgeLocation roadLocation;
    private boolean free;

    public BuildRoad(Type buildRoad, int playerIndex, EdgeLocation roadLocation, boolean free) {
        BuildRoad = buildRoad;
        this.playerIndex = playerIndex;
        this.roadLocation = roadLocation;
        this.free = free;
    }

    @Override
    public void execute() {
    }

    @Override
    public void unexecute() {

    }
}
