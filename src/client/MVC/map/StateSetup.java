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

        //players start with 15 roads and 5 settlements
        int pid = facade.getPlayerIndex();
        int roads = facade.getGameModel().getPlayers().get(pid).getRoads();
        int settlements = facade.getGameModel().getPlayers().get(pid).getSettlements();
        if(roads == 15 && settlements == 5)
            startMove(PieceType.ROAD, true, true);
        else if(roads == 14 && settlements == 4)
            startMove(PieceType.ROAD, true, true);
        else
            startMove(PieceType.SETTLEMENT, true, false);
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        if(setRoad)
            return false;
        return Facade.getInstance().canPlaceRoadSetup(edgeLoc.getNormalizedLocation());
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
        Facade.getInstance().retrieveGameModel();
        startMove(PieceType.SETTLEMENT, true, false);
    }

    public void placeSettlement(VertexLocation vertLoc)
    {
        view.placeSettlement(vertLoc, color);
        setSettlement = true;
        Facade.getInstance().placeSettlement(Facade.getInstance().getPlayerID(), vertLoc.getNormalizedLocation(), true);
        Facade.getInstance().FinishTurn(Facade.getInstance().getPlayerIndex());
        Facade.getInstance().retrieveGameModel();
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {
        color = Facade.getInstance().getPlayerColor(Facade.getInstance().getPlayerIndex());
        view.startDrop(pieceType, color, false);
    }

    public boolean finishedSetup()
    {
        return (setRoad && setSettlement);
    }

    public String getName() {return "Setup"; }
}
