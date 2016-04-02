package server.plugincode.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import server.plugincode.iplugin.ICommandDAO;
import server.commandobjects.ICommand;

import java.util.ArrayList;
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
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("Command", command);
        bdbo.put("ID", gameID);
        mdb.getCollection("Commands").insert(bdbo);
    }

    @Override
    public List<ICommand> readAllCommands(int gameId)
    {
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("ID", gameId);

        DBCursor dbc = mdb.getCollection("Commands").find(bdbo);

        List<ICommand> commands = new ArrayList<ICommand>();

        while(dbc.hasNext())
        {
            DBObject dbo = dbc.next();
            ICommand c = (ICommand)dbo.get("Command");
            commands.add(c);
        }

        return new ArrayList<ICommand>();
    }

    @Override
    public void clearGame(int gameId)
    {
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("ID", gameId);
        mdb.getCollection("Commands").remove(bdbo);
    }

    @Override
    public void clearAll()
    {
        mdb.getCollection("Commands").drop();
    }
}
