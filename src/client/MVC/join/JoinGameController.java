package client.MVC.join;

import client.MVC.base.*;
import client.MVC.data.*;
import client.MVC.main.Catan;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.player.CurrentPlayer;
import client.model.player.Player;
import shared.definitions.CatanColor;
import shared.serialization.CreateGamePassObject;

import javax.swing.*;
import java.util.List;
import java.util.Observable;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

    private INewGameView newGameView;
    private ISelectColorView selectColorView;
    private IMessageView messageView;
    private IAction joinAction;

    /**
     * JoinGameController constructor
     *
     * @param view            Join game view
     * @param newGameView     New game view
     * @param selectColorView Select color view
     * @param messageView     Message view (used to display error messages that occur while the user is joining a game)
     */
    public JoinGameController(IJoinGameView view, INewGameView newGameView, ISelectColorView selectColorView, IMessageView messageView) {
        super(view);

        setNewGameView(newGameView);
        setSelectColorView(selectColorView);
        setMessageView(messageView);
        Facade.getInstance().addObserver(this);
    }

    public IJoinGameView getJoinGameView() {

        return (IJoinGameView) super.getView();
    }

    /**
     * Returns the action to be executed when the user joins a game
     *
     * @return The action to be executed when the user joins a game
     */
    public IAction getJoinAction() {

        return joinAction;
    }

    /**
     * Sets the action to be executed when the user joins a game
     *
     * @param value The action to be executed when the user joins a game
     */
    public void setJoinAction(IAction value) {

        joinAction = value;
    }

    public INewGameView getNewGameView() {

        return newGameView;
    }

    public void setNewGameView(INewGameView newGameView) {

        this.newGameView = newGameView;
    }

    public ISelectColorView getSelectColorView() {
        return selectColorView;
    }

    public void setSelectColorView(ISelectColorView selectColorView) {
        this.selectColorView = selectColorView;
    }

    public IMessageView getMessageView() {

        return messageView;
    }

    public void setMessageView(IMessageView messageView) {

        this.messageView = messageView;
    }

    @Override
    public void start() {
        //    System.out.println("start");
        Facade.getInstance().updateGamesList();
        List<GameInfo> games = Facade.getInstance().gamesList();
        GameInfo[] myType = new GameInfo[games.size()];
        for (int i = 0; i < games.size(); i++) {
            myType[i] = games.get(i);
        }
        System.out.println();
        getJoinGameView().setGames(myType, Facade.getInstance().getCurrentPlayerInfo());
        getJoinGameView().showModal();
    }

    @Override
    public void startCreateNewGame() {
        Facade.getInstance().setSettingColor(true);
        //    System.out.println("startCreateNewGame");
        getNewGameView().showModal();
    }

    @Override
    public void cancelCreateNewGame() {
        //    System.out.println("cancelNewGame");
        Facade.getInstance().setSettingColor(false);
        getNewGameView().closeModal();
    }

    @Override
    public void createNewGame() {
        //    System.out.println("createNewGame");

        CreateGamePassObject gameNew = new CreateGamePassObject();
        gameNew.setGameName(getNewGameView().getTitle());
        gameNew.setRandomNumbers(getNewGameView().getRandomlyPlaceNumbers());
        gameNew.setRandomPorts(getNewGameView().getUseRandomPorts());
        gameNew.setRandomTiles(getNewGameView().getRandomlyPlaceHexes());
        Facade.getInstance().gamesCreate(gameNew);
        Facade.getInstance().updateGamesList();
        List<GameInfo> games = Facade.getInstance().gamesList();
        GameInfo[] myType = new GameInfo[games.size()];
        for (int i = 0; i < games.size(); i++) {
            myType[i] = games.get(i);
        }
        getJoinGameView().setGames(myType, Facade.getInstance().getCurrentPlayerInfo());
        getNewGameView().closeModal();
    }

    @Override
    public void startJoinGame(GameInfo game) {//selects game
        // Facade.getInstance().setSettingColor(true);
        int gameToJoin = 0;
        if (game != null) {
            getSelectColorView().resetColors(null);
            List<PlayerInfo> players = game.getPlayers();
            Facade.getInstance().setSettingColor(true);
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getId() != Facade.getInstance().getCurrentPlayer().getPlayerId()) {
                    getSelectColorView().setColorEnabled(players.get(i).getColor(), false);
                }
            }
            Facade.getInstance().getCurrentPlayer().setGameId(game.getId());
            getSelectColorView().showModal();
        }
    }

    @Override
    public void cancelJoinGame() {
        // Facade.getInstance().setSettingColor(false);
        getJoinGameView().closeModal();
    }

    @Override
    public void joinGame(CatanColor color) {
        //    System.out.println("Game Joining");
        //    CurrentPlayer player = Facade.getInstance().getCurrentPlayer();
        Facade.getInstance().gamesJoin(color.name(), Facade.getInstance().getCurrentPlayer().getGameId());
        Facade.getInstance().getCurrentPlayer().setColor(color);
        getSelectColorView().closeModal();
        getJoinGameView().closeModal();
        joinAction.execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!Facade.getInstance().isSettingColor()) {
            List<GameInfo> games = Facade.getInstance().gamesList();
            GameInfo[] myType = new GameInfo[games.size()];
            for (int i = 0; i < games.size(); i++) {
                myType[i] = games.get(i);
            }
            if (getJoinGameView().isModalShowing()) {
                getJoinGameView().closeModal();
            }
            getJoinGameView().setGames(myType, Facade.getInstance().getCurrentPlayerInfo());
            getJoinGameView().showModal();
        }
        if (selectColorView.isModalShowing()) {
            CatanColor myColor = selectColorView.getSelectedColor();
            selectColorView.resetColors(myColor);
            selectColorView.closeModal();
            Facade.getInstance().updateGamesList();
            List<GameInfo> gameInfo = Facade.getInstance().gamesList();
            GameInfo game = new GameInfo();
            for(int i =0 ; i < gameInfo.size(); i++)
            {
                if(gameInfo.get(i).getId() == Facade.getInstance().getCurrentPlayer().getGameId())
                    game = gameInfo.get(i);
            }
            List<PlayerInfo> players = game.getPlayers();
            for(int i = 0; i < players.size(); i++){
                if(!players.get(i).getName().equals("") && !Facade.getInstance().getCurrentPlayer().getUsername().equals(players.get(i).getName())){
                    selectColorView.setColorEnabled(players.get(i).getColor(), false);
                }
            }
            selectColorView.showModal();
        }
        //update the game list with the polller?  allow to retrieve newly created games?Sam
    }
}

