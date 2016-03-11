package server.commandfactories;

import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.commandobjects.user.LoginUser;
import server.commandobjects.user.RegisterUser;
import server.servermain.JsonConstructionInfo;

/**
 * Created by Joshua on 3/10/2016.
 */
public class UserFactory {
    public UserFactory(){

    }
    /**
     * This is the method that you call when you want a Command object that is in the User category
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand command = new AcceptTrade(1, false);
        return command;
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    private LoginUser makeLoginUser(JsonConstructionInfo info){
        return new LoginUser("", "");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    private RegisterUser makeRegisterUser(JsonConstructionInfo info){
        return new RegisterUser("", "");
    }

}
