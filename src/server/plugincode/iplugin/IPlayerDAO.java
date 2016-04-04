package server.plugincode.iplugin;

import client.MVC.data.PlayerInfo;
import client.model.player.Player;
import shared.jsonobject.Login;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IPlayerDAO {
    void addPlayer(Login login);
    List<PlayerInfo> readAllPlayers();
    void clearTable();
}
