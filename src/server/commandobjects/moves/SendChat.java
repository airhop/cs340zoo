package server.commandobjects.moves;

import server.commandobjects.ICommand;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class SendChat implements ICommand {
    private int playerIndex;
    private String content;

    public SendChat(int playerIndex, String content) {
        this.playerIndex = playerIndex;
        this.content = content;
    }

    /**
     * Calls on server facade and allows for communication between
     * ingame players. Adds their comment to the chatbox that all other
     * players can see
     */
    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }
}
