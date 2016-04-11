package server.plugincode.sql;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

import java.io.File;
import java.sql.*;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlPersistencePlugin implements IPersistencePlugin {
    private ICommandDAO commandDao;
    private IGameDAO gameDAO;
    private IPlayerDAO playerDAO;
    private static Connection connection;

    private static final String DATABASE_DIRECTORY = "database";
    private static final String DATABASE_FILE = "Project1.sqlite";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_DIRECTORY +
            File.separator + DATABASE_FILE;

    public static Connection getConnection() {
        return connection;
    }

    public SqlPersistencePlugin() {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            System.out.println("THISDFSDFLKJHSDLFKJ");
            //connection = DriverManager.getConnection(DATABASE_URL);
        } catch (ClassNotFoundException e) {// | SQLException e) {
            System.out.println(e.toString());
        }

        commandDao = new SqlCommandDAO();
        gameDAO = new SqlGameDAO();
        playerDAO = new SqlPlayerDAO();

    }

    /**
     * Starts a transaction on the dataBase
     */
    @Override
    public void startTransaction() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL);
                if (connection == null) {
                    System.out.println("DIE");
                } else {
                    System.out.println("real");
                    //Check table else create them
                    String query;
                    PreparedStatement stmt;
                    ResultSet rs;
                    try {
                        query = "SELECT name FROM sqlite_master WHERE type='table' AND name='players'";
                        stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
                        rs = stmt.executeQuery();
                        if (!rs.next()) {
                            query = "CREATE TABLE players " +
                                    "(PlayerId INT NOT NULL," +
                                    " Username     varchar(32)    NOT NULL, " +
                                    " Password     varchar(32)     NOT NULL)";
                            stmt = connection.prepareStatement(query);
                            stmt.execute();
                        }

                        query = "SELECT name FROM sqlite_master WHERE type='table' AND name='games'";
                        stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
                        stmt.executeQuery();
                        if (!rs.next()) {
                            query = "CREATE TABLE games " +
                                    "(GameId INT PRIMARY KEY     NOT NULL," +
                                    " GameName varchar(64)    NOT NULL, " +
                                    " Game   varchar(16384)     NOT NULL)";
                            stmt = connection.prepareStatement(query);
                            stmt.execute();
                        }

                        query = "SELECT name FROM sqlite_master WHERE type='table' AND name='commands'";
                        stmt = SqlPersistencePlugin.getConnection().prepareStatement(query);
                        rs = stmt.executeQuery();
                        if (!rs.next()) {
                            query = "CREATE TABLE commands " +
                                    "(GameId INT   NOT NULL," +
                                    " CommandType  varchar(64)    NOT NULL, " +
                                    " Command      varchar(512)     NOT NULL)";
                            stmt = connection.prepareStatement(query);
                            stmt.execute();
                        }
                    } catch (SQLException e) {
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ends the transaction on the dataBase, committing or not
     *
     * @param commit Whether or not you are going to commit
     */
    @Override
    public void endTransaction(boolean commit) {
        if (connection != null) {
            try {
                if (commit) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                System.out.println("Could not end transaction");
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }

    /**
     * Abstract factory for the CommandDAO
     *
     * @return Returns the DAO
     */
    @Override
    public ICommandDAO getCommandDAO() {
        return commandDao;
    }

    /**
     * Abstract factory for the PlayerDAO
     *
     * @return Returns the DAO
     */
    @Override
    public IPlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    /**
     * Abstract factory for the GameDAO
     *
     * @return Returns the DAO
     */
    @Override
    public IGameDAO getGameDAO() {
        return gameDAO;
    }
}
