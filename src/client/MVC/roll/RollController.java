package client.MVC.roll;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import shared.extra.StopWatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

    private IRollResultView resultView;
    private boolean waiting = false;
    private int timesThrough = 0;

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
        //timer = new Timer(5000, new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        rollDice();
        //        timer.restart();
        //    }
        //});
        //timer.start();
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

        System.out.println("rolling!!");
        getRollView().closeModal();
        getResultView().showModal();
        getResultView().setRollValue(roll);
        Facade.getInstance().roll(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), roll);
//        Facade.getInstance().setCloseMap(false);
    }

    public void setWaiting() {waiting = false;}

    @Override
    public void update(Observable o, Object arg)
    {
        GameModel gm = (GameModel)o;
        if(!Facade.getInstance().isReady())
            return;
        if(Facade.getInstance().getCurrentPlayer().getPlayerIndex() != Facade.getInstance().getGameModel().getTurnTracker().getCurrentPlayer())
            return;
        if(!gm.getTurnTracker().getStatus().equalsIgnoreCase("Rolling"))
            return;

        if (!waiting)
        {
            if (!getRollView().isModalShowing()) {
                if (!getResultView().isModalShowing()) {
                        getRollView().showModal();
                }
            } else waiting = true;
        } else {
            timesThrough++;
        }
        if (timesThrough == 2) {
            rollDice();
            timesThrough = 0;
        }
//        else if(gm.getTurnTracker().getStatus().equalsIgnoreCase("Discard") || gm.getTurnTracker().getStatus().equalsIgnoreCase("Playing"))
//        {
//            waiting = false;
//            getResultView().closeModal();
//        }

    }
}

