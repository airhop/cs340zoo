package server.plugincode.sql;

import client.MVC.data.PlayerInfo;
import client.model.GameModel;
import client.model.player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlPlayerDAO implements IPlayerDAO{

    public SqlPlayerDAO() {

    }


    /**
     * Adds a player to the table
     * @param player The player that is going to be put into the table
     */
    @Override
    public void addPlayer(Login player) {
        PreparedStatement stmt;
        try {
            String query = "insert into players (PlayerId, Username, Password) values (?, ?, ?)";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.setInt(1, player.getID());
            stmt.setString(2, player.getUsername());
            stmt.setString(3, player.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    /**
     * Reads all the players from the table
     * @return List of our players
     */
    @Override
    public List<Login> readAllPlayers() {
        List<Login> myPlayers = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "select * from players";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);

            rs = stmt.executeQuery();
            while (rs.next()) {
                myPlayers.add(new Login(rs.getString(2), rs.getString(3), rs.getInt(1)));
            }
        } catch (SQLException e) {
        }
        return myPlayers;
    }

    /**
     * Clears all the tables
     */
    @Override
    public void clearTable() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "DELETE from players";
            stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
