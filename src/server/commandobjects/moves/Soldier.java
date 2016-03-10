package server.commandobjects.moves;

import server.commandobjects.ICommand;
import shared.locations.HexLocation;

import java.lang.reflect.Type;

/**
 * Created by airho on 3/9/2016.
 */
public class Soldier implements ICommand {
    private Type Soldier;
    private int playerIndex;
    private int vicitmIndex;
    private HexLocation location;

    public Soldier(Type soldier, int playerIndex, int vicitmIndex, HexLocation location) {
        Soldier = soldier;
        this.playerIndex = playerIndex;
        this.vicitmIndex = vicitmIndex;
        this.location = location;
    }

    @Override
    public void execute() {

    }
}
