package server.plugincode.iplugin;

import client.model.GameModel;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IGameDAO {
    void addGame(GameModel addModel, int id);
    GameModel readGame(int gameId);
    List<GameModel> readAllGames();
    void updateGame(int gameId, GameModel updateModel);
    void deleteGame(int gameId);
    void clearTable();
}
