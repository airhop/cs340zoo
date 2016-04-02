package server.plugincode.sql;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;

/**
 * Created by Joshua on 4/2/2016.
 */
public class PersistencePlugin implements IPersistencePlugin {
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
