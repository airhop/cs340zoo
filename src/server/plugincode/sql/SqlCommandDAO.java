package server.plugincode.sql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlCommandDAO implements ICommandDAO {
    private Connection connnection;
    private Gson myGson;

    public SqlCommandDAO(Connection givenConnection) {
        connnection = givenConnection;
        GsonBuilder myBuild = new GsonBuilder();
        myBuild.enableComplexMapKeySerialization();
        myGson = myBuild.create();
    }

    /**
     * Adds a command to the command table
     *
     * @param command Command to add
     * @param gameId  Game command to be added
     */

    @Override
    public void addCommand(ICommand command, int gameId) {
        PreparedStatement stmt;
        try {
            String query = "insert into commands (GameId, CommandType, Command) values (?, ?, ?)";
            stmt = connnection.prepareStatement(query);

            stmt.executeQuery();
        } catch (SQLException e) {
        }
    }

    /**
     * Takes all commands from the database
     *
     * @param gameId game to get the commands for
     * @return list of all commands
     */
    @Override
    public List<ICommand> readAllCommands(int gameId) {
        return null;
    }

    /**
     * removes a game, may not need this
     *
     * @param gameId GameId to be dropped
     */
    @Override
    public void clearGame(int gameId) {

    }

    /**
     * Drops all tables
     */
    @Override
    public void clearAll() {

    }
}
