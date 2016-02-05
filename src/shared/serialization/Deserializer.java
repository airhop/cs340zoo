package shared.serialization;

import client.model.GameModel;
import client.model.bank.Bank;
import client.model.bank.DevCardList;
import client.model.bank.ResourceList;
import client.model.map.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import shared.exceptions.FailureToAddException;

import java.io.IOException;

public class Deserializer {

    /**
     * deserializes the JSON formatted data from the server
     *
     * @return object, you must cast this object to the thing you are deseralizing.
     */
    public GameModel deserialize(String jsonString, GameModel myModel) {
        Bank myBank = myModel.getBank();
        Map myMap = myModel.getMap();
        ResourceList myResource = myBank.getResources();
        DevCardList myDevCards = myBank.getDevCards();
        String resourceType = "";
        int xValue;
        int yValue;
        int chitValue;

        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(jsonString);
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        System.out.println(jsonString);
        JsonObject myObj = new JsonObject();
        String action = "";
        String myCurrent = "";
        int myIn = 0;
        boolean myBool = false;
        System.out.println(myTree.toString());
        try {
            while (!myTree.peek().name().equals("END_DOCUMENT")) {
                System.out.println(myTree.peek().name());
                switch (myTree.peek().name()) {
                    case "BEGIN_OBJECT":
                        myTree.beginObject();
                        break;
                    case "NAME":
                        myCurrent = myTree.nextName();
                        switch (myCurrent){
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
                                System.out.println(myCurrent);
                                myTree.beginArray();
                                while(!myTree.peek().name().equals("END_ARRAY")){
                                    myTree.beginObject();
                                    action = myTree.nextName();
                                    switch (action){
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
                                            if(myTree.peek().name().equals("NAME")){
                                                myTree.nextName();
                                                chitValue = myTree.nextInt();
                                                myMap.addHex(xValue, yValue, resourceType, chitValue);
                                                myTree.endObject();
                                            }else{
                                                myMap.addHexDesert(xValue, yValue);
                                                myTree.endObject();
                                            }
                                            break;
                                    }
                                }
                                System.out.println(myCurrent);
                                break;
                            case "cities":
                                System.out.println(myTree.peek().name());
                                break;
                            case "settlements":
                                System.out.println(myTree.peek().name());
                                break;
                            case "ports":
                                System.out.println(myTree.peek().name());
                                break;
                            case "robber":
                                System.out.println(myTree.peek().name());
                                break;
                            case "players":
                                System.out.println(myTree.peek().name());
                                break;
                            case "log":
                                System.out.println(myTree.peek().name());
                                break;
                            case "chat":
                                System.out.println(myTree.peek().name());
                                break;
                            case "bank":
                                System.out.println(myTree.peek().name());
                                break;
                            case "turnTracker":
                                System.out.println(myTree.peek().name());
                                break;


                            default:
                                System.out.println(myCurrent);
                        }

                        if(myCurrent.equals("map")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("players")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("log")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("chat")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("bank")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("turnTracker")){
                            action = myCurrent;
                        }
                        System.out.println(myCurrent);
                        break;
                    case "STRING":
                        myCurrent = myTree.nextString();
                        System.out.println(myCurrent);
                        break;
                    case "NUMBER":
                        myIn = myTree.nextInt();
                        System.out.println(myIn);
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
                    case "BOOLEAN":
                        myBool = myTree.nextBoolean();
                        System.out.println(myBool);
                        break;
                }
            }
            myTree.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (FailureToAddException e) {
            e.printStackTrace();
        }


        return myModel;
    }


}
