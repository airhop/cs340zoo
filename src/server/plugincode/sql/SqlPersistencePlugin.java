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

    @Override
    public void startTransaction() {

    }

    @Override
    public void endTransaction(boolean commit) {

    }

    @Override
    public ICommandDAO getCommandDAO() {
        return null;
    }

    @Override
    public IPlayerDAO getPlayerDAO() {
        return null;
    }

    @Override
    public IGameDAO getGameDAO() {
        return null;
    }
}