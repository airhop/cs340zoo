package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;


public class StateRobbing extends StateAbstract
{
    private IMapView view;
    private IRobView robView;
    private HexLocation hl;
    private int victim;

    public StateRobbing(IMapView v, IRobView rv)
    {
        view = v;
        robView = rv;
        robView.showModal();
    }

    //if soldier is played can the cancelMove possibly be called, because during a roll 7 it can
//    public void cancelMove(){}
    @Override
    public void robPlayer(RobPlayerInfo victim)
    {
        this.victim = victim.getId();
    }

    @Override
    public void playSoldierCard() {

    }

    @Override
    public void playRoadBuildingCard() {

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
    public boolean canPlaceRobber(HexLocation hexLoc)
    {
        return Facade.getInstance().canMoveRobber(hexLoc);
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
    public void placeRobber(HexLocation hexLoc)
    {
        int playerIndex = Facade.getInstance().getGameIndex();
        Facade.getInstance().rob(playerIndex, victim, hexLoc);
        robView.closeModal();
    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

    }

    @Override
    public void cancelMove() {

    }

    @Override
    public String getName()   {        return "Robbing";    }

}
