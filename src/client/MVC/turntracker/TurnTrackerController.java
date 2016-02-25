package client.MVC.turntracker;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import shared.definitions.CatanColor;

import java.util.Observable;
import java.util.Random;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

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
        Facade.getInstance().FinishTurn(Facade.getInstance().getPlayerID());
        getView().updateGameState("Finally . . .", false);
    }

    private void initFromModel() {
        //<temp>
        CatanColor c = Facade.getInstance().getPlayerColor(Facade.getInstance().getPlayerID());
        if(c == null)
            c = CatanColor.RED;

        getView().setLocalPlayerColor(c);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Random rand = new Random();
        int x = rand.nextInt();
        int pid = Facade.getInstance().getPlayerID();
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
        initFromModel();
    }
}

