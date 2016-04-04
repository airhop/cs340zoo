package server.plugincode.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

import java.net.UnknownHostException;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PersistencePlugin implements IPersistencePlugin {

        private MongoClient mongoClient;
        private GameDAO Games;
        private PlayerDAO Players;
        private CommandDAO Commands;

        public PersistencePlugin()
        {
            mongoClient = new MongoClient();
//            try {
//
//            }
//            catch(UnknownHostException e)
//            {
//                //oops . . .
//            }
            DB mdb = mongoClient.getDB("Catan");

            if(mdb.collectionExists("Games"))
                mdb.getCollection("Games").drop();

            if(mdb.collectionExists("Players"))
                mdb.getCollection("Players").drop();

            if(mdb.collectionExists("Commands"))
                mdb.getCollection("Commands").drop();

            Players = new PlayerDAO(mdb);
            Games = new GameDAO(mdb);
            Commands = new CommandDAO(mdb);

        }

    @Override
    public void initializeDB() {

    }

    @Override
    public void startTransaction() {

    }

    @Override
    public void endTransaction(boolean commit) {

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
