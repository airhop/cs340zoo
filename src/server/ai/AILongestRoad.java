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

    public AILongestRoad(int id, int index) {
        playerAIIndex = index;
        playerAIId = id;
    }


    @Override
    public void gameToActOn(GameModel game) {
        myGame = game;
    }

    @Override
    public List<ICommand> buildTurn() {
        List<ICommand> myCommands = new ArrayList<>();
        Player myPlayer = myGame.getPlayers().get(playerAIIndex);
        Map myMap = myGame.getMap();
        List<Hex> hexMap = myMap.getHexMap();
        int rollNum = rollAction();
        ICommand curCommand = new RollNumber(rollNum, playerAIIndex);
        myCommands.add(curCommand);
        if (rollNum == 7) {
            //Player must rob
            EdgeLocation myEdge = null;
            Hex myHex = null;
            int victim = -1;
            for (int i = 0; i < hexMap.size(); i++) {
                myHex = hexMap.get(i);
                if (myMap.canRelocateRobber(myHex.getLocation())) {
                    List<VertexObject> vertexes = myMap.getVObjectsAroundHexlocation(myHex.getLocation());
                    for(int j = 0; j < vertexes.size(); j++){

                        if(vertexes.get(j).getOwner() != playerAIIndex){
                            vertexes.get(j).getOwner();
                            i = hexMap.size();
                            j = vertexes.size();
                        }
                    }

                }
            }
//            if (myEdge != null) {
//                curCommand = new BuildRoad(playerAIIndex, myEdge, false);
//                myCommands.add(curCommand);
//            }
//            curCommand = new RobPlayer();
//            myCommands.add(curCommand);
        }


        if (myPlayer.getResources().getBrick() > 0 && myPlayer.getResources().getWood() > 0) {
            EdgeLocation myEdge = null;
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
                myCommands.add(curCommand);
            }
        }
        return myCommands;
    }

    private int rollAction() {
        Random rand = new Random();
        int roll = rand.nextInt() % 6 + 1;
        roll += rand.nextInt() % 6 + 1;
        return roll;
    }
}
