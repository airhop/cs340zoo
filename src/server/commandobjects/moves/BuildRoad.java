package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class BuildRoad implements ICommand {
    private int playerIndex;
    private EdgeLocation roadLocation;
    private boolean free;

    public BuildRoad(int playerIndex, EdgeLocation roadLocation, boolean free) {
        this.playerIndex = playerIndex;
        this.roadLocation = roadLocation;
        this.free = free;
    }

    /**
     * Calls on server facade and builds a road
     * on the map if all necessary conditions are met
     */
    @Override
    public void execute() {
    }

    @Override
    public void unexecute() {

    }
}
