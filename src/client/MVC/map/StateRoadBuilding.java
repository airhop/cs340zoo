package client.MVC.map;

import client.MVC.data.PlayerInfo;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;


public class StateRoadBuilding extends StateAbstract
{
    int RoadsLaid = 0;
    private IMapView view;
    CatanColor color;
    public StateRoadBuilding(IMapView v, IRobView robView)
    {
        view = v;
     //   color = facade.getCatanColor();
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        return true;
       //return facade.canPlaceRoad(edgeLoc);
    }

    public void placeRoad(EdgeLocation edgeLoc)
    {
        view.placeRoad(edgeLoc, color);
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {
        view.startDrop(pieceType, color, true);
    }

    public void cancelMove()
    {

    }
}
