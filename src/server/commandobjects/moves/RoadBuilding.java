package server.commandobjects.moves;

import com.sun.javafx.geom.Edge;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class RoadBuilding implements ICommand {
    private int playerIndex;
    private EdgeLocation spot1;
    private EdgeLocation spot2;

    public RoadBuilding(int playerIndex, int x, int y, EdgeDirection direction, EdgeLocation spot2) {
        this.playerIndex = playerIndex;
        this.spot1 = new EdgeLocation(new HexLocation(x, y), direction);
        this.spot2 = spot2;
    }

    /**
     * Calls on server facade and allows the player to
     * place down two roads on the map
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().roadBuilding(playerIndex, spot1, spot2);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.Road_Building.toString();
    }
    public int getPID() {return playerIndex;}
}
