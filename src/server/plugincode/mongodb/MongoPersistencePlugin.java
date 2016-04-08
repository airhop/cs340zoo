package server.plugincode.mongodb;

//import com.mongodb.*;
import com.mongodb.Mongo;
import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import shared.jsonobject.Login;
import org.bson.Document;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Joshua on 4/2/2016.
 */
public class MongoPersistencePlugin implements IPersistencePlugin {

        public MongoClient mongoClient;
        private GameDAO Games;
        private PlayerDAO Players;
        private CommandDAO Commands;
        public DB mdb;
        private DB safeguard;
//        private MongoCredential credential;

        public MongoPersistencePlugin()
        {

            mongoClient = new MongoClient();
            MongoDatabase mdb = mongoClient.getDatabase("Catan2");

            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
            String info = gson.create().toJson(new Login("un", "p", 20));

            Document test = new Document("_id", 20).append("Login", info);
            mdb.getCollection("Players").insertOne(test);

            mdb.getCollection("Players").drop();

            Players = new PlayerDAO(mdb);

            // Games = new GameDAO(mdb);
           // Commands = new CommandDAO(mdb);
            //   clear();
        }


    /**
     * method that will be called when the ant target clears the method
     */
    public void clear()
    {
        mongoClient = new MongoClient();
        mdb = mongoClient.getDB("Catan");
        Players.clearTable();
//        Games.clearTable();
//        Commands.clearAll();
    }

    /**
     * to start a transaction
     */
    @Override
    public void startTransaction()
    {
        safeguard = mdb;
    }

    /**
     * End a transaction.  Rollback or commit
     * @param commit - whether to rollback or not
     */
    @Override
    public void endTransaction(boolean commit)
    {
        if(commit)
            safeguard = null;
        else
            mdb = safeguard;

    }

    @Override
    public ICommandDAO getCommandDAO() {
        return Commands;
    }

    @Override
    public IPlayerDAO getPlayerDAO() {
        return Players;
    }

    @Override
    public IGameDAO getGameDAO() {
        return Games;
    }
}

//            credential = MongoCredential.createCredential("Me", "Catan", "I is awesome".toCharArray());
//            List<MongoCredential> mcs = new ArrayList<MongoCredential>();
//            mcs.add(credential);
//            mongoClient = new MongoClient(new ServerAddress("localhost"), mcs);

//            List<String> names = mongoClient.getDatabaseNames();
//            mdb = mongoClient.getDB("Catan");

//            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
//            builder.writeConcern(WriteConcern.JOURNAL_SAFE);
//            MongoClient mongo = new MongoClient(new ServerAddress("localhost"), builder.build());
//            MongoClient mongo = new MongoClient("localhost", 27017);

// Games = new GameDAO(mdb);
// Commands = new CommandDAO(mdb);

//   if(mdb.getCollection("Players").count() != 0)
//       System.out.println("success . . .");
//   clear();
