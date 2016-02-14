package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class StateSetup extends StateAbstract
{
    public StateSetup(){}

    public boolean canPlaceRoad(EdgeLocation edgeLoc){return false;}

    public boolean canPlaceSettlement(VertexLocation vertLoc){return false;}

    public void placeRoad(EdgeLocation edgeLoc){}

    public void placeSettlement(VertexLocation vertLoc){}

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}

    public void cancelMove(){}

}
