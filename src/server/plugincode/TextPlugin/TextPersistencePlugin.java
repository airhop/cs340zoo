package server.plugincode.TextPlugin;

import server.plugincode.iplugin.ICommandDAO;
import server.plugincode.iplugin.IGameDAO;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.iplugin.IPlayerDAO;
import server.plugincode.mongodb.GameDAO;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TextPersistencePlugin implements IPersistencePlugin {


    private TextGameDAO games;
    private TextPlayerDAO players;
    private TextCommandDAO commands;

    public TextPersistencePlugin()
    {
        File dir = new File("TextData");
        if(!dir.exists())
            dir.mkdir();

        games = new TextGameDAO();
        players = new TextPlayerDAO();
        commands = new TextCommandDAO();

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
        return commands;
    }

    /**
     * Abstract factory for the PlayerDAO
     * @return Returns the DAO
     */
    @Override
    public IPlayerDAO getPlayerDAO() {
        return players;
    }

    /**
     * Abstract factory for the GameDAO
     * @return Returns the DAO
     */
    @Override
    public IGameDAO getGameDAO() {
        return games;
    }
}
