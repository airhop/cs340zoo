package server.plugincode.sql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;
import server.shared.CommandType;
import shared.jsonobject.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlCommandDAO implements ICommandDAO {
    private Gson myGson;

    public SqlCommandDAO() {
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
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, gameId);
            stmt.setString(2, command.getType());
            stmt.setString(3, myGson.toJson(command));

            stmt.executeUpdate();
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
        List<ICommand> myCommands = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select * from commands where GameId = ?";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, gameId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                myCommands.add(CommandType.getClass(CommandType.convert(rs.getString(2)), rs.getString(3)));
            }
        } catch (SQLException e) {
        }
        return myCommands;
    }

    /**
     * removes a game, may not need this
     *
     * @param gameId GameId to be dropped
     */
    @Override
    public void clearGame(int gameId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "DELETE from commands Where GameId = ?";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, gameId);

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    /**
     * Drops all tables
     */
    @Override
    public void clearAll() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "DELETE from commands";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
