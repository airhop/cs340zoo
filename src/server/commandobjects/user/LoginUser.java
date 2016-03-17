package server.commandobjects.user;

import client.facade.Facade;
import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
import server.servermain.Server;
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
        ServerFacade.getInstance().userLogin(username, password);
        GameModel model = ServerFacade.getInstance().getModel();
        return new Login(model.getCurrentPlayer().getUsername(), model.getCurrentPlayer().getPassword(), model.getID());
    }

    @Override
    public void unexecute() {

    }
}
