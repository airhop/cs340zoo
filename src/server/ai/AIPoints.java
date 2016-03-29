package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 3/28/2016.
 */
public class AIPoints implements IAIntel {
    @Override
    public void gameToActOn(GameModel game) {

    }

    @Override
    public List<ICommand> buildTurn() {
        return null;
    }
}
