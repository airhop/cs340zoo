package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import shared.locations.HexLocation;

/**
 * Interface for the rob view, which lets the user select a player to rob
 */
public interface IRobView extends IOverlayView {

    void setPlayers(RobPlayerInfo[] candidateVictims);
}

