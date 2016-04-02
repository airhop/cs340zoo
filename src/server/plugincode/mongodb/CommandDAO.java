package server.plugincode.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {

    private DB mdb;
    public CommandDAO(DB mdb)
    {
        this.mdb = mdb;
        mdb.createCollection("Commands", new BasicDBObject());
    }

    @Override
    public void addCommand(ICommand command, int gameID)
    {

    }

    @Override
    public List<ICommand> readAllCommands()
    {
        return null;
    }

    @Override
    public void clearGame(int gameId)
    {

    }

    @Override
    public void clearAll()
    {

    }
}
