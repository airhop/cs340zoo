package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public class StatePlayersTurn extends StateAbstract
{
    private IMapView view;
    public StatePlayersTurn(IMapView v, IRobView rv)
    {
        view = v;
    }

    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return false;
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc) {

    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {

    }

    @Override
    public void placeCity(VertexLocation vertLoc) {

    }

    @Override
    public void placeRobber(HexLocation hexLoc) {

    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

    }

    @Override
    public void cancelMove() {

    }

    @Override
    public void robPlayer(RobPlayerInfo victim)
    {

    }

    @Override
    public void playSoldierCard() {

    }

    @Override
    public void playRoadBuildingCard() {}
    @Override
    public String getName() { return "Playing"; }
}

//    public boolean canPlaceRoad(EdgeLocation edgeLoc) {return false;}
//    public boolean canPlaceSettlement(VertexLocation vertLoc){return false;}
//    public boolean canPlaceCity(VertexLocation vertLoc) {return false;}
//    public boolean canPlaceRobber(HexLocation hexLoc) {return false;}
//    public void placeRoad(EdgeLocation edgeLoc){}
//    public void placeSettlement(VertexLocation vertLoc){}
//    public void placeCity(VertexLocation vertLoc) {}
//    public void placeRobber(HexLocation hexLoc) {}
//    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){}
//    public void cancelMove(){}
//    public void robPlayer(RobPlayerInfo victim) {}
//    public void playSoldierCard() {}