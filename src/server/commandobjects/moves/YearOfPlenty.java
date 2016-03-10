package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.definitions.ResourceType;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class YearOfPlenty implements ICommand {

    private Type Year_of_Plenty;
    private int playerIndex;
    private ResourceType resource1;
    private ResourceType resource2;

    public YearOfPlenty(Type year_of_Plenty, int playerIndex, ResourceType resource1, ResourceType resource2) {
        Year_of_Plenty = year_of_Plenty;
        this.playerIndex = playerIndex;
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void execute() {

    }
}
