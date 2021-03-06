package server.commandobjects.games;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.jsonobject.CreatedGame;

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
        return ServerFacade.getInstance().createGame(randomTiles, randomNumbers, randomPorts, name);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.create.toString();
    }

    public int getPID() {return -1;}
}
