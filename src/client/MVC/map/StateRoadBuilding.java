package client.MVC.map;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;

/**
 * Created by GaryPetersen on 2/13/2016.
 */
public class StateRoadBuilding extends StateAbstract
{
    int RoadsLaid = 0;


    public boolean canPlaceRoad(EdgeLocation edgeLoc) {return false;}
    public void placeRoad(EdgeLocation edgeLoc){}
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}
    public void cancelMove(){}
}
