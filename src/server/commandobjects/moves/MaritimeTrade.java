package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class MaritimeTrade implements ICommand {
    private Type MaritimeTrade;
    private int playerIndex;
    private int ratio;
    private String inputResource;
    private String outputResource;

    public MaritimeTrade(Type maritimeTrade, int playerIndex, int ratio, String inputResource, String outputResource) {
        MaritimeTrade = maritimeTrade;
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
