package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.*;

import java.util.Random;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {

    private IRobView robView;
    private StateAbstract state;

    public MapController(IMapView view, IRobView robView) {

        super(view);
        setRobView(robView);
        initFromModel();
        state = new StateDefault();
    }

    public IMapView getView() {

        return (IMapView) super.getView();
    }

    private IRobView getRobView() {
        return robView;
    }

    private void setRobView(IRobView robView) {
        this.robView = robView;
    }

    protected void initFromModel()
    {
        //change the state here . . .
        state.initFromModel();
    }


    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return state.canPlaceRoad(edgeLoc);
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return state.canPlaceSettlement(vertLoc);
    }

    public boolean canPlaceCity(VertexLocation vertLoc) {
        return state.canPlaceCity(vertLoc);
    }

    public boolean canPlaceRobber(HexLocation hexLoc) {
        return state.canPlaceRobber(hexLoc);
    }

    public void placeRoad(EdgeLocation edgeLoc) {
        state.placeRoad(edgeLoc);
//        getView().placeRoad(edgeLoc, CatanColor.ORANGE);
    }

    public void placeSettlement(VertexLocation vertLoc) {
        state.placeSettlement(vertLoc);
//        getView().placeSettlement(vertLoc, CatanColor.ORANGE);
    }

    public void placeCity(VertexLocation vertLoc) {
        state.placeCity(vertLoc);
//        getView().placeCity(vertLoc, CatanColor.ORANGE);
    }

    public void placeRobber(HexLocation hexLoc) {
        state.placeRobber(hexLoc);
//        getView().placeRobber(hexLoc);
//        getRobView().showModal();
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {
        state.startMove(pieceType, isFree, allowDisconnected);
//        getView().startDrop(pieceType, CatanColor.ORANGE, true);
    }

    public void cancelMove() { state.cancelMove();     }

    public void playSoldierCard() { state.playSoldierCard();    }

    public void playRoadBuildingCard() { state.playRoadBuildingCard();   }

    public void robPlayer(RobPlayerInfo victim) { state.robPlayer(victim);    }

}

/* temporary initFromModel for debugging to see if it works . . .

        //<temp>

        Random rand = new Random();

        for (int x = 0; x <= 3; ++x) {

            int maxY = 3 - x;
            for (int y = -3; y <= maxY; ++y) {
                int r = rand.nextInt(HexType.values().length);
                HexType hexType = HexType.values()[r];
                HexLocation hexLoc = new HexLocation(x, y);
                getView().addHex(hexLoc, hexType);
                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
                        CatanColor.RED);
                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
                        CatanColor.BLUE);
                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
                        CatanColor.ORANGE);
                getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
                getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
            }

            if (x != 0) {
                int minY = x - 3;
                for (int y = minY; y <= 3; ++y) {
                    int r = rand.nextInt(HexType.values().length);
                    HexType hexType = HexType.values()[r];
                    HexLocation hexLoc = new HexLocation(-x, y);
                    getView().addHex(hexLoc, hexType);
                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
                            CatanColor.RED);
                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
                            CatanColor.BLUE);
                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
                            CatanColor.ORANGE);
                    getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
                    getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
                }
            }
        }

        PortType portType = PortType.BRICK;
        getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
        getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
        getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
        getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
        getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
        getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);

        getView().placeRobber(new HexLocation(0, 0));

        getView().addNumber(new HexLocation(-2, 0), 2);
        getView().addNumber(new HexLocation(-2, 1), 3);
        getView().addNumber(new HexLocation(-2, 2), 4);
        getView().addNumber(new HexLocation(-1, 0), 5);
        getView().addNumber(new HexLocation(-1, 1), 6);
        getView().addNumber(new HexLocation(1, -1), 8);
        getView().addNumber(new HexLocation(1, 0), 9);
        getView().addNumber(new HexLocation(2, -2), 10);
        getView().addNumber(new HexLocation(2, -1), 11);
        getView().addNumber(new HexLocation(2, 0), 12);

        //</temp>

 */