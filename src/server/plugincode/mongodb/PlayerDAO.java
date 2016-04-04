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
//        if(!mdb.collectionExists("Players"))
//           mdb.createCollection("Players", new BasicDBObject());
    }

    public void addPlayer(Login pi)
    {
        System.out.println(pi.toString());
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("ID", pi.getID());
        bdbo.put("PlayerInfo", pi);
        mdb.getCollection("Players").insert(bdbo);
    }

    public List<PlayerInfo> readAllPlayers()
    {
        List<PlayerInfo> pi = new ArrayList<PlayerInfo>();
        DBCursor cursor = mdb.getCollection("Players").find();
        if(cursor.hasNext())
        {
            DBObject dbo = cursor.next();
            pi.add((PlayerInfo)dbo.get("PlayerInfo"));
        }
        return pi;
    }

    public void clearTable()
    {
        DBCursor dbc = mdb.getCollection("Players").find();
        while(dbc.hasNext())
            mdb.getCollection("Players").findAndRemove(dbc.next());
    }
}
