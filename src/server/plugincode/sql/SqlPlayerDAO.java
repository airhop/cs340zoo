package server.plugincode.sql;

import client.MVC.data.PlayerInfo;
import client.model.player.Player;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.Login;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlPlayerDAO implements IPlayerDAO{
    /**
     * Adds a player to the table
     * @param player The player that is going to be put into the table
     */
    @Override
    public void addPlayer(Login player) {

    }

    /**
     * Reads all the players from the table
     * @return List of our players
     */
    @Override
    public List<Login> readAllPlayers() {
        return null;
    }

    /**
     * Clears all the tables
     */
    @Override
    public void clearTable() {

    }
}
