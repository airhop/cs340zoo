package server.plugincode.iplugin;

/**
 * Created by Joshua on 4/2/2016.
 */
public interface IPersistencePlugin {
    void initializeDB();
    void startTransaction();
    void endTransaction(boolean commit);
    ICommandDAO getCommandDAO();
    IPlayerDAO getPlayerDAO();
    IGameDAO getGameDAO();
}
