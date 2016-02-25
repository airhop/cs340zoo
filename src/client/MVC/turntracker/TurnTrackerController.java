package client.MVC.turntracker;

import client.MVC.base.*;
import client.facade.Facade;
import shared.definitions.CatanColor;

import java.util.Observable;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn()
    {
        int pid = Facade.getInstance().getPlayerID();
        if(Facade.getInstance().canFinishTurn(pid))
            getView().updateGameState("Are you sure?!", true);
        else
            getView().updateGameState("Hurry Up!!!", false);

    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

