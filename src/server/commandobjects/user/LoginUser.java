package server.commandobjects.user;

import client.facade.Facade;
import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.servermain.Server;
import server.shared.CommandType;
import shared.jsonobject.Login;

/**
 * Created by airho on 3/9/2016.
 */
public class LoginUser implements ICommand {

    private String username;
    private String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Calls on server facade and allows the user to log in to
     * the server and choose a game to join, or create a game
     */
    @Override
    public Object execute() {
        return ServerFacade.getInstance().userLogin(username, password);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getType() {
        return CommandType.login.toString();
    }
    public int getPID() {return -1;}
}
