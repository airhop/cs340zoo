package server.commandobjects.user;

import client.model.GameModel;
import server.commandobjects.ICommand;
import server.serverfacade.ServerFacade;
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
        ServerFacade.getInstance().userRegister(username, password);
        GameModel model = ServerFacade.getInstance().getModel();
        return new Login(model.getCurrentPlayer().getUsername(), model.getCurrentPlayer().getPassword(), model.getID());
    }

    @Override
    public void unexecute() {

    }
}
