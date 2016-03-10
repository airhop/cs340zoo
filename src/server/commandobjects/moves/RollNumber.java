package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class RollNumber implements ICommand {
    private Type RollNumber;
    private int playerIndex;
    private int number;

    public RollNumber(Type rollNumber, int playerIndex, int number) {
        this.RollNumber = rollNumber;
        this.playerIndex = playerIndex;
        this.number = number;
    }

    @Override
    public void execute() {

    }
}
