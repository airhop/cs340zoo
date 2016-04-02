package server.plugincode.sql;

import client.model.player.Player;
import server.plugincode.iplugin.IPlayerDAO;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PlayerDAO implements IPlayerDAO{
    @Override
    public void addPlayer() {

    }

    @Override
    public List<Player> readAllPlayers() {
        return null;
    }

    @Override
    public void clearTable() {

    }
}
