package server.plugincode.iplugin;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IPersistencePlugin {
    void startTransaction();
    void endTransaction();
    ICommandDAO getCommandDAO();
    IPlayerDAO getPlayerDAO();
    IGameDAO getGameDAO();
}
