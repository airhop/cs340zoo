package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public class StateRobbing extends StateAbstract
{
    private IMapView view;
    private IRobView robView;

    public StateRobbing(IMapView v, IRobView rv)
    {
        view = v;
        robView = rv;
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}

    public void cancelMove(){}

    public void robPlayer(RobPlayerInfo victim) {}

    public boolean canPlaceRobber(HexLocation hexLoc) {return false;}

    public void placeRobber(HexLocation hexLoc) {}

}
