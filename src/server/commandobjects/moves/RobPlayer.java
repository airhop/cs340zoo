package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.locations.HexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class RobPlayer implements ICommand {
    private int playerIndex;
    private int victimIndex;
    private HexLocation location;

    public RobPlayer(int playerIndex, int victimIndex, String x, String y) {
        this.playerIndex = playerIndex;
        this.victimIndex = victimIndex;
        this.location = new HexLocation(Integer.parseInt(x), Integer.parseInt(y));
    }

    /**
     * Calls on server facade and allows the player to rob
     * another player on a given hex whenever a 7 is rolled,
     * or a soldier is played
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().robPlayer(playerIndex, victimIndex, location);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.robPlayer.toString();
    }
}
