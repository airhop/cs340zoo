package server.commandobjects;

/**
 * Created by airho on 3/9/2016.
 */
public interface ICommand {
    /**
     * Calls on server facade and performs the necessary action based
     * on the command object type
     */
    void execute();

    /**
     * Calls on server facade and reverses the action done
     * by the execute method
     */
    void unexecute();
}
