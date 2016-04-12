package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
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
    public Object execute() {

        ServerFacade.getInstance().buildRoad(playerIndex, roadLocation, free);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.buildRoad.toString();
    }
    public int getPID() {return playerIndex;}
}
