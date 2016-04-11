package server.plugincode.sql;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlPersistencePlugin implements IPersistencePlugin {
    private ICommandDAO commandDao;
    private IGameDAO gameDAO;
    private IPlayerDAO playerDAO;
    private Connection connection;

    private static final String DATABASE_DIRECTORY = "database";
    private static final String DATABASE_FILE = "Project1.sqlite";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_DIRECTORY +
            File.separator + DATABASE_FILE;

    public SqlPersistencePlugin(){
//        try {
//            final String driver = "org.sqlite.JDBC";
//            Class.forName(driver);
//            //connection = DriverManager.getConnection(DATABASE_URL);
//        }
//        catch(ClassNotFoundException e){// | SQLException e) {
//            System.out.println(e.toString());
//        }

        commandDao = new SqlCommandDAO(connection);
        gameDAO = new SqlGameDAO(connection);
        playerDAO = new SqlPlayerDAO(connection);

    }

    /**
     * Starts a transaction on the dataBase
     */
    @Override
    public void startTransaction() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(DATABASE_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ends the transaction on the dataBase, committing or not
     * @param commit Whether or not you are going to commit
     */
    @Override
    public void endTransaction(boolean commit) {
        if (connection != null) {
            try {
                if (commit) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                System.out.println("Could not end transaction");
                e.printStackTrace();
            }
            finally {
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
     * @return Returns the DAO
     */
    @Override
    public ICommandDAO getCommandDAO() {
        return commandDao;
    }

    /**
     * Abstract factory for the PlayerDAO
     * @return Returns the DAO
     */
    @Override
    public IPlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    /**
     * Abstract factory for the GameDAO
     * @return Returns the DAO
     */
    @Override
    public IGameDAO getGameDAO() {
        return gameDAO;
    }
}
