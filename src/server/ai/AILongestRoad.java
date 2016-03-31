package server.ai;

import client.facade.Facade;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.map.Hex;
import client.model.map.Map;
import client.model.map.VertexObject;
import client.model.player.CurrentPlayer;
import client.model.player.Player;
import server.commandobjects.ICommand;
import server.commandobjects.moves.*;
import shared.definitions.ResourceType;
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
    private Random getRandom;

    @Override
    public int getPlayerAIId() {
        return playerAIId;
    }

    @Override
    public void setPlayerAIId(int playerAIId) {
        this.playerAIId = playerAIId;
    }

    @Override
    public GameModel getMyGame() {
        return myGame;
    }

    @Override
    public void setMyGame(GameModel myGame) {
        this.myGame = myGame;
    }

    @Override
    public int getPlayerAIIndex() {
        return playerAIIndex;
    }

    @Override
    public void setPlayerAIIndex(int playerAIIndex) {
        this.playerAIIndex = playerAIIndex;
    }

    public AILongestRoad(int id, int index) {
        playerAIIndex = index;
        playerAIId = id;
        getRandom = new Random();
    }

    public AILongestRoad() {
        playerAIIndex = 0;
        playerAIId = 0;
        getRandom = new Random();
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
        ICommand curCommand;
        if (setup) {
            CurrentPlayer curPlayer = myGame.getCurrentPlayer();
            curPlayer.setPlayerId(playerAIId);
            curPlayer.setPlayerIndex(playerAIIndex);
            Facade.getInstance().setGame(myGame);
            Facade.getInstance().setCurPlayerIndex();
            curCommand = buildSetupRoad(hexMap, myMap);
            if(curCommand != null){
                curCommand.execute();
            }
            curCommand = buildSetupSettlement(hexMap, myMap);
            if(curCommand != null){
                curCommand.execute();
            }
        } else {
            int rollNum = rollAction();
            curCommand = new RollNumber(playerAIIndex, rollNum);
            myCommands.add(curCommand);
            if (rollNum == 7) {
                //Player must rob
                if (myPlayer.getResources().size() > 7) {
                    myCommands.add(getDiscard(myPlayer));
                }
                curCommand = robPlayer(hexMap, myMap);
                if (curCommand != null) {
                    myCommands.add(curCommand);
                }
            }
            if (myPlayer.getResources().getBrick() > 0 && myPlayer.getResources().getWood() > 0) {
                curCommand = buildRoad(hexMap, myMap);
                if (curCommand != null) {
                    myCommands.add(curCommand);
                    buildingRoad = true;
                }
            }
            if (buildingRoad && myPlayer.getResources().getWood() > 1 && myPlayer.getResources().getBrick() > 1 && myPlayer.getResources().getSheep() > 0 && myPlayer.getResources().getWheat() > 0) {
                myCommands.add(buildSettlement(hexMap, myMap));
            } else if (myPlayer.getResources().getWood() > 0 && myPlayer.getResources().getBrick() > 0 && myPlayer.getResources().getSheep() > 0 && myPlayer.getResources().getWheat() > 0) {
                myCommands.add(buildSettlement(hexMap, myMap));
            }
            if (myPlayer.getResources().getOre() > 0 && myPlayer.getResources().getSheep() > 0 && myPlayer.getResources().getWheat() > 0) {
                myCommands.add(new BuyDevCard(playerAIIndex));
            }
            if (myPlayer.getOldDevCards().getSize() > 0) {
                if (myPlayer.getOldDevCards().getSoldier() > 0) {
                    myCommands.add(getSoldier(hexMap, myMap));
                }
                if (myPlayer.getOldDevCards().getMonument() > 0 && myPlayer.getVictoryPoints() + myPlayer.getOldDevCards().getMonument() == 10) {
                    for (int i = 0; i < myPlayer.getOldDevCards().getMonument(); i++) {
                        myCommands.add(new Monument(playerAIIndex));
                    }
                }
                if (myPlayer.getOldDevCards().getMonopoly() > 0) {
                    int wood = 0;
                    int brick = 0;
                    for (int i = 0; i < myGame.getPlayers().size(); i++) {
                        if (i != playerAIIndex) {
                            wood += myGame.getPlayers().get(i).getResources().getWood();
                            brick += myGame.getPlayers().get(i).getResources().getBrick();
                        }
                    }
                    if (wood > brick) {
                        myCommands.add(new Monopoly(playerAIIndex, ResourceType.WOOD.toString()));
                    } else {
                        myCommands.add(new Monopoly(playerAIIndex, ResourceType.BRICK.toString()));
                    }
                }
                if (myPlayer.getOldDevCards().getYearOfPlenty() > 0) {
                    myCommands.add(new YearOfPlenty(playerAIIndex, ResourceType.WOOD, ResourceType.BRICK));
                }
                if (myPlayer.getOldDevCards().getRoadBuilding() > 0) {
                    buildRoad(hexMap, myMap).execute();
                    buildRoad(hexMap, myMap).execute();
                    myPlayer.getOldDevCards().setRoadBuilding(myPlayer.getOldDevCards().getRoadBuilding() - 1);
                    myGame.getBank().getDevCards().setRoadBuilding(myGame.getBank().getDevCards().getRoadBuilding() + 1);
                }
            }
        }
        myCommands.add(new FinishTurn(playerAIIndex));
        return myCommands;
    }

    public ICommand getDiscard(Player myPlayer) {
        ResourceList resources = new ResourceList();
        for (int i = 0; i < myPlayer.getResources().size() - 7; i++) {
            resources.addResourceType(myPlayer.getResources().getRandomCard().toString(), -1);
        }
        return new DiscardCards(playerAIIndex, resources);
    }


    public void mixHexes(List<Hex> hexMap) {

    }

    private ICommand buildSettlement(List<Hex> hexMap, Map myMap) {
        Hex myHex;
        ICommand curCommand = null;
        VertexLocation myVertex = null;
        boolean tester = true;
        int grabInt = 0;
        while (tester && grabInt < hexMap.size() + 10) {
            myHex = hexMap.get(getRandom.nextInt(hexMap.size()));
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 1:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NW);
                        break;
                    case 2:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.E);
                        break;
                    case 3:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.SE);
                        break;
                    case 4:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.SW);
                        break;
                    case 5:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.W);
                        break;
                    default:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.W);
                        break;
                }
                if (myMap.canPlaceSettlement(myVertex)) {
                    j = 6;
                    tester = false;
                }
            }
            grabInt++;
        }
        curCommand = new BuildSettlement(playerAIIndex, myVertex.getHexLoc().getX(), myVertex.getHexLoc().getY(), myVertex.getDir(), false);
        return curCommand;
    }

    private ICommand getSoldier(List<Hex> hexMap, Map myMap) {
        Hex myHex = null;
        int victim = -1;
        ICommand curCommand;
        int grabInt = 0;
        boolean tester = true;
        while (tester && grabInt < hexMap.size() + 10) {
            myHex = hexMap.get(getRandom.nextInt(hexMap.size()));
            if (myMap.canRelocateRobber(myHex.getLocation())) {
                List<VertexObject> vertexes = myMap.getVObjectsAroundHexlocation(myHex.getLocation());
                for (int j = 0; j < vertexes.size(); j++) {
                    if (vertexes.get(j).getOwner() != playerAIIndex) {
                        victim = vertexes.get(j).getOwner();
                        tester = false;
                        j = vertexes.size();
                    }
                }
            }
            grabInt++;
        }
        if (myHex != null && victim != -1) {
            curCommand = new Soldier(playerAIIndex, victim, myHex.getLocation());//index, vic, x, y
            return curCommand;
        }
        return null;
    }


    private ICommand robPlayer(List<Hex> hexMap, Map myMap) {
        Hex myHex = null;
        int victim = -1;
        ICommand curCommand;
        int grabInt = 0;
        boolean tester = true;
        while (tester && grabInt < hexMap.size() + 10) {
            myHex = hexMap.get(getRandom.nextInt(hexMap.size()));
            if (myMap.canRelocateRobber(myHex.getLocation())) {
                List<VertexObject> vertexes = myMap.getVObjectsAroundHexlocation(myHex.getLocation());
                for (int j = 0; j < vertexes.size(); j++) {
                    if (vertexes.get(j).getOwner() != playerAIIndex) {
                        victim = vertexes.get(j).getOwner();
                        tester = false;
                        j = vertexes.size();
                    }
                }

            }
            grabInt++;
        }
        if (myHex != null && victim != -1) {
            curCommand = new RobPlayer(playerAIIndex, victim, "" + myHex.getLocation().getX(), "" + myHex.getLocation().getY());//index, vic, x, y
            return curCommand;
        }
        return null;
    }

    private ICommand buildSetupSettlement(List<Hex> hexMap, Map myMap) {
        Hex myHex;
        ICommand curCommand = null;
        VertexLocation myVertex = null;
        boolean tester = true;
        int grabInt = 0;
        while (tester && grabInt < hexMap.size() + 10) {
            myHex = hexMap.get(getRandom.nextInt(hexMap.size()));
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NE);
                        break;
                    case 1:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.NW);
                        break;
                    case 2:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.E);
                        break;
                    case 3:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.SE);
                        break;
                    case 4:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.SW);
                        break;
                    case 5:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.W);
                        break;
                    default:
                        myVertex = new VertexLocation(myHex.getLocation(), VertexDirection.W);
                        break;
                }
                if (myMap.canPlaceSettlement(myVertex)) {
                    j = 6;
                    tester = false;
                }
            }
            grabInt++;
        }
        curCommand = new BuildSettlement(playerAIIndex, myVertex.getHexLoc().getX(), myVertex.getHexLoc().getY(), myVertex.getDir(), true);
        return curCommand;
    }

    private ICommand buildSetupRoad(List<Hex> hexMap, Map myMap) {
        EdgeLocation myEdge = null;
        ICommand curCommand = null;
        boolean tester = true;
        int testNum = 0;
        int grabInt = 0;
        while (tester && grabInt < hexMap.size() + 10) {
            testNum = getRandom.nextInt(hexMap.size());
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.N);
                        break;
                    case 1:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.NE);
                        break;
                    case 2:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.NW);
                        break;
                    case 3:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.S);
                        break;
                    case 4:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.SE);
                        break;
                    case 5:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.SW);
                        break;
                    default:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.N);
                        break;
                }
                if (myMap.canPlaceRoadSetup(myEdge)) {
                    tester = false;
                    j = 6;
                }
            }
            grabInt++;
        }
        curCommand = new BuildRoad(playerAIIndex, myEdge, true);
        return curCommand;
    }


    private ICommand buildRoad(List<Hex> hexMap, Map myMap) {
        EdgeLocation myEdge = null;
        ICommand curCommand = null;
        boolean tester = true;
        int testNum = 0;
        int grabInt = 0;
        while (tester && grabInt < hexMap.size() + 10) {
            testNum = getRandom.nextInt(hexMap.size());
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.N);
                        break;
                    case 1:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.NE);
                        break;
                    case 2:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.NW);
                        break;
                    case 3:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.S);
                        break;
                    case 4:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.SE);
                        break;
                    case 5:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.SW);
                        break;
                    default:
                        myEdge = new EdgeLocation(hexMap.get(testNum).getLocation(), EdgeDirection.N);
                        break;
                }
                if (myMap.canPlaceRoad(myEdge, true)) {
                    tester = false;
                    j = 6;
                }
            }
            grabInt++;
        }
        curCommand = new BuildRoad(playerAIIndex, myEdge, false);
        return curCommand;
    }

    private int rollAction() {
        int roll = getRandom.nextInt(6) + 1;
        roll += getRandom.nextInt(6) + 1;
        return roll;
    }
}
