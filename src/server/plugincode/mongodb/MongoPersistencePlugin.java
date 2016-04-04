package server.plugincode.mongodb;

import com.mongodb.*;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class MongoPersistencePlugin implements IPersistencePlugin {

        private MongoClient mongoClient;
        private GameDAO Games;
        private PlayerDAO Players;
        private CommandDAO Commands;
        private DB mdb;
        private DB safeguard;
        private MongoCredential credential;

        public MongoPersistencePlugin()
        {
//            credential = MongoCredential.createCredential("Me", "Catan", "I is awesome".toCharArray());
//            List<MongoCredential> mcs = new ArrayList<MongoCredential>();
//            mcs.add(credential);
//            mongoClient = new MongoClient(new ServerAddress("localhost"), mcs);

//            mongoClient = new MongoClient();
//            List<String> names = mongoClient.getDatabaseNames();
//            mdb = mongoClient.getDB("Catan");

            MongoClient mongo = new MongoClient("localhost", 27017);
            mdb = mongo.getDB("Catan");

            Players = new PlayerDAO(mdb);
            Games = new GameDAO(mdb);
            Commands = new CommandDAO(mdb);
            initializeDB();
        }

    /**
     * method to initialize the Database
     */
    public void initializeDB()
    {
        Players.clearTable();
        Games.clearTable();
        Commands.clearAll();
    }

    /**
     * method that will be called when the ant target clears the method
     */
    public void clear()
    {
        mongoClient = new MongoClient();
        mdb = mongoClient.getDB("Catan");
        //Players.clearTable();
        //Games.clearTable();
        //Commands.clearAll();
    }

    @Override
    public void initializeDB() {

    }

    @Override
    public void startTransaction()
    {
        safeguard = mdb;
    }

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
