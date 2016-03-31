package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 3/28/2016.
 */
public interface IAIntel {
    void gameToActOn(GameModel game);
    List<ICommand> buildTurn(boolean setup);
}
