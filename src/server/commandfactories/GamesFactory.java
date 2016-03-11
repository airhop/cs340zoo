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
    public GamesFactory(){

    }

    /**
     * This is the method that you call when you want a Command object that is in the games category
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand commandObject;
        commandObject = new Create(true,true,true,"GameName");

        return commandObject;
    }
    private Create makeCreate(JsonConstructionInfo info){
        return new Create(true,true,true,"GameName");
    }
    private Join makeJoin(JsonConstructionInfo info){
        return new Join(1, "");
    }
}
