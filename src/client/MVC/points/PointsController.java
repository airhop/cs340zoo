package client.MVC.points;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.GameModel;
import client.model.player.Player;

import java.util.ArrayList;
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
    public void update(Observable o, Object arg) {
        if (Facade.getInstance().isReady()) {
            int playerIndex = Facade.getInstance().getPlayerIndex();
            getPointsView().setPoints(Facade.getInstance().getPoints(playerIndex));
        }

        System.out.println("Winner " + ((GameModel)o).getWinner());
        if (((GameModel) o).getWinner() != -1)
        {
            System.out.println("There was a winner!!");
            if (Facade.getInstance().getCurrentPlayer().getPlayerId() == ((GameModel) o).getWinner()) {
                getFinishedView().setWinner(Facade.getInstance().getCurrentPlayer().getUsername(), true);
            } else
            {
                ArrayList<Player> players = ((GameModel)o).getPlayers();
                String name = "";
                for(int i =0 ; i < 4; i++)
                {
                    if(players.get(i).getPlayerID() == ((GameModel)o).getWinner())
                        name = players.get(i).getUsername();
                }
                getFinishedView().setWinner(name, false);
            }

            getFinishedView().showModal();
        }
    }
}

