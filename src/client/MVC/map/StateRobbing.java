package client.MVC.map;

import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import client.model.player.Player;
import shared.definitions.CatanColor;
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
        v.startDrop(PieceType.ROBBER, CatanColor.BLUE, false);
        RobPlayerInfo[] players = new RobPlayerInfo[3];
        int i=0;
        int j=0;
        for(Player player : Facade.getInstance().getGameModel().getPlayers())
        {
            if(Facade.getInstance().getCurrentPlayerInfo().getId() != j) {
                players[i] = new RobPlayerInfo(player.getPlayerID(), player.getPlayerIndex(), player.getUsername(), CatanColor.convert(player.getColor())/*CatanColor.BLUE*/, 2);
                i++;
            }
            j++;
        }
        rv.setPlayers(players);
    }

    //if soldier is played can the cancelMove possibly be called, because during a roll 7 it can
//    public void cancelMove(){}
    @Override
    public void robPlayer(RobPlayerInfo victim)
    {
        this.victim = victim.getId();
        int pid = Facade.getInstance().getPlayerIndex();
        Facade.getInstance().rob(pid, victim.getId(), Facade.getInstance().getGameModel().getMap().getRobber().getHl());
        robView.closeModal();
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
        Facade.getInstance().getGameModel().relocateRobber(hexLoc);
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
