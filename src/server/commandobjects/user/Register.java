package server.commandobjects.user;

import server.commandobjects.ICommand;

/**
 * Created by airho on 3/9/2016.
 */
public class Register implements ICommand {
    private String username;
    private String password;

    public Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {

    }
}
