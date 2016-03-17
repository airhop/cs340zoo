package server.commandobjects.games;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

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
        ServerFacade.getInstance().joinGame(id, color);
        GameModel model = ServerFacade.getInstance().getModel();
        return model.getID();
    }

    @Override
    public void unexecute() {

    }
}
