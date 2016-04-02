package server.plugincode.iplugin;

import client.MVC.data.PlayerInfo;
import client.model.player.Player;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IPlayerDAO {
    void addPlayer(PlayerInfo playerInfo);
    List<Player> readAllPlayers();
    void clearTable();
}
