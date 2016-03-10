package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.HexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class RobPlayer implements ICommand {
    private Type RobPlayer;
    private int playerIndex;
    private int victimIndex;
    private HexLocation location;

    public RobPlayer(Type robPlayer, int playerIndex, int victimIndex, String x, String y) {
        this.RobPlayer = robPlayer;
        this.playerIndex = playerIndex;
        this.victimIndex = victimIndex;
        this.location = new HexLocation(Integer.parseInt(x), Integer.parseInt(y));
    }

    @Override
    public void execute() {

    }
}
