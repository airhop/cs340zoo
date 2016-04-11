package server.commandobjects.moves;

import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.locations.HexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class Soldier implements ICommand {
    private int playerIndex;
    private int vicitmIndex;
    private HexLocation location;

    public Soldier(int playerIndex, int vicitmIndex, HexLocation location) {
        this.playerIndex = playerIndex;
        this.vicitmIndex = vicitmIndex;
        this.location = location;
    }

    /**
     * Calls on server facade and allows the player to
     * move the robber to a different hex and rob someone
     * if they desire.
     */
    @Override
    public Object execute() {
        ServerFacade.getInstance().soldier(playerIndex, vicitmIndex, location);
        return ServerFacade.getInstance().getModel();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.Soldier.toString();
    }
}
