package client.MVC.map;


import client.MVC.data.RobPlayerInfo;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface StateInterface
{
    public void initFromModel();

    public boolean canPlaceRoad(EdgeLocation edgeLoc);

    public boolean canPlaceSettlement(VertexLocation vertLoc);

    public boolean canPlaceCity(VertexLocation vertLoc);

    public boolean canPlaceRobber(HexLocation hexLoc);

    public void placeRoad(EdgeLocation edgeLoc);

    public void placeSettlement(VertexLocation vertLoc);

    public void placeCity(VertexLocation vertLoc);

    public void placeRobber(HexLocation hexLoc);

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected);

    public void cancelMove();

    public void playSoldierCard();

    public void playRoadBuildingCard();

    public void robPlayer(RobPlayerInfo victim);

}
