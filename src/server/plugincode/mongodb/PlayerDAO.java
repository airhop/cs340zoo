package server.plugincode.mongodb;

import client.MVC.data.PlayerInfo;
import client.model.player.Player;
import com.mongodb.*;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PlayerDAO implements IPlayerDAO
{
    private DB mdb;
    public PlayerDAO(DB db)
    {
        mdb = db;
        mdb.getCollection("Players");
      //  mdb = mc.getDB("Players");
       // mdb.getCollection("Players");

//        if(!mdb.collectionExists("Players"))
//           mdb.createCollection("Players", new BasicDBObject());
    }

    /**
     * add a player to the players database
     * @param pi - the login information to put into the player database
     */
    public void addPlayer(Login pi)
    {
        System.out.println(pi.toString());
        BasicDBObject bdbo = new BasicDBObject("_id", pi.getID()).append("PlayerInfo", pi);
        mdb.getCollection("Players").insert(bdbo);
    }

    /**
     * Return a list of all players
     * @return
     */
    public List<Login> readAllPlayers()
    {
        List<Login> pi = new ArrayList<PlayerInfo>();
        DBCursor cursor = mdb.getCollection("Players").find();
        if(cursor.hasNext())
        {
            DBObject dbo = cursor.next();
            pi.add((Login)dbo.get("PlayerInfo"));
        }
        return pi;
    }

    public void clearTable()
    {
//        if(mdb.getCollection("Players").count() != 0)
//            mdb.getCollection("Players").drop();

        Login lo = new Login("FirstUser", "password", 0);
        BasicDBObject bdbo = new BasicDBObject("FirstUser", lo);

        mdb.createCollection("Players", bdbo);

//        mdb.createCollection("Players", new BasicDBObject());
//        DBCursor dbc = mdb.getCollection("Players").find();
//        while(dbc.hasNext())
//            mdb.getCollection("Players").findAndRemove(dbc.next());
    }
}
