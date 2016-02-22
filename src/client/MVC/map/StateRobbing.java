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

    public void robPlayer(RobPlayerInfo victim)
    {
        this.victim = victim.getId();
    }

    public boolean canPlaceRobber(HexLocation hexLoc)
    {
        return Facade.getInstance().canMoveRobber(hexLoc);
    }

    public void placeRobber(HexLocation hexLoc)
    {
        int pid = Facade.getInstance().getPlayerID();
        Facade.getInstance().rob(pid, victim, hexLoc);
        robView.closeModal();
    }

}
