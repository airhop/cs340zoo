package server.commandobjects.user;

import server.commandobjects.ICommand;

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
        return null;
    }

    @Override
    public void unexecute() {

    }
}
