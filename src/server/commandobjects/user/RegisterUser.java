package server.commandobjects.user;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.shared.CommandType;
import shared.jsonobject.Login;

/**
 * Created by airho on 3/9/2016.
 */
public class RegisterUser implements ICommand {
    private String username;
    private String password;

    public RegisterUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Calls on server facade and allows for the registration
     * of new users
     */
    @Override
    public Object execute() {
        return ServerFacade.getInstance().userRegister(username, password);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.register.toString();
    }
    public int getPID() {return -1;}
}
