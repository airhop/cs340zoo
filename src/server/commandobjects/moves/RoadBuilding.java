package server.commandobjects.moves;

import com.sun.javafx.geom.Edge;
import server.commandobjects.ICommand;
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

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
