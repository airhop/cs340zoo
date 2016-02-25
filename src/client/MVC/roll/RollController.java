package client.MVC.roll;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;

import java.util.Observable;
import java.util.Random;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

    private IRollResultView resultView;
    private boolean waiting = false;

    /**
     * RollController constructor
     *
     * @param view       Roll view
     * @param resultView Roll result view
     */
    public RollController(IRollView view, IRollResultView resultView) {

        super(view);
        Facade.getInstance().addObserver(this);
        setResultView(resultView);
    }

    public IRollResultView getResultView() {
        return resultView;
    }

    public void setResultView(IRollResultView resultView) {
        this.resultView = resultView;
    }

    public IRollView getRollView() {
        return (IRollView) super.getView();
    }

    @Override
    public void rollDice()
    {

        Random r = new Random();
        int x = r.nextInt(6);  //0 - 5
        int y = r.nextInt(6); // 0 - 5
        int roll = x + y + 2;

        Facade.getInstance().roll(Facade.getInstance().getPlayerID(), roll);
        getResultView().showModal();
        getResultView().setRollValue(roll);
    }

    public void setWaiting() {waiting = false;}

    @Override
    public void update(Observable o, Object arg)
    {
        if(((GameModel)o).getTurnTracker().getStatus().equalsIgnoreCase("Rolling") && !waiting)
        {
            getRollView().showModal();
            waiting = true;
        }

    }
}
