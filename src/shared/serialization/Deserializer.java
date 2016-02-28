package shared.serialization;

import client.model.GameModel;
import client.model.bank.Bank;
import client.model.bank.DevCardList;
import client.model.bank.ResourceList;
import client.model.history.MessageLine;
import client.model.history.MessageList;
import client.model.map.Map;
import client.model.map.Road;
import client.model.map.Settlement;
import client.model.misc.TurnTracker;
import client.model.player.Player;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import shared.exceptions.FailureToAddException;
import shared.locations.*;

import java.io.IOException;
import java.util.ArrayList;

public class Deserializer {

    /**
     * deserializes the JSON formatted data from the server
     *
     * @return object, you must cast this object to the thing you are deseralizing.
     */
    public GameModel deserialize(String jsonString, GameModel myModel) {
        GameModel gameNew = new GameModel();
        Bank myBank = new Bank();
        Map myMap = new Map();
        ResourceList myResource;
        DevCardList myDevCards = myBank.getDevCards();
        String resourceType = "";
        int xValue;
        int yValue;
        int chitValue;
        int owner;
        String myDir = "";
        int ratio;
        ArrayList<Player> players = myModel.getPlayers();
        Player currentPlayer;
        MessageList myMessages = new MessageList();
        String source = "";
        String message = "";
        TurnTracker turnTracker = new TurnTracker();
        int winner;
        int version;


        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(jsonString);
        JsonTreeReader myTree = new JsonTreeReader(myEle);
       System.out.println(jsonString);
        JsonObject myObj = new JsonObject();
        String action = "";
        String myCurrent = "";
        int myIn = 0;
        boolean myBool = false;
        //System.out.println(myTree.toString());
        try {
            while (!myTree.peek().name().equals("END_DOCUMENT")) {
//                System.out.println(myTree.peek().name());
                switch (myTree.peek().name()) {
                    case "BEGIN_OBJECT":
                        myTree.beginObject();
                        break;
                    case "NAME":
                        myCurrent = myTree.nextName();
                        switch (myCurrent) {
                            case "deck":
                                myTree.beginObject();
                                myTree.nextName();
                                myDevCards.setYearOfPlenty(myTree.nextInt());
                                myTree.nextName();
                                myDevCards.setMonopoly(myTree.nextInt());
                                myTree.nextName();
                                myDevCards.setSoldier(myTree.nextInt());
                                myTree.nextName();
                                myDevCards.setRoadBuilding(myTree.nextInt());
                                myTree.nextName();
                                myDevCards.setMonument(myTree.nextInt());
                                break;
                            case "hexes":
//                                System.out.println(myCurrent);
                                myTree.beginArray();
                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    action = myTree.nextName();
                                    switch (action) {
                                        case "resource":
                                            resourceType = myTree.nextString();//for the resource Type
                                            myTree.nextName();
                                        case "location":
                                            myTree.beginObject();
                                            myTree.nextName();
                                            xValue = myTree.nextInt();//x
                                            myTree.nextName();
                                            yValue = myTree.nextInt();//y
                                            myTree.endObject();
                                            if (myTree.peek().name().equals("NAME")) {
                                                myTree.nextName();
                                                chitValue = myTree.nextInt();
                                                myMap.addHex(xValue, yValue, resourceType, chitValue);
                                                myTree.endObject();
                                            } else {
                                                myMap.addHexDesert(xValue, yValue);
                                                myTree.endObject();
                                            }
                                            break;
                                    }
                                }
//                                System.out.println(myCurrent);
                                break;
                            case "roads":
                                myTree.beginArray();
                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    myTree.nextName();
                                    owner = myTree.nextInt();
                                    myTree.nextName();//location
                                    myTree.beginObject();
                                    myTree.nextName();
                                    myDir = myTree.nextString();
                                    myTree.nextName();//x
                                    xValue = myTree.nextInt();
                                    myTree.nextName();//y
                                    yValue = myTree.nextInt();
                                    myMap.getRoads().add(new Road(new EdgeLocation(new HexLocation(xValue, yValue), EdgeDirection.valueOf(myDir)), owner));
                                    myTree.endObject();
                                    myTree.endObject();
//                                    System.out.println(myTree.peek().name());
                                }
                                break;
                            case "cities":
                                myTree.beginArray();
                                myTree.endArray();
//                                System.out.println(myTree.peek().name());
                                break;
                            case "settlements":
                                myTree.beginArray();
                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    myTree.nextName();
                                    owner = myTree.nextInt();
                                    myTree.nextName();//location
                                    myTree.beginObject();
                                    myTree.nextName();
                                    myDir = myTree.nextString();
                                    myTree.nextName();//x
                                    xValue = myTree.nextInt();
                                    myTree.nextName();//y
                                    yValue = myTree.nextInt();
                                    myMap.getBuildings().add(new Settlement(new VertexLocation(new HexLocation(xValue, yValue), VertexDirection.valueOf(myDir)), owner));
                                    myTree.endObject();
                                    myTree.endObject();
//                                    System.out.println(myTree.peek().name());
                                }
                                break;
                            case "ports":
                                myTree.beginArray();

                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    myTree.nextName();
                                    ratio = myTree.nextInt();
                                    if (myTree.nextName().equals("resource")) {
                                        resourceType = myTree.nextString();
                                        myTree.nextName();//Dir
                                        myDir = myTree.nextString();
                                        myTree.nextName();
                                        myTree.beginObject();//location
                                        myTree.nextName();//x
                                        xValue = myTree.nextInt();
                                        myTree.nextName();//y
                                        yValue = myTree.nextInt();
                                        myMap.addPort(xValue, yValue, resourceType, EdgeDirection.valueOf(myDir), ratio);
                                    } else {
                                        myDir = myTree.nextString();
                                        myTree.nextName();
                                        myTree.beginObject();//location
                                        myTree.nextName();//x
                                        xValue = myTree.nextInt();
                                        myTree.nextName();//y
                                        yValue = myTree.nextInt();
                                        myMap.addPort(xValue, yValue, "THREE", EdgeDirection.valueOf(myDir), ratio);
                                    }
                                    myTree.endObject();
                                    myTree.endObject();
//                                    System.out.println(myTree.peek().name());
                                }
//                                System.out.println(myTree.peek().name());
                                break;
                            case "robber":
                                myTree.beginObject();
                                myTree.nextName();
                                xValue = myTree.nextInt();
                                myTree.nextName();
                                yValue = myTree.nextInt();
                                myMap.getRobber().setHl(new HexLocation(xValue, yValue));
                                myTree.endObject();
//                                System.out.println(myTree.peek().name());
                                break;
                            case "players":
                                myTree.beginArray();
                                for (int i = 0; i < 4; i++) {
                                    currentPlayer = players.get(i);
                                    myResource = currentPlayer.getResources();
                                    myDevCards = currentPlayer.getOldDevCards();
                                    if (myTree.peek().name().equals("BEGIN_OBJECT")) {
                                        myTree.beginObject();
                                        myTree.nextName();
                                        myTree.beginObject();
                                        myTree.nextName();
                                        myResource.setBrick(myTree.nextInt());
                                        myTree.nextName();
                                        myResource.setWood(myTree.nextInt());
                                        myTree.nextName();
                                        myResource.setSheep(myTree.nextInt());
                                        myTree.nextName();
                                        myResource.setWheat(myTree.nextInt());
                                        myTree.nextName();
                                        myResource.setOre(myTree.nextInt());
                                        myTree.endObject();

                                        myTree.nextName();
                                        myTree.beginObject();//oldDevCards
                                        myTree.nextName();
                                        myDevCards.setYearOfPlenty(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setMonopoly(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setSoldier(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setRoadBuilding(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setMonument(myTree.nextInt());
                                        myTree.endObject();

                                        myDevCards = players.get(i).getNewDevCards();
                                        myTree.nextName();
                                        myTree.beginObject();//newDevCards
                                        myTree.nextName();
                                        myDevCards.setYearOfPlenty(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setMonopoly(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setSoldier(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setRoadBuilding(myTree.nextInt());
                                        myTree.nextName();
                                        myDevCards.setMonument(myTree.nextInt());
                                        myTree.endObject();

                                        myTree.nextName();
                                        currentPlayer.setRoads(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setCities(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setSettlements(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setSoldiers(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setVictoryPoints(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setMonuments(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setPlayedDevCard(myTree.nextBoolean());
                                        myTree.nextName();
                                        currentPlayer.setDiscarded(myTree.nextBoolean());
                                        myTree.nextName();
                                        currentPlayer.setPlayerID(myTree.nextInt());
                                        myTree.nextName();
                                        currentPlayer.setPlayerIndex(myTree.nextInt());

                                        myTree.nextName();
                                        currentPlayer.setUsername(myTree.nextString());
                                        myTree.nextName();
                                        currentPlayer.setColor(myTree.nextString());

                                        myTree.endObject();
                                    } else {
                                        myTree.nextNull();
                                        //System.out.println(myTree.peek().name());
                                    }
//                                    System.out.println(myTree.peek().name());

                                }
                                break;
                            case "log":
                                myTree.beginObject();
                                myTree.nextName();
                                myTree.beginArray();
                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    myTree.nextName();
                                    source = myTree.nextString();
                                    myTree.nextName();
                                    message = myTree.nextString();
                                    myMessages.addMessage(new MessageLine(source, message));
                                    myTree.endObject();
//                                    System.out.println(myTree.peek().name());
                                }

                                break;
                            case "chat":
                                myTree.beginObject();
                                myTree.nextName();
                                myTree.beginArray();
                                while (!myTree.peek().name().equals("END_ARRAY")) {
                                    myTree.beginObject();
                                    myTree.nextName();
                                    source = myTree.nextString();
                                    myTree.nextName();
                                    message = myTree.nextString();
                                    myMessages.addMessage(new MessageLine(source, message));
                                    myTree.endObject();
//                                    System.out.println(myTree.peek().name());
                                }
//                                System.out.println(myTree.peek().name());

                                break;
                            case "bank":
                                myTree.beginObject();
                                myResource = myBank.getResources();
                                myTree.nextName();
                                myResource.setBrick(myTree.nextInt());
                                myTree.nextName();
                                myResource.setWood(myTree.nextInt());
                                myTree.nextName();
                                myResource.setSheep(myTree.nextInt());
                                myTree.nextName();
                                myResource.setWheat(myTree.nextInt());
                                myTree.nextName();
                                myResource.setOre(myTree.nextInt());
                                myTree.endObject();
//                                System.out.println(myTree.peek().name());
                                break;
                            case "turnTracker":
                                myTree.beginObject();
                                myTree.nextName();
                                turnTracker.updateStatus(myTree.nextString());
                                myTree.nextName();
                                turnTracker.setCurrentPlayer(myTree.nextInt());
                                myTree.nextName();
                                turnTracker.setLongestRoad(myTree.nextInt());
                                myTree.nextName();
                                turnTracker.setLongestRoad(myTree.nextInt());
                                myTree.endObject();
                                //System.out.println(myTree.peek().name());
                                break;
                            case "winner":
                                myModel.setWinner(myTree.nextInt());
                                //System.out.println(myCurrent);
                                break;
                            case "version":
                                myModel.setVersion(myTree.nextInt());
                                break;
                            default:
//                                System.out.println(myCurrent);
                        }
                        //System.out.println(myCurrent);
                        break;
                    case "NUMBER":
                        myIn = myTree.nextInt();
                        //System.out.println(myIn);
                        break;
                    case "END_OBJECT":
                        myTree.endObject();
                        break;
                    case "BEGIN_ARRAY":
                        myTree.beginArray();
                        break;
                    case "END_ARRAY":
                        myTree.endArray();
                        break;
                }
            }
            myTree.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FailureToAddException e) {
            e.printStackTrace();
        }


        myModel.setMap(myMap);
        myModel.setTt(turnTracker);
        myModel.setBank(myBank);
        myModel.setPlayers(players);

        gameNew.updateGameModel(myModel);
        return gameNew;
    }


}
