package client.MVC.map;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;


public class StatePlayersTurn
{
    private IMapView view;
    public StatePlayersTurn(IMapView v, IRobView rv)
    {
        view = v;
    }

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
