package client.MVC.map;


import client.MVC.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public abstract class StateAbstract
{
//implement these two . . .
    public void initFromModel(){}


//don't implement . . .
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {return false;}
    public boolean canPlaceSettlement(VertexLocation vertLoc){return false;}
    public void placeRoad(EdgeLocation edgeLoc){}
    public void placeSettlement(VertexLocation vertLoc){}
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}
    public void cancelMove(){}
    public boolean canPlaceCity(VertexLocation vertLoc) {return false;}
    public void placeCity(VertexLocation vertLoc) {}
    public boolean canPlaceRobber(HexLocation hexLoc) {return false;}
    public void placeRobber(HexLocation hexLoc) {}
    public void robPlayer(RobPlayerInfo victim) {}
    public void playSoldierCard() {}
    public void playRoadBuildingCard() {}


}
