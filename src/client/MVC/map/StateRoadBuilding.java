package client.MVC.map;

import client.MVC.data.PlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;


public class StateRoadBuilding extends StateAbstract
{
    int RoadsLaid = 0;
    public StateRoadBuilding(IMapView view, IRobView robView)
    {
        super(view, robView);
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        boolean response = facade.canPlaceRoad(edgeLoc);
        getView().startDrop(PieceType.ROAD, PlayerInfo.getColor(), response);
        return response;
    }

    public void placeRoad(EdgeLocation edgeLoc)
    {
        getView().placeRoad(edgeLoc, PlayerInfo.getColor());
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {

    }

    public void cancelMove()
    {

    }
}
