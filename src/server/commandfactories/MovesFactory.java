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
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand commandObject;
        commandObject = new AcceptTrade(1, true);
//        switch (){
//
//        }

        return commandObject;
    }
}
