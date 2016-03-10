package server.commandobjects.games;

import server.commandobjects.ICommand;

/**
 * Created by airho on 3/9/2016.
 */
public class Join implements ICommand {

    private int id;
    private String color;

    public Join(int id, String color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public void execute() {

    }
}
