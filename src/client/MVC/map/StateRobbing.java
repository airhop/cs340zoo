package client.MVC.map;

import client.MVC.data.PlayerInfo;
import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import client.model.map.Settlement;
import client.model.map.VertexObject;
import client.model.player.Player;
import client.proxy.Proxy;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StateRobbing extends StateAbstract
{
    private IMapView view;
    private IRobView robView;
    private HexLocation hl;
    private int victim;
    private List<VertexObject> objects = new ArrayList<>();


    public StateRobbing(IMapView v, IRobView rv)
    {
        view = v;
        robView = rv;
        v.startDrop(PieceType.ROBBER, CatanColor.BLUE, false);
    }

    //if soldier is played can the cancelMove possibly be called, because during a roll 7 it can
//    public void cancelMove(){}
    @Override
    public void robPlayer(RobPlayerInfo victim)
    {
        this.victim = victim.getPlayerIndex();
        int pid = Facade.getInstance().getPlayerIndex();
        Facade.getInstance().rob(pid, victim.getPlayerIndex(), hl);
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
        objects = Facade.getInstance().getVObjectsAroundHexlocation(hexLoc);
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
        hl=hexLoc;
        Set<Integer> people = new HashSet<Integer>();
        for(VertexObject obj : objects)
        {
            people.add(obj.getOwner());
        }
        RobPlayerInfo[] players = null;
        boolean inThere = false;
        for(int x : people)
        {
            if(x == Facade.getInstance().getCurrentPlayer().getPlayerIndex()) {
                inThere = true;
            }
        }
        if(inThere)
        {
            players = new RobPlayerInfo[people.size()-1];
        }
        else
        {
            players = new RobPlayerInfo[people.size()];
        }
        int i=0;
        int j=0;
        for(int x : people)
        {
            if(x != Facade.getInstance().getCurrentPlayer().getPlayerIndex()) {
                ArrayList<Player> playas = Facade.getInstance().getGameModel().getPlayers();
                for (Player player : playas) {
                    if (player.getPlayerIndex() == x && player.getResources().getSize() > 0) {
                        players[j] = new RobPlayerInfo(player.getPlayerID(), player.getPlayerIndex(), player.getUsername(), CatanColor.convert(player.getColor()), player.getResources().getSize());
                        j++;
                    }
                }
            }
            i++;
        }
        robView.setPlayers(players);
        robView.showModal();
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
