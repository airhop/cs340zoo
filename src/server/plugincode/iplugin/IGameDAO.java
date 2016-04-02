package server.plugincode.iplugin;

import client.model.GameModel;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IGameDAO {
    void addGame(GameModel addModel);
    GameModel readGame(int gameId);
    void updateGame(int gameId, GameModel updateModel);
    void clearTable();
}
