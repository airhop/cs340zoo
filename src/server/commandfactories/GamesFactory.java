package server.commandfactories;

import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.commandobjects.games.*;
import server.servermain.JsonConstructionInfo;
import shared.infoobjects.CurrentResources;

/**
 * Created by Joshua on 3/10/2016.
 */
public class GamesFactory {
    public GamesFactory() {

    }

    /**
     * This is the method that you call when you want a Command object that is in the games category
     *
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info) {
        ICommand commandObject;
        commandObject = new Create(true, true, true, "GameName");

        return commandObject;
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Create makeCreate(JsonConstructionInfo info) {
        return new Create(true, true, true, "GameName");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Join makeJoin(JsonConstructionInfo info) {
        return new Join(1, "");
    }
}
