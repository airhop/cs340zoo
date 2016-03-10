package server.commandfactories;

import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.servermain.JsonConstructionInfo;

/**
 * Created by Joshua on 3/10/2016.
 */
public class UserFactory {
    public UserFactory(){

    }
    /**
     *
     * @param info -
     * @return -
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand command = new AcceptTrade(1, false);
        return command;
    }
}
