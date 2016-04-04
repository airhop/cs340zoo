package server.plugincode.sql;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PersistencePlugin implements IPersistencePlugin {


    public PersistencePlugin(){
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void initializeDB() {
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
    public void endTransaction() {

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
