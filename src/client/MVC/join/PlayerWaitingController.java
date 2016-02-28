package client.MVC.join;

import client.MVC.base.*;
import client.MVC.data.PlayerInfo;
import client.facade.Facade;
import client.model.GameModel;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.ArrayList;
import java.util.Observable;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController
{

    public PlayerWaitingController(IPlayerWaitingView view)
    {
        super(view);
        Facade.getInstance().addObserver(this);
    }

    private int getNumPlayers()
    {
        int numPlayers = Facade.getInstance().gamesList().get(Facade.getInstance().getGameModel().getID()).getPlayers().size();
        if (numPlayers == 4)
            Facade.getInstance().setReady();
        return numPlayers;
    }
    private PlayerInfo[] playersInfo()
    {
        PlayerInfo[] players = new PlayerInfo[getNumPlayers()];

        for(int i = 0; i < getNumPlayers(); i++)
        {
            players[i] = Facade.getInstance().gamesList().get(Facade.getInstance().getGameModel().getID()).getPlayers().get(i);
        }
        return players;
    }
    @Override
    public IPlayerWaitingView getView() {

        return (IPlayerWaitingView) super.getView();
    }
    

    @Override
    public void start() {
        System.out.println("the number of current players: ");
        System.out.println(getNumPlayers());


        getView().setPlayers(playersInfo());
        if (getNumPlayers() < 4) 
        {
            getView().showModal();
        } else
        {
            getView().closeModal();
        }
    }

    @Override
    public void addAI() {

        // TEMPORARY
        Facade.getInstance().gameAddAI();
        if (getNumPlayers() < 4) 
        {

            getView().showModal();
        } else 
        {
            getView().closeModal();
        }
        //Facade.getInstance().gamesCreate("New Game");
    }

    @Override
    public void update(Observable o, Object arg) {

        GameModel game = Facade.getInstance().getGameModel();
        if(Facade.getInstance().gamesList().get(game.getID()).getPlayers().size() >= 4 && game.getTurnTracker().getStatus() == "Waiting")
        {
            getView().setPlayers(playersInfo());
            getView().closeModal();
        }


    }
}

