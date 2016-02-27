package client.MVC.points;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;

import java.util.Observable;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

    private IGameFinishedView finishedView;

    /**
     * PointsController constructor
     *
     * @param view         Points view
     * @param finishedView Game finished view, which is displayed when the game is over
     */
    public PointsController(IPointsView view, IGameFinishedView finishedView) {

        super(view);

        setFinishedView(finishedView);
        Facade.getInstance().addObserver(this);
        getPointsView().setPoints(0);
    }

    public IPointsView getPointsView() {

        return (IPointsView) super.getView();
    }

    public IGameFinishedView getFinishedView() {
        return finishedView;
    }

    public void setFinishedView(IGameFinishedView finishedView) {
        this.finishedView = finishedView;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        int playerIndex = Facade.getInstance().getPlayerIndex();
        getPointsView().setPoints(Facade.getInstance().getPoints(playerIndex));
    }
}

