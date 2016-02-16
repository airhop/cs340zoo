package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import client.MVC.data.RobPlayerInfo;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class StateSetup extends StateAbstract
{
    public StateSetup(IMapView view, IRobView robView)
    {
        super(view, robView);
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        boolean response = facade.canPlaceRoad(edgeLoc);
        getView().startDrop(PieceType.ROAD, PlayerInfo.getColor(), response);
        return response;
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc)
    {
        boolean response = facade.canPlaceSettlement(vertLoc);
        getView().startDrop(PieceType.SETTLEMENT, PlayerInfo.getColor(), response);
        return response;

    }

    public void placeRoad(EdgeLocation edgeLoc)
    {
        getView().placeRoad(edgeLoc, PlayerInfo.getColor());
    }

    public void placeSettlement(VertexLocation vertLoc)
    {
        getView().placeSettlement(vertLoc, PlayerInfo.getColor());
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {

    }

    public void cancelMove()
    {

    }

}
