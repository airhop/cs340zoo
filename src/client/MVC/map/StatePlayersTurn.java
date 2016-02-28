package client.MVC.map;

import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;


public class StatePlayersTurn extends StateAbstract
{
    private IMapView view;
    public StatePlayersTurn(IMapView v, IRobView rv)
    {
        view = v;
    }

    public String getName() { return "Playing"; }
}
