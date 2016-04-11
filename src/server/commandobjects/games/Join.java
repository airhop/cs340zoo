package server.commandobjects.games;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;

/**
 * Created by airho on 3/9/2016.
 */
public class Join implements ICommand {

    private int id;
    private String color;

    public Join(int id, String color) {
        this.id = id;
        this.color = color;
    }

    /**
     * Calls on server facade and allows the given player to join
     * a game with at least one vacant spot
     */
    @Override
    public Object execute() {
        return ServerFacade.getInstance().joinGame(id, color);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.join.toString();
    }
}
