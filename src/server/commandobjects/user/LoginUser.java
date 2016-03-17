package server.commandobjects.user;

import server.commandobjects.ICommand;

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
        return null;
    }

    @Override
    public void unexecute() {

    }
}
