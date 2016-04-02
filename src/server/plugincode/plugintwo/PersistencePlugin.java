package server.plugincode.plugintwo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.LazyDBObject;
import server.plugincode.iplugin.IPersistencePlugin;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;
import org.bson.Document;



/**
 * Created by Joshua on 4/2/2016.
 */
public class PersistencePlugin implements IPersistencePlugin
{

    private MongoClient mongoClient;
    private GameDAO Games;
    private PlayerDAO Players;
    private CommandDAO Commands;

    public PersistencePlugin()
    {
        try {
            mongoClient = new MongoClient();
        }
        catch(UnknownHostException e)
        {
            //oops . . .
        }
        DB mdb = mongoClient.getDB("Catan");

        if(mdb.collectionExists("Games") || mdb.collectionExists("Players") || mdb.collectionExists("Commands"))
            mdb.dropDatabase();
        mdb.createCollection("Games", new BasicDBObject());
        mdb.createCollection("Commands", new BasicDBObject());

        Players = new PlayerDAO(mdb);

    }



}
