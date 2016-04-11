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
    private boolean grabAi;

    public PlayerWaitingController(IPlayerWaitingView view) {
        super(view);
        Facade.getInstance().addObserver(this);
        grabAi = true;
    }

    private int getNumPlayers() {
        System.out.println(Facade.getInstance().getCurrentPlayer().getGameId());
        List<GameInfo> gameInfo = Facade.getInstance().gamesList();
        GameInfo foundGame = new GameInfo();
        for(int i =0 ; i < gameInfo.size(); i++)
        {
            if(gameInfo.get(i).getId() == Facade.getInstance().getCurrentPlayer().getGameId())
                foundGame = gameInfo.get(i);
        }
        int numPlayers = foundGame.getPlayers().size();
        if (numPlayers == 4)
            Facade.getInstance().setReady();
        return numPlayers;
    }

    private PlayerInfo[] playersInfo() {
        int numPlayers = getNumPlayers();
        PlayerInfo[] players = new PlayerInfo[numPlayers];

        List<GameInfo> gameInfo = Facade.getInstance().gamesList();
        GameInfo foundGame = new GameInfo();
        for(int i =0 ; i < gameInfo.size(); i++)
        {
            if(gameInfo.get(i).getId() == Facade.getInstance().getCurrentPlayer().getGameId())
                foundGame = gameInfo.get(i);
        }
        List<PlayerInfo> myPlayers = foundGame.getPlayers();

        for (int i = 0; i < numPlayers; i++) {
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
        System.out.println("the number of current players: " + getNumPlayers());
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
        if (grabAi){
            List<String> myList = Facade.getInstance().getAiList();
            int size = myList.size();
            String[] AIs = new String[size];
            for(int i = 0; i < size; i++){
                AIs[i] = myList.get(i);
            }
            getView().setAIChoices(AIs);
        }
        List<GameInfo> gameInfo = Facade.getInstance().gamesList();
        GameInfo foundGame = new GameInfo();
        for(int i =0 ; i < gameInfo.size(); i++)
        {
            if(gameInfo.get(i).getId() == Facade.getInstance().getCurrentPlayer().getGameId())
                foundGame = gameInfo.get(i);
        }

        if (foundGame.getPlayers().size() >= 4) {
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
                getView().setPlayers(playersInfo());
                getView().showModal();
            }
        }
    }
}

