package shared.test;

import client.model.GameModel;
import client.model.bank.ResourceList;
import org.junit.Test;
import server.commandobjects.ICommand;
import server.factories.GamesFactory;
import server.factories.MovesFactory;
import server.factories.UserFactory;
import server.serverfacade.ServerFacade;
import server.servermain.JsonConstructionInfo;
import server.shared.CommandType;
import shared.jsonobject.CreatedGame;
import shared.jsonobject.Login;

/**
 * Created by airho on 3/30/2016.
 */
public class ServerTest {

    private UserFactory userFactory = new UserFactory();
    private GamesFactory gamesFactory = new GamesFactory();
    private MovesFactory movesFactory = new MovesFactory();
    private ServerFacade facade;

    @Test
    public void testLogin() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"username\":\"Aaron\",\"password\":\"aaron\"}";
        ICommand current = userFactory.getCommand(new JsonConstructionInfo(CommandType.login, requestBody));
        Object o = current.execute();
        Login login = (Login) o;
        assert (login.getUsername().equals("Aaron") && login.getPassword().equals("aaron"));
    }

    @Test
    public void testCreate() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"randomTiles\":true,\"randomNumbers\":true,\"randomPorts\":true,\"name\":\"Best Game\"}";
        ICommand current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.create, requestBody));
        Object o = current.execute();
        CreatedGame game = (CreatedGame) o;
        assert (game.getId() == 2 && game.getTitle().equals("Best Game"));
    }

    @Test
    public void testJoin() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"id\":\"2\",\"color\":\"purple\"}";
        ICommand current = gamesFactory.getCommand(new JsonConstructionInfo(CommandType.join, requestBody));
        Object o = current.execute();
        CreatedGame game = (CreatedGame) o;
        assert (game.getId() == 2);
    }

    @Test
    public void testAcceptTrade() {
        facade = ServerFacade.getInstance();
        ResourceList firstRL = facade.getModel().getPlayers().get(1).getResources();
        String requestBody = "{\"type\":\"acceptTrade\",\"playerIndex\":1,\"willAccept\":true}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.acceptTrade, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (firstRL != game.getPlayers().get(1).getResources());
    }

    @Test
    public void testBuildRoad() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"buildRoad\",\"playerIndex\":0,\"roadLocation\":{\"x\":\"0\",\"y\":\"0\",\"direction\":\"NW\"},\"free\":\"true\"}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.buildRoad, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getMap().getRoads().size() == 1);
    }

    @Test
    public void testBuildSettlement() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"buildSettlement\",\"playerIndex\":0,\"vertexLocation\":{\"x\":\"0\",\"y\":\"0\",\"direction\":\"NW\"},\"free\":true}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.buildSettlement, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getMap().getBuildings().size() == 1);
    }

    @Test
    public void testFinishTurn() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"finishTurn\",\"playerIndex\":0}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.finishTurn, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getTurnTracker().getStatus().equals("rolling"));
    }

    @Test
    public void testRollNumber() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"rollNumber\",\"playerIndex\":0,\"number\":\"7\"}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.rollNumber, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getTurnTracker().getStatus().equals("robbing"));
    }

    @Test
    public void testSendChat() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"sendChat\",\"playerIndex\":0,\"content\":\"hello!\"}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.sendChat, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getChat().getChatList().size() == 1);
    }

    @Test
    public void testOfferTrade() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"offerTrade\",\"playerIndex\":0,\"offer\":{\"brick\":5,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":-1},\"receiver\":1}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.offerTrade, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getTradeOffer().getSender() == 0 && game.getTradeOffer().getReciever() == 1);
    }

    @Test
    public void testBuyDevCard() {
        facade = ServerFacade.getInstance();
        String requestBody = "{\"type\":\"buyDevCard\",\"playerIndex\":0}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.buyDevCard, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (game.getPlayers().get(0).getNewDevCards().getSize() == 1);
    }

    @Test
    public void testDiscardCards() {
        facade = ServerFacade.getInstance();
        ResourceList firstRL = facade.getModel().getPlayers().get(0).getResources();
        String requestBody = "{\"type\":\"discardCards\",\"playerIndex\":0,\"discardedCards\":{\"brick\":5,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0}}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.discardCards, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (firstRL != game.getPlayers().get(0).getResources());
    }

    @Test
    public void testRobPlayer() {
        facade = ServerFacade.getInstance();
        ResourceList firstRL = facade.getModel().getPlayers().get(0).getResources();
        String requestBody = "{\"type\":\"robPlayer\",\"playerIndex\":0,\"victimIndex\":1,\"location\":{\"x\":\"0\",\"y\":\"0\"}}";
        ICommand current = movesFactory.getCommand(new JsonConstructionInfo(CommandType.robPlayer, requestBody));
        Object o = current.execute();
        GameModel game = (GameModel) o;
        assert (firstRL != game.getPlayers().get(0).getResources());
    }
}
