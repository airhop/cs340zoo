package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class MaritimeTrade implements ICommand {
    private int playerIndex;
    private int ratio;
    private String inputResource;
    private String outputResource;

    public MaritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource) {
        this.playerIndex = playerIndex;
        this.ratio = ratio;
        this.inputResource = inputResource;
        this.outputResource = outputResource;
    }

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
