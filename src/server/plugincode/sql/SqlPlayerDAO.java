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
    @Override
    public void addPlayer(Login player) {

    }

    @Override
    public List<PlayerInfo> readAllPlayers() {
        return null;
    }

    @Override
    public void clearTable() {

    }
}