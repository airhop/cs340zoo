package server.plugincode.iplugin;

import client.model.player.Player;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IPlayerDAO {
    void addPlayer();
    List<Player> readAllPlayers();
}
