package server.ai;

import client.model.GameModel;
import client.model.map.Hex;
import client.model.map.Map;
import client.model.map.VertexObject;
import client.model.player.Player;
import server.commandobjects.ICommand;
import server.commandobjects.moves.BuildRoad;
import server.commandobjects.moves.RobPlayer;
import server.commandobjects.moves.RollNumber;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Joshua on 3/28/2016.
 */
public class AILongestRoad implements IAIntel {
    private GameModel myGame;
    private int playerAIIndex;
    private int playerAIId;
    private boolean buildingRoad;

    public AILongestRoad(int id, int index) {
        playerAIIndex = index;
        playerAIId = id;
    }


    @Override
    public void gameToActOn(GameModel game) {
        myGame = game;
    }

    @Override
    public List<ICommand> buildTurn(boolean setup) {
        List<ICommand> myCommands = new ArrayList<>();
        Player myPlayer = myGame.getPlayers().get(playerAIIndex);
        buildingRoad = false;
        Map myMap = myGame.getMap();
        List<Hex> hexMap = myMap.getHexMap();
        int rollNum = rollAction();
        ICommand curCommand = new RollNumber(rollNum, playerAIIndex);
        myCommands.add(curCommand);
        if (rollNum == 7) {
            //Player must rob
            curCommand = robPlayer(hexMap, myMap);
            if(curCommand != null){
                myCommands.add(curCommand);
            }
        }

        if (myPlayer.getResources().getBrick() > 0 && myPlayer.getResources().getWood() > 0) {
            curCommand = buildRoad(hexMap, myMap);
            if(curCommand != null){
                myCommands.add(curCommand);
                buildingRoad = true;
            }
        }
        if(buildingRoad && myPlayer.getResources().getWood() > 1 && myPlayer.getResources().getBrick() > 1 && myPlayer.getResources().getSheep() > 0 && myPlayer.getResources().getWheat() > 0 ){

        }else if(myPlayer.getResources().getWood() > 0 && myPlayer.getResources().getBrick() > 0 && myPlayer.getResources().getSheep() > 0 && myPlayer.getResources().getWheat() > 0 ){

        }

        return myCommands;
    }

    private ICommand buildSettlement(List<Hex> hexMap, Map myMap){
        Hex myHex = null;
        int victim = -1;
        ICommand curCommand;
        VertexLocation myVertex = null;
        for(int i = 0; i < hexMap.size(); i++){
            myHex = hexMap.get(i);

            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 1:
                        myVertex  = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 2:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 3:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 4:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 5:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    default:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                }
                if (myMap.canPlaceSettlement(myVertex)) {
                    i = hexMap.size();
                    j = 6;
                }
            }
        }
        return null;
    }



    private ICommand robPlayer(List<Hex> hexMap, Map myMap){
        Hex myHex = null;
        int victim = -1;
        ICommand curCommand;
        for (int i = 0; i < hexMap.size(); i++) {
            myHex = hexMap.get(i);
            if (myMap.canRelocateRobber(myHex.getLocation())) {
                List<VertexObject> vertexes = myMap.getVObjectsAroundHexlocation(myHex.getLocation());
                for(int j = 0; j < vertexes.size(); j++){
                    if(vertexes.get(j).getOwner() != playerAIIndex){
                        victim = vertexes.get(j).getOwner();
                        i = hexMap.size();
                        j = vertexes.size();
                    }
                }

            }
        }
        if (myHex != null && victim != -1) {
            curCommand = new RobPlayer(playerAIIndex, victim, "" + myHex.getLocation().getX(), "" + myHex.getLocation().getY());//index, vic, x, y
            return curCommand;
        }
        return null;
    }

    private ICommand buildRoad(List<Hex> hexMap, Map myMap){
        EdgeLocation myEdge = null;
        ICommand curCommand = null;
        for (int i = 0; i < hexMap.size(); i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.N);
                        break;
                    case 1:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.NE);
                        break;
                    case 2:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.NW);
                        break;
                    case 3:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.S);
                        break;
                    case 4:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.SE);
                        break;
                    case 5:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.SW);
                        break;
                    default:
                        myEdge = new EdgeLocation(hexMap.get(i).getLocation(), EdgeDirection.N);
                        break;
                }
                if (myMap.canPlaceRoad(myEdge, true)) {
                    i = hexMap.size();
                    j = 6;
                }
            }
        }
        if (myEdge != null) {
            curCommand = new BuildRoad(playerAIIndex, myEdge, false);
        }
        return curCommand;
    }

    private int rollAction() {
        Random rand = new Random();
        int roll = rand.nextInt() % 6 + 1;
        roll += rand.nextInt() % 6 + 1;
        return roll;
    }
}
