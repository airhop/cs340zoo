package client.MVC.map;

import client.MVC.base.OverlayView;
import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import client.model.map.VertexObject;
import client.model.player.Player;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StatePlayersTurn extends StateAbstract
{
    private IMapView view;
    private CatanColor color;
    private IRobView robView;
    private List<VertexObject> objects = new ArrayList<>();
    private HexLocation RobberHL;

    public StatePlayersTurn(IMapView v, IRobView rv)
    {
        view = v;
        color = Facade.getInstance().getCurrentPlayer().getColor();
        robView = rv;
    }

    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        return Facade.getInstance().canPlaceRoad(edgeLoc.getNormalizedLocation(), true);
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc)
    {
        return Facade.getInstance().canPlaceSettlement(vertLoc);
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc)
    {
        return Facade.getInstance().canPlaceCity(vertLoc);
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc)
    {
        objects = Facade.getInstance().getVObjectsAroundHexlocation(hexLoc);
        return Facade.getInstance().canMoveRobber(hexLoc);
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc)
    {
        System.out.println(color.toString());
        view.placeRoad(edgeLoc, color);
        Facade.getInstance().placeRoad(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), edgeLoc.getNormalizedLocation(), false, true);
        Facade.getInstance().retrieveGameModel();
        view.closeModal();
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc)
    {
        view.placeSettlement(vertLoc, color);
        Facade.getInstance().placeSettlement(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), vertLoc.getNormalizedLocation(), false);
        Facade.getInstance().retrieveGameModel();
        view.closeModal();
    }

    @Override
    public void placeCity(VertexLocation vertLoc)
    {
        view.placeCity(vertLoc, color);
        Facade.getInstance().placeCity(Facade.getInstance().getCurrentPlayer().getPlayerIndex(), vertLoc.getNormalizedLocation());
        view.closeModal();
    }

    @Override
    public void placeRobber(HexLocation hexLoc)
    {
        RobberHL =hexLoc;
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
        else {
            players = new RobPlayerInfo[people.size()];
        }
        int i=0;
        int j=0;
        for(int x : people)
        {
            if(x != Facade.getInstance().getCurrentPlayer().getPlayerIndex()) {
                ArrayList<Player> playas = Facade.getInstance().getGameModel().getPlayers();
                for (Player player : playas) {
                    if (player.getPlayerIndex() == x) {
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
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {
        System.out.println("AND IN HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEERE");
        System.out.println("COLOR MAYBE: "+color.toString());
        System.out.println("COLOR MAYBE PLAYER: "+Facade.getInstance().getCurrentPlayer().getColor().toString());
        view.startDrop(pieceType, color, true);
    }

    @Override
    public void cancelMove()
    {
        view.closeModal();
    }

    @Override
    public void robPlayer(RobPlayerInfo victim)
    {
        int vid = victim.getPlayerIndex();
        int pid = Facade.getInstance().getPlayerIndex();
//        System.out.println();
        Facade.getInstance().rob(pid, victim.getPlayerIndex(), RobberHL);
        robView.closeModal();
    }

    @Override
    public void playSoldierCard()
    {
        startMove(PieceType.ROBBER, true, true);
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