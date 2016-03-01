package client.MVC.join;

import client.MVC.base.*;
import client.MVC.data.GameInfo;
import client.MVC.data.PlayerInfo;
import client.facade.Facade;
import client.model.GameModel;
import client.model.player.CurrentPlayer;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

    public PlayerWaitingController(IPlayerWaitingView view) {
        super(view);
        Facade.getInstance().addObserver(this);
    }

    private int getNumPlayers() {
        int numPlayers = Facade.getInstance().gamesList().get(Facade.getInstance().getGameModel().getID()).getPlayers().size();
        if (numPlayers == 4)
            Facade.getInstance().setReady();
        return numPlayers;
    }

    private PlayerInfo[] playersInfo() {
        int numPlayers = getNumPlayers();
        PlayerInfo[] players = new PlayerInfo[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            int gameNum = Facade.getInstance().getGameModel().getID();
            GameInfo myGame = Facade.getInstance().gamesList().get(gameNum);
            List<PlayerInfo> myPlayers = myGame.getPlayers();
            players[i] = myPlayers.get(i);
        }
        return players;
    }

    @Override
    public IPlayerWaitingView getView() {
        return (IPlayerWaitingView) super.getView();
    }


    @Override
    public void start() {
        Facade.getInstance().setPlayerWaiting(true);
        System.out.println("the number of current players: ");
        System.out.println(getNumPlayers());
        getView().setPlayers(playersInfo());
        if (getNumPlayers() < 4) {
            getView().showModal();
        } else {
            getView().closeModal();
        }
    }

    @Override
    public void addAI() {

        // TEMPORARY
        Facade.getInstance().gameAddAI();
        Facade.getInstance().retrieveGameModel();
        if (getNumPlayers() < 4) {
            if(!getView().isModalShowing()){
                getView().showModal();
            }else{
                getView().closeModal();
                getView().showModal();
            }
        } else {
            getView().closeModal();
        }
        //Facade.getInstance().gamesCreate("New Game");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!Facade.getInstance().isPlayerWaiting())
            return;
        GameModel game = Facade.getInstance().getGameModel();
        CurrentPlayer myPlayer = Facade.getInstance().getCurrentPlayer();
        String[] AIs = new String[1];
        AIs[0] = "LARGEST_ARMY";
        getView().setAIChoices(AIs);
        if (Facade.getInstance().gamesList().get(myPlayer.getGameId()).getPlayers().size() >= 4) {
            getView().setPlayers(playersInfo());
            if(Facade.getInstance().isPlayerWaiting()){
                getView().closeModal();
            }
            Facade.getInstance().setPlayerWaiting(false);
        }else{
            getView().setPlayers(playersInfo());
            if(!getView().isModalShowing()){
                getView().showModal();
            }else{
                getView().closeModal();
                getView().showModal();
            }
        }
    }
}

