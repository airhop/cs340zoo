package server.plugincode.iplugin;

import server.commandobjects.ICommand;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface ICommandDAO {
    void addCommand(ICommand command, int gameId);
    List<ICommand> readAllCommands(int gameId);
    void clearGame(int gameId);
    void clearAll();
}
