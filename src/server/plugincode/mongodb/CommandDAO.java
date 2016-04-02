package server.plugincode.mongodb;

import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {
    @Override
    public void addCommand(ICommand command) {

    }

    @Override
    public List<ICommand> readAllCommands() {
        return null;
    }

    @Override
    public void clearGame(int gameId) {

    }

    @Override
    public void clearAll() {

    }
}
