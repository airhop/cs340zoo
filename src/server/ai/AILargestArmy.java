package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 3/28/2016.
 */
public class AILargestArmy implements IAIntel {
    @Override
    public int getPlayerAIId() {
        return 0;
    }

    @Override
    public void setPlayerAIId(int playerAIId) {

    }

    @Override
    public GameModel getMyGame() {
        return null;
    }

    @Override
    public void setMyGame(GameModel myGame) {

    }

    @Override
    public int getPlayerAIIndex() {
        return 0;
    }

    @Override
    public void setPlayerAIIndex(int playerAIIndex) {

    }

    @Override
    public void gameToActOn(GameModel game) {

    }

    @Override
    public List<ICommand> buildTurn(boolean setup) {
        return null;
    }
}
