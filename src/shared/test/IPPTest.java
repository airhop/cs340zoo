package shared.test;

import client.model.GameModel;
import org.junit.Test;
import server.commandobjects.ICommand;
import server.commandobjects.moves.RollNumber;
import server.commandobjects.moves.SendChat;
import server.plugincode.TextPlugin.TextPersistencePlugin;
import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.Login;

import java.util.List;

public class IPPTest {
    @Test
    public void PlayerDAO() {
        TextPersistencePlugin tpp = new TextPersistencePlugin();
        IPlayerDAO tpdao = tpp.getPlayerDAO();
        tpdao.clearTable();

        List<Login> logins = tpdao.readAllPlayers();
        assert (logins.size() == 0);

        Login first = new Login("un", "p", 0);
        tpdao.addPlayer(first);

        logins = tpdao.readAllPlayers();
        assert (logins.size() == 1);
        assert (logins.get(0).compareTo(first) == 0);

        Login second = new Login("user", "password", 1);
        tpdao.addPlayer(second);
        logins = tpdao.readAllPlayers();
        assert (logins.size() == 2);

        int agreed = 0;
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).compareTo(first) == 0)
                agreed++;
            if (logins.get(i).compareTo(second) == 0)
                agreed++;
        }
        assert (agreed == 2);

        tpdao.clearTable();
        logins = tpdao.readAllPlayers();
        assert (logins.size() == 0);
    }

    @Test
    public void TestGameDAO() {
        TextPersistencePlugin tpp = new TextPersistencePlugin();
        IGameDAO gameDAO = tpp.getGameDAO();
        gameDAO.clearTable();

        List<GameModel> games = gameDAO.readAllGames();
        assert (games.size() == 0);

        GameModel first = new GameModel("first", 1);
        GameModel second = new GameModel("second", 2);

        gameDAO.addGame(first, 0);
        games = gameDAO.readAllGames();
        assert (games.size() == 1);
        assert (games.get(0).getID() == 1);

        gameDAO.addGame(second, 1);
        games = gameDAO.readAllGames();
        assert (games.size() == 2);

        int count = 0;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getID() == 1)
                count++;
            if (games.get(i).getID() == 2)
                count++;
        }
        assert (count == 2);

        //nothing should update
        gameDAO.updateGame(22, new GameModel("third", 3));
        games = gameDAO.readAllGames();
        assert (games.size() == 2);

        count = 0;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getID() == 1)
                count++;
            if (games.get(i).getID() == 2)
                count++;
        }
        assert (count == 2);

        first.getTurnTracker().setCurrentPlayer(4);
        first.getTurnTracker().updateStatus("Robbing");
        gameDAO.updateGame(1, first);
        games = gameDAO.readAllGames();
        count = 0;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getID() == 1)
                count++;
            if (games.get(i).getID() == 2)
                count++;
        }
        assert (count == 2);

        GameModel single = gameDAO.readGame(1);
        assert (single.getTurnTracker().getCurrentPlayer() == 4);
        assert (single.getTurnTracker().getStatus().equalsIgnoreCase("Robbing"));

        single = gameDAO.readGame(22);
        assert (single == null);


        gameDAO.clearTable();
        games = gameDAO.readAllGames();
        assert (games.size() == 0);
    }

    @Test
    public void CommandDAOTest() {
        TextPersistencePlugin tpp = new TextPersistencePlugin();
        ICommandDAO commandDAO = tpp.getCommandDAO();
        commandDAO.clearAll();

        commandDAO.addCommand(new SendChat(0, "Hello"), 0);
        List<ICommand> commands = commandDAO.readAllCommands(0);
        assert (commands.size() == 1);
        assert (commands.get(0).getClass() == SendChat.class);

        commands = commandDAO.readAllCommands(1);
        assert (commands.size() == 0);

        commandDAO.addCommand(new RollNumber(0, 3), 0);
        commands = commandDAO.readAllCommands(0);
        assert (commands.size() == 2);

        int count = 0;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getClass() == SendChat.class)
                count++;
            if (commands.get(i).getClass() == RollNumber.class)
                count++;
        }
        assert (count == 2);

        commandDAO.clearGame(0);
        commands = commandDAO.readAllCommands(0);
        assert (commands.size() == 0);

        commandDAO.addCommand(new SendChat(0, "World"), 0);
        commands = commandDAO.readAllCommands(0);
        assert (commands.size() == 1);

        commandDAO.clearAll();

        commands = commandDAO.readAllCommands(0);
        assert (commands.size() == 0);

    }
}
