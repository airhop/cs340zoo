package server.ai;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.commandobjects.moves.RollNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Joshua on 3/28/2016.
 */
public class AISettlements implements IAIntel {
    private GameModel myGame;
    private int playerAIIndex;
    private int playerAIId;

    public AISettlements(int id, int index){
        playerAIIndex = index;
        playerAIId = id;
    }

    @Override
    public void gameToActOn(GameModel game) {
        myGame = game;
    }

    @Override
    public List<ICommand> buildTurn(boolean setup) {
        List<ICommand> myCommands = new ArrayList<>();
        ICommand roll = new RollNumber(rollAction(), playerAIIndex);
        myCommands.add(roll);

        return myCommands;
    }
    private int rollAction(){
        Random rand = new Random();
        int roll = rand.nextInt()%6 + 1;
        roll += rand.nextInt()%6 + 1;
        return roll;
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
