package server.plugincode.sql;

import client.MVC.data.GameInfo;
import client.model.GameModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.plugincode.iplugin.IGameDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlGameDAO implements IGameDAO {
    private Gson myGson;


    public SqlGameDAO() {
        GsonBuilder myBuild = new GsonBuilder();
        myBuild.enableComplexMapKeySerialization();
        myGson = myBuild.create();
    }

    /**
     * Add a gameModel to the database
     *
     * @param addModel the model that you are adding
     * @param id       id to put in with the model
     */
    @Override
    public void addGame(GameModel addModel, int id) {
        PreparedStatement stmt;
        ResultSet rs = null;
        try {
            String query = "insert into games (GameId, GameName, Game) values (?, ?, ?)";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, addModel.getID());
            stmt.setString(2, addModel.getTitle());
            stmt.setString(3, myGson.toJson(addModel));

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    /**
     * reads a game model with an id
     *
     * @param gameId the game to read from the database
     * @return returns a gameModel
     */
    @Override
    public GameModel readGame(int gameId) {
        GameModel myModel = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select Game from games WHERE GameId = ?";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, gameId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                myModel = myGson.fromJson(rs.getString(1), GameModel.class);
            }
        } catch (SQLException e) {
        }
        return myModel;
    }

    public List<GameModel> readAllGames() {
        GameModel myModel = null;
        List<GameModel> myGames = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select Game from games";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);

            rs = stmt.executeQuery();
            while (rs.next()) {
                myGames.add(myGson.fromJson(rs.getString(1), GameModel.class));
            }
        } catch (SQLException e) {
        }
        return myGames;
    }

    /**
     * Updates a gameModel in the database, from an Id
     *
     * @param gameId      The game id that you are grabbing
     * @param updateModel The model that you are updating with
     */
    @Override
    public void updateGame(int gameId, GameModel updateModel) {
        GameModel myModel = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "update games set Game = ? where GameId = ?";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setString(1, myGson.toJson(updateModel, GameModel.class));
            stmt.setInt(2, gameId);

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteGame(int gameId)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "delete from games where GameId = ?";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, gameId);

            stmt.executeUpdate();
        } catch (SQLException e) {
        }

    }

    /**
     * Drop all tables
     */
    @Override
    public void clearTable() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "DELETE from games";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
