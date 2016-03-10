package server.commandobjects.games;

import server.commandobjects.ICommand;

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

    @Override
    public void execute() {

    }
}
