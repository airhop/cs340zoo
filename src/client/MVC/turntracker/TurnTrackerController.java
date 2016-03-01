package client.MVC.turntracker;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController
{
    private boolean setup = false;

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);
        Facade.getInstance().addObserver(this);
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn()
    {
        System.out.println("hey!!");
        Facade.getInstance().FinishTurn(Facade.getInstance().getPlayerIndex());
        getView().updateGameState("Finally . . .", false);
    }

    private void initFromModel() {
        //<temp>
        CatanColor c = Facade.getInstance().getPlayerColor(Facade.getInstance().getPlayerIndex());
        if(c == null)
            c = CatanColor.RED;
        //this
        getView().setLocalPlayerColor(c);


        //</temp>
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if(!Facade.getInstance().isReady())
            return;
        Random rand = new Random();
        int x = rand.nextInt();
        int pid = Facade.getInstance().getCurrentPlayer().getPlayerIndex();
        String state = ((GameModel)o).getTurnTracker().getStatus();

        if(state.equalsIgnoreCase("FirstRound") && state.equalsIgnoreCase("SecondRound"))
            getView().updateGameState("Zzzzzzz", false);
        else if(Facade.getInstance().canFinishTurn(pid))
            getView().updateGameState("Are you sure?!", true);
        else
        {
            if(x % 5 == 0)
                getView().updateGameState("Sheila Parker is taking forever . . . .", false);
            else
                getView().updateGameState("Hurry Up!!!", false);
        }
        if(!setup)
            initFromModel();

        GameModel gm = (GameModel)o;
        ArrayList<Player> players = gm.getPlayers();
        if(players.get(3) == null)
            return;

        int playerId = gm.getCurrentPlayer().getPlayerIndex();
        for(int i = 0; i < players.size(); i++)
        {
            boolean la = gm.getTurnTracker().getLargestArmy() == i;
            boolean lr = gm.getTurnTracker().getLongestRoad() == i;
            boolean highlight = playerId == players.get(i).getPlayerIndex();
            if(!setup)
                getView().initializePlayer(i, players.get(i).getUsername(),
                     CatanColor.convert(players.get(i).getColor()));
            getView().updatePlayer(i, gm.getPoints(i), highlight, la, lr);
          //  System.out.println(i + " " + gm.getPoints(i) + " " + highlight + " " + la + " " + lr);
          //  System.out.println(gm.getTurnTracker().getLargestArmy() + " " + gm.getTurnTracker().getLongestRoad());
        }
        setup = true;
    }

}

