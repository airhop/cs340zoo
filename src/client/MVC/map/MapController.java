package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import client.facade.Facade;
import client.model.GameModel;
import shared.definitions.CatanColor;
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
    private IMapView mapView;
    private int playing; //needed for debugging when playing by yourself
    private int secondRound = 0;
    private boolean soldier;

    private int roundNum = 0;

    public MapController(IMapView view, IRobView robView) {
        super(view);
        mapView = view;
        setRobView(robView);
        state = new StateDefault(view, robView);
        Facade.getInstance().addObserver(this);
        soldier = false;
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
         System.out.println(s + " " + pid);

        if (s.equalsIgnoreCase("FirstTurn") || s.equalsIgnoreCase("SecondTurn"))
        {
            state = new StateSetup(getView(), robView);
            if (((StateSetup) state).finishedSetup()) {
                state = new StateDefault(getView(), robView);
                facade.FinishTurn(pid);
            }
        }
        if (s.equalsIgnoreCase("Rolling")) {
            state = new StatePlayersTurn(getView(), robView);
            Facade.getInstance().roll(7,pid); //random number
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
       // System.out.println("Map State - " + s);

        if(state.getName().equalsIgnoreCase("setup"))
        {
            if(((StateSetup)state).finishedSetup())
            {
                state = new StateDefault(getView(), robView);
                return false;
            }
            if(Facade.getInstance().isCloseMap())
                state = new StateDefault(getView(), robView);
//            System.out.println("Close Map = "  + Facade.getInstance().isCloseMap());
        }

        if(Facade.getInstance().getCurrentPlayer().getPlayerIndex() != Facade.getInstance().getGameModel().getTurnTracker().getCurrentPlayer())
        {
//            System.out.println("Not your turn!!!");
            state = new StateDefault(getView(), robView);
            return true;
        }

        if (s.equalsIgnoreCase("RoadBuilding")){
            String test = s;
        }

        if(state.getName().equalsIgnoreCase("RoadBuilding"))
        {
            if(((StateRoadBuilding)state).finished())
                state = new StatePlayersTurn(getView(), robView);
            return true;
        }
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
        {
            System.out.println(((MapView)getView()).getOverlaid());
//            if(((MapView)getView()).getOverlaid())
//            {
//                state = new StateSetup(getView(), robView);
//                return true;
//            }
//            else
                return false;
        }


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

        String testState = state.getName();

        changeState(gm.getTurnTracker().getStatus());
//        if (!changeState(gm.getTurnTracker().getStatus()))
//            return;
        Facade.getInstance().setCurPlayerIndex();
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
    }

    public void placeCity(VertexLocation vertLoc) {
        state.placeCity(vertLoc);
//        getView().placeCity(vertLoc, CatanColor.ORANGE);
    }

    public void placeRobber(HexLocation hexLoc) {
        mapView.placeRobber(hexLoc);
        state.placeRobber(hexLoc);
        getView().placeRobber(hexLoc);
//        getRobView().showModal();
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {
        state.startMove(pieceType, isFree, allowDisconnected);
    }

    public void cancelMove() {
        state.cancelMove();
    }

    public void playSoldierCard()
    {
        state = new StateRobbing(getView(), robView);
        soldier = true;
//        state.playSoldierCard();
    }

    public void playRoadBuildingCard()
    {
        state = new StateRoadBuilding(getView(), robView);
        state.playRoadBuildingCard();
    }

    public void robPlayer(RobPlayerInfo victim) {
//        soldier = false;
        if(soldier){
            soldier = false;
            state.playSoldierCard(victim);
        }else{
            state.robPlayer(victim);
        }

    }


}