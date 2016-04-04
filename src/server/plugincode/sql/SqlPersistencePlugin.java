package server.plugincode.sql;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

/**
 * Created by Joshua on 4/2/2016.
 */
public class SqlPersistencePlugin implements IPersistencePlugin {


    public SqlPersistencePlugin(){
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Starts a transaction on the dataBase
     */
    @Override
    public void startTransaction() {

    }

    /**
     * Ends the transaction on the dataBase, committing or not
     * @param commit Whether or not you are going to commit
     */
    @Override
    public void endTransaction(boolean commit) {

    }

    /**
     * Abstract factory for the CommandDAO
     * @return Returns the DAO
     */
    @Override
    public ICommandDAO getCommandDAO() {
        return null;
    }

    /**
     * Abstract factory for the PlayerDAO
     * @return Returns the DAO
     */
    @Override
    public IPlayerDAO getPlayerDAO() {
        return null;
    }

    /**
     * Abstract factory for the GameDAO
     * @return Returns the DAO
     */
    @Override
    public IGameDAO getGameDAO() {
        return null;
    }
}
