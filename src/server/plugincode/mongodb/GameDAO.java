package server.plugincode.mongodb;

import client.model.GameModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import server.plugincode.iplugin.IGameDAO;

/**
 * Created by Joshua on 4/2/2016.
 */
public class GameDAO implements IGameDAO {

    private DB mdb;
    public GameDAO(DB mdb)
    {
        this.mdb = mdb;
        mdb.getCollection("Games");
      //  if(!mdb.collectionExists("Games"))
      //      mdb.createCollection("Games", new BasicDBObject());
    }

    @Override
    public void addGame(GameModel addModel, int id)
    {
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("ID", addModel.getID());
        bdbo.put("Model", addModel);
        mdb.getCollection("Games").insert(bdbo);
    }

    @Override
    public GameModel readGame(int gameId)
    {
        DBCursor dbc = mdb.getCollection("Games").find();
        while(dbc.hasNext())
        {
            DBObject dbo = dbc.next();
            if((int)dbo.get("ID") == gameId)
                return (GameModel)dbo.get("Model");
        }

        return null;
    }

    @Override
    public void updateGame(int gameId, GameModel updateModel)
    {
        BasicDBObject search = new BasicDBObject();
        search.put("ID", gameId);

        BasicDBObject noob = new BasicDBObject();
        noob.put("ID", gameId);
        noob.put("Model", updateModel);
        mdb.getCollection("Games").update(search, noob);
    }

    @Override
    public void clearTable()
    {
        mdb.getCollection("Games").drop();
    }
}
