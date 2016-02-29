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

//        if(0==1){
//            Facade.getInstance().getPlayerID();
//        }

        Facade.getInstance().roll(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), roll);
        getRollView().closeModal();
        getResultView().showModal();
        getResultView().setRollValue(roll);
        Facade.getInstance().setCloseMap(false);

    }

    public void setWaiting() {waiting = false;}

    @Override
    public void update(Observable o, Object arg)
    {
        GameModel gm = (GameModel)o;
        if(!Facade.getInstance().isReady())
            return;
        if((gm.getTurnTracker().getStatus().equalsIgnoreCase("Rolling") && !waiting))
        {
            if(!getRollView().isModalShowing() || !getResultView().isModalShowing()){
                getRollView().showModal();
            }
            waiting = true;
        }
//        else if(gm.getTurnTracker().getStatus().equalsIgnoreCase("Discard") || gm.getTurnTracker().getStatus().equalsIgnoreCase("Playing"))
//        {
//            waiting = false;
//            getResultView().closeModal();
//        }

    }
}

