package server.plugincode.sql;

import client.model.GameModel;
import server.plugincode.iplugin.IGameDAO;

import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlGameDAO implements IGameDAO {

    /**
     * Add a gameModel to the database
     * @param addModel the model that you are adding
     * @param id id to put in with the model
     */
    @Override
    public void addGame(GameModel addModel, int id) {

    }

    /**
     * reads a game model with an id
     * @param gameId the game to read from the database
     * @return returns a gameModel
     */
    @Override
    public GameModel readGame(int gameId) {
        return null;
    }

    public List<GameModel> readAllGames()
    {
        return null;
    }
    /**
     * Updates a gameModel in the database, from an Id
     * @param gameId The game id that you are grabbing
     * @param updateModel The model that you are updating with
     */
    @Override
    public void updateGame(int gameId, GameModel updateModel) {

    }

    /**
     * Drop all tables
     */
    @Override
    public void clearTable() {

    }
}
