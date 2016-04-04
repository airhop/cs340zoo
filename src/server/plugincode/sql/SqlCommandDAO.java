package server.plugincode.sql;

import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlCommandDAO implements ICommandDAO {

   public SqlCommandDAO()
   {

   }


    /**
     * Adds a command to the command table
     * @param command Command to add
     * @param gameId Game command to be added
     */

    @Override
    public void addCommand(ICommand command, int gameId) {

    }

    /**
     * Takes all commands from the database
     * @param gameId game to get the commands for
     * @return list of all commands
     */
    @Override
    public List<ICommand> readAllCommands(int gameId) {
        return null;
    }

    /**
     * removes a game, may not need this
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
