package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 3/28/2016.
 */
public interface IAIntel {
    int getPlayerAIId();
    void setPlayerAIId(int playerAIId);
    GameModel getMyGame();
    void setMyGame(GameModel myGame);
    int getPlayerAIIndex();
    void setPlayerAIIndex(int playerAIIndex);
    void gameToActOn(GameModel game);
    List<ICommand> buildTurn(boolean setup);
}
