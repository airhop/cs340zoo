package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public class StateRobbing extends StateAbstract
{
    public StateRobbing(IMapView view, IRobView robView)
    {
        super(view, robView);
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}

    public void cancelMove(){}

    public void robPlayer(RobPlayerInfo victim) {}

    public boolean canPlaceRobber(HexLocation hexLoc) {return false;}

    public void placeRobber(HexLocation hexLoc) {}

}
