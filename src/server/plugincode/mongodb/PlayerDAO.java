package server.plugincode.mongodb;

import client.MVC.data.PlayerInfo;
import client.model.player.Player;
import com.mongodb.*;
import server.plugincode.iplugin.IPlayerDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PlayerDAO implements IPlayerDAO
{
    private DB mdb;
    public PlayerDAO(DB mdb)
    {
        this.mdb = mdb;
        mdb.createCollection("Players", new BasicDBObject());
    }

    public void addPlayer(PlayerInfo pi)
    {
        BasicDBObject bdbo = new BasicDBObject();
        bdbo.put("ID", pi.getId());
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
        mdb.getCollection("Players").drop();
    }
}
