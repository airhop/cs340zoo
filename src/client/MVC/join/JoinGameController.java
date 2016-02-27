package client.MVC.join;

import client.MVC.base.*;
import client.MVC.data.*;
import client.MVC.misc.*;
import client.facade.Facade;
import client.model.player.Player;
import shared.definitions.CatanColor;
import shared.serialization.CreateGamePassObject;

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
        List<GameInfo> games = Facade.getInstance().gamesList();
        GameInfo[] myType = new GameInfo[games.size()];
        for (int i = 0; i < games.size(); i++) {
            myType[i] = games.get(i);
        }
        getJoinGameView().setGames(myType, Facade.getInstance().getCurrentPlayerInfo());
        getJoinGameView().showModal();
    }

    @Override
    public void startCreateNewGame() {
    //    System.out.println("startCreateNewGame");
        getNewGameView().showModal();
    }

    @Override
    public void cancelCreateNewGame() {
    //    System.out.println("cancelNewGame");
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
        int gameToJoin = 0;
        if (game != null) {
            getSelectColorView().resetColors();
            getSelectColorView().showModal();
        //    System.out.println("startJoinGame");
            List<PlayerInfo> players = game.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                getSelectColorView().setColorEnabled(players.get(i).getColor(), true);
            }
            Facade.getInstance().getCurrentPlayer().setGameId(game.getId());

        } else {
            Facade.getInstance().gamesCreate(new CreateGamePassObject("Awesome", true, true, true));
            Facade.getInstance().playerLogin("Sam", "sam");
            Facade.getInstance().gamesJoin("blue", gameToJoin);
//            Facade.getInstance().gameAddAI();
//            Facade.getInstance().gameAddAI();
//            Facade.getInstance().gameAddAI();
            getSelectColorView().closeModal();
            getJoinGameView().closeModal();
        }
    }

    @Override
    public void cancelJoinGame() {
        getJoinGameView().closeModal();
    }

    @Override
    public void joinGame(CatanColor color) {
    //    System.out.println("Game Joining");
        Facade.getInstance().gamesJoin(color.name(), Facade.getInstance().getCurrentPlayer().getGameId());
        getSelectColorView().closeModal();
        getJoinGameView().closeModal();

//        Facade.getInstance().playerLogin();

//        Facade.getInstance().gamesJoin(CatanColor.toString(color), 0);
//
//    //to Add 3 extra players so that the game will actually play for testing
//        Facade.getInstance().playerLogin("Pete", "pete");
//        Facade.getInstance().gamesJoin("red", 1);
//        Facade.getInstance().playerLogin("Mark", "mark");
//        Facade.getInstance().gamesJoin("brown", 2);
//        Facade.getInstance().playerLogin("Brooke", "brooke");
//        Facade.getInstance().gamesJoin("purple", 3);
//
//

        joinAction.execute();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        List<GameInfo> games = Facade.getInstance().gamesList();
        GameInfo[] myType = new GameInfo[games.size()];
        for (int i = 0; i < games.size(); i++) {
            myType[i] = games.get(i);
        }
        getJoinGameView().setGames(myType, Facade.getInstance().getCurrentPlayerInfo());
        getJoinGameView().showModal();
        //update the game list with the polller?  allow to retrieve newly created games?Sam
    }
}

