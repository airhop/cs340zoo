package server.commandfactories;

import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.servermain.JsonConstructionInfo;

/**
 * Created by Joshua on 3/10/2016.
 */
public class MovesFactory {
    public MovesFactory(){

    }

    /**
     *
     * @param info -
     * @param globalVariable -
     * @return
     */
    public ICommand getCommand(JsonConstructionInfo info, boolean globalVariable){
        ICommand commandObject;
        if(globalVariable){
            commandObject = new AcceptTrade(1, false);
        }else{
            commandObject = new AcceptTrade(1, true);
        }
        return commandObject;
    }
}
