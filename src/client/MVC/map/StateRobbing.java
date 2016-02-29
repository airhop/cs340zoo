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

        Set<Integer> people = new HashSet<Integer>();
        for(VertexObject obj : objects)
        {
            obj.getOwner();
        }
        RobPlayerInfo[] players = new RobPlayerInfo[people.size()];
        int i=0;
        for(int x : people)
        {
            ArrayList<Player> playas = Facade.getInstance().getGameModel().getPlayers();
            for(Player player : playas)
            {
                if(player.getPlayerIndex() == x)
                {
                    players[i] = new RobPlayerInfo(player.getPlayerID(), player.getPlayerIndex(), player.getUsername(), CatanColor.convert(player.getColor()), 2);
                }
            }
            i++;
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
        System.out.println("TRYING TO PLACE ROBBERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        objects = Facade.getInstance().getVObjectsAroundHexlocation(hexLoc);
        for(VertexObject o : objects)
        {
            System.out.println("OWNER INDEX: "+o.getOwner());
        }
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
