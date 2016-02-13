package client.MVC.map;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * Created by GaryPetersen on 2/13/2016.
 */
public class StatePlayersTurn extends StateAbstract
{
    public StatePlayersTurn(){}

    public boolean canPlaceRoad(EdgeLocation edgeLoc) {return false;}
    public boolean canPlaceSettlement(VertexLocation vertLoc){return false;}
    public void placeRoad(EdgeLocation edgeLoc){}
    public void placeSettlement(VertexLocation vertLoc){}
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}
    public void cancelMove(){}
    public boolean canPlaceCity(VertexLocation vertLoc) {return false;}
    public void placeCity(VertexLocation vertLoc) {}
    public void playSoldierCard() {}
    public void playRoadBuildingCard() {}

}
