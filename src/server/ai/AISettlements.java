package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 3/28/2016.
 */
public class AISettlements implements IAIntel {
    GameModel myGame;

    public AISettlements(){

    }

    @Override
    public void gameToActOn(GameModel game) {
        myGame = game;
    }

    @Override
    public List<ICommand> buildTurn() {
        return null;
    }
}
/**
 * Actions
 * Roll - always
 * Trade, maritime, Chat
 * Build - city, road, settlement
 * Buy DevCard
 * Use all devCards always haha
 * Build --> Settlement, City, Road
 */
