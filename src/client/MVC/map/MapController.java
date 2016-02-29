package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import client.facade.Facade;
import client.model.GameModel;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.*;
import client.model.map.*;

import java.util.ArrayList;
import java.util.Observable;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {

    private IRobView robView;
    private StateAbstract state;
    private int playing; //needed for debugging when playing by yourself
    private int secondRound = 0;

    public MapController(IMapView view, IRobView robView) {

        super(view);
        setRobView(robView);
        state = new StateDefault(view, robView);
        Facade.getInstance().addObserver(this);
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

    public boolean finishedSetup() {
        StateSetup s = new StateSetup(getView(), robView);
        if (s.getClass() != state.getClass())
            return false;
        return ((StateSetup) state).finishedSetup();
    }

    private void changeState() {

        Facade facade = Facade.getInstance();
        String s = facade.getGameModel().getTurnTracker().getStatus();
        int pid = facade.getPlayerID();
        // System.out.println(s + " " + pid);

        if (s.equalsIgnoreCase("FirstTurn") || s.equalsIgnoreCase("SecondTurn")) {
            state = new StateSetup(getView(), robView);
            if (((StateSetup) state).finishedSetup()) {
                state = new StateDefault(getView(), robView);
                facade.FinishTurn(pid);
            }
        }
        if (s.equalsIgnoreCase("Rolling")) {
            state = new StatePlayersTurn(getView(), robView);
            Facade.getInstance().roll(7, pid); //random number
        }
        if (s.equalsIgnoreCase("Playing") || s.equalsIgnoreCase("Robbing")) {
            playing++;
            if (playing == 3) {
                playing = 0;
                state = new StateDefault(getView(), robView);

                facade.FinishTurn(pid);
            }
        }


        //switch here between the states and set it up

        //find a way to switch out of stateRoadBuilding when it has finished.
    }

    private boolean changeState(String s) {
        // System.out.println("Desired State: " + s);
//if it is in default stage go ahead and change it
        if (state.getName().equalsIgnoreCase("default")) {
            if (s.equalsIgnoreCase("FirstRound") || s.equalsIgnoreCase("SecondRound"))
                state = new StateSetup(getView(), robView);
            else if (s.equalsIgnoreCase("RoadBuilding"))
                state = new StateRoadBuilding(getView(), robView);
            else if (s.equalsIgnoreCase("Robbing"))
                state = new StateRobbing(getView(), robView);
            else if (s.equalsIgnoreCase("playing"))
                state = new StatePlayersTurn(getView(), robView);
            else
                state = new StateDefault(getView(), robView);
            //still need to reset the map

            return true;
        }

        //if the state is going to be the same don't worry about updating it
        if (((s.equalsIgnoreCase("FirstRound") || s.equalsIgnoreCase("SecondRound") && state.getName().equalsIgnoreCase("Setup"))
                || s.equalsIgnoreCase(state.getName())))
            return false;


        //otherwise the state is changing
        if (s.equalsIgnoreCase("RoadBuilding"))
            state = new StateRoadBuilding(getView(), robView);
        else if (s.equalsIgnoreCase("Robbing"))
            state = new StateRobbing(getView(), robView);
        else if (s.equalsIgnoreCase("playing"))
            state = new StatePlayersTurn(getView(), robView);
        else
            state = new StateDefault(getView(), robView);
        return true;
    }

    public void update(Observable observable, Object args) {
        if (!Facade.getInstance().isReady())
            return;

        GameModel gm = (GameModel) observable;
        //without input it will change the phase automatically for playing with
        //changeState();

        if (state.getName().equalsIgnoreCase("Setup")) {
            if (((StateSetup) state).finishedSetup())
                state = new StateDefault(getView(), robView);
            //Facade.getInstance().FinishTurn(Facade.getInstance().getPlayerID());
            return;
        }
        //with input, it will change based on the updating to work with everyone else
        // System.out.println("Current State: " + state.getName());
        String tester = gm.getTurnTracker().getStatus();
        changeState(gm.getTurnTracker().getStatus());
//        if (!changeState(gm.getTurnTracker().getStatus()))
//            return;

        Facade facade = Facade.getInstance();
        Map map = gm.getMap();
        ArrayList<Hex> hexes = map.getHexMap();
        // System.out.println(hexes.size());
        for (int i = 0; i < hexes.size(); i++) {
            getView().addHex(hexes.get(i).getLocation(), HexType.convert(hexes.get(i).getResource()));
            getView().addNumber(hexes.get(i).getLocation(), hexes.get(i).getNumber());
        }

        ArrayList<Port> ports = map.getPorts();
        for (int i = 0; i < ports.size(); i++) {
            EdgeLocation el = new EdgeLocation(ports.get(i).getLocation(), ports.get(i).getDirection());
            getView().addPort(el, PortType.convert(ports.get(i).getResource()));
        }

        ArrayList<Road> roads = map.getRoads();
        for (int i = 0; i < roads.size(); i++) {
            getView().placeRoad(roads.get(i).getLocation(), facade.getPlayerColor(roads.get(i).getOwner()));
        }

        ArrayList<VertexObject> buildings = map.getBuildings();
        for (int i = 0; i < buildings.size(); i++) {
            if ((buildings.get(i)) instanceof Settlement)
                getView().placeSettlement(buildings.get(i).getLocation(), facade.getPlayerColor(buildings.get(i).getOwner()));
            else
                getView().placeCity(buildings.get(i).getLocation(), facade.getPlayerColor(buildings.get(i).getOwner()));
        }

        //   System.out.println("\nbuildings = " + buildings.size());
        getView().placeRobber(map.getRobber().getHl());
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

    public void cancelMove() {
        state.cancelMove();
    }

    public void playSoldierCard() {
        state.playSoldierCard();
    }

    public void playRoadBuildingCard() {
        state.playRoadBuildingCard();
    }

    public void robPlayer(RobPlayerInfo victim) {
        state.robPlayer(victim);
    }

}

///** temporary initFromModel for debugging to see if it works . . .
//
//        //<temp>
//
//        Random rand = new Random();
//
//        for (int x = 0; x <= 3; ++x) {
//
//            int maxY = 3 - x;
//            for (int y = -3; y <= maxY; ++y) {
//                int r = rand.nextInt(HexType.values().length);
//                HexType hexType = HexType.values()[r];
//                HexLocation hexLoc = new HexLocation(x, y);
//                getView().addHex(hexLoc, hexType);
//                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//                        CatanColor.RED);
//                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//                        CatanColor.BLUE);
//                getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//                        CatanColor.ORANGE);
//                getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
//                getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
//            }
//
//            if (x != 0) {
//                int minY = x - 3;
//                for (int y = minY; y <= 3; ++y) {
//                    int r = rand.nextInt(HexType.values().length);
//                    HexType hexType = HexType.values()[r];
//                    HexLocation hexLoc = new HexLocation(-x, y);
//                    getView().addHex(hexLoc, hexType);
//                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//                            CatanColor.RED);
//                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//                            CatanColor.BLUE);
//                    getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//                            CatanColor.ORANGE);
//                    getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
//                    getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
//                }
//            }
//        }
//
//        PortType portType = PortType.BRICK;
//        getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
//        getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
//        getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//        getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//        getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//        getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//
//        getView().placeRobber(new HexLocation(0, 0));
//
//        getView().addNumber(new HexLocation(-2, 0), 2);
//        getView().addNumber(new HexLocation(-2, 1), 3);
//        getView().addNumber(new HexLocation(-2, 2), 4);
//        getView().addNumber(new HexLocation(-1, 0), 5);
//        getView().addNumber(new HexLocation(-1, 1), 6);
//        getView().addNumber(new HexLocation(1, -1), 8);
//        getView().addNumber(new HexLocation(1, 0), 9);
//        getView().addNumber(new HexLocation(2, -2), 10);
//        getView().addNumber(new HexLocation(2, -1), 11);
//        getView().addNumber(new HexLocation(2, 0), 12);
//
//        //</temp>
//
// */