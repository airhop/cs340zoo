package server.plugincode.sql;

import client.model.GameModel;
import server.plugincode.iplugin.IGameDAO;

/**
 * Created by Joshua on 4/2/2016.
 */
public class GameDAO implements IGameDAO {
    @Override
    public void addGame(GameModel addModel) {

    }

    @Override
    public GameModel readGame(int gameId) {
        return null;
    }

    @Override
    public void updateGame(int gameId, GameModel updateModel) {

    }

    @Override
    public void clearTable() {

    }
}
