package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class StateSetup extends StateAbstract
{
    private IMapView view;
    private IRobView robView;
    CatanColor color;
    Facade facade;
    boolean setRoad = false, setSettlement = false;

    public StateSetup(IMapView v, IRobView rv)
    {
        view = v;
        robView = rv;
        facade = Facade.getInstance();
        color = facade.getCurrentPlayer().getColor();
        startMove(PieceType.ROAD, true, true);
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        if(setRoad)
            return false;
        return Facade.getInstance().canPlaceRoad(edgeLoc.getNormalizedLocation(), true);
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc)
    {
        if(setSettlement)
            return false;
      return Facade.getInstance().canPlaceSettlement(vertLoc.getNormalizedLocation());
    }

    public void placeRoad(EdgeLocation edgeLoc)
    {
        view.placeRoad(edgeLoc, color);
        setRoad = true;
        Facade.getInstance().placeRoad(Facade.getInstance().getPlayerID(), edgeLoc.getNormalizedLocation(), true, true);
        startMove(PieceType.SETTLEMENT, true, false);
    }

    public void placeSettlement(VertexLocation vertLoc)
    {
        view.placeSettlement(vertLoc, color);
        setSettlement = true;
        Facade.getInstance().placeSettlement(Facade.getInstance().getPlayerID(), vertLoc.getNormalizedLocation(), true);
        int pid = Facade.getInstance().getPlayerID();
        Facade.getInstance().FinishTurn(pid);
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {
        color = Facade.getInstance().getPlayerColor(Facade.getInstance().getPlayerID());
        view.startDrop(pieceType, color, true);
    }

    public boolean finishedSetup()
    {
        return (setRoad && setSettlement);
    }
//    public void cancelMove()
//    {
//        view.cancelDrop();
//    }

    public String getName() {return "Setup"; }
}
