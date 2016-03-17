package server.commandobjects.games;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;

/**
 * Created by airho on 3/9/2016.
 */
public class Create implements ICommand {

    private boolean randomTiles;
    private boolean randomNumbers;
    private boolean randomPorts;
    private String name;

    public Create(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
        this.randomTiles = randomTiles;
        this.randomNumbers = randomNumbers;
        this.randomPorts = randomPorts;
        this.name = name;
    }

    /**
     * Calls on server facade and creates a game for other
     * players to join
     */
    @Override
    public Object execute() {
        //Dont have all the proper variables yet
        //ServerFacade.getInstance().createGame();
        GameModel model = ServerFacade.getInstance().getModel();
        return null;
    }

    @Override
    public void unexecute() {

    }
}
