package server.factories;

import client.model.bank.ResourceList;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import server.commandobjects.ICommand;
import server.commandobjects.moves.*;
import server.servermain.JsonConstructionInfo;
import shared.definitions.ResourceType;
import shared.locations.*;

import java.io.IOException;

/**
 * Created by Joshua on 3/10/2016.
 */
public class MovesFactory {
    public MovesFactory() {

    }

    /**
     * This is the method that you call when you want a Command object that is in the move category
     *
     *
     * @param info    - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info) {
        ICommand commandObject;
        commandObject = new AcceptTrade(1, true);
        switch(info.getName()){
            case acceptTrade:
                return makeAcceptTrade(info);
            case buildCity:
                return makeBuildCity(info);
            case buildRoad:
                return makeBuildRoad(info);
            case buildSettlement:
                return makeBuildSettlement(info);
            case buyDevCard:
                return makeBuyDevCar(info);
            case discardCards:
                return makeDiscardCards(info);
            case finishTurn:
                return makeFinishTurn(info);
            case maritimeTrade:
                return makeMaritimeTrade(info);
            case Monopoly:
                return makeMonopoly(info);
            case Monument:
                return makeMonument(info);
            case offerTrade:
                return makeOfferTrade(info);
            case Road_Building:
                return makeRoadBuilding(info);
            case robPlayer:
                return makeRobPlayer(info);
            case rollNumber:
                return makeRollNumber(info);
            case sendChat:
                return makeSendChat(info);
            case Soldier:
                return makeSoldier(info);
            case Year_Of_Plenty:
                return makeYearOfPlenty(info);
        }
        return commandObject;
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public AcceptTrade makeAcceptTrade(JsonConstructionInfo info) {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        boolean willAccept = false;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName(); //This is the willAccept
            willAccept = myTree.nextBoolean(); //This is the boolean
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AcceptTrade(playerIndex, willAccept);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuildCity makeBuildCity(JsonConstructionInfo info) {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int x = 0;
        int y = 0;
        String jsonDirection = "";
        VertexDirection direction = null;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName(); //This is the Vertex Location name
            myTree.beginObject();//Begining the vertex location object
            myTree.nextName();//the next name is the Hex location
            myTree.beginObject(); //begining the hex location object
            myTree.nextName(); //the first integer x name
            x = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y = myTree.nextInt();//the y-coordinate
            myTree.endObject();//exiting the hexlocation object
            myTree.nextName(); // getting the name of the string vertex direction
            jsonDirection= myTree.nextString(); // the actual vertex direction

        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(jsonDirection)
        {
            case "W":
                direction =  VertexDirection.W;
                break;
            case "NW":
                direction = VertexDirection.NW;
                break;
            case "NE":
                direction = VertexDirection.NE;
                break;
            case "E":
                direction = VertexDirection.E;
                break;
            case "SE":
                direction = VertexDirection.SE;
                break;
            case "SW":
                direction = VertexDirection.SW;
                break;
        }
        return new BuildCity(playerIndex, new VertexLocation(new HexLocation(x, y), direction));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuildRoad makeBuildRoad(JsonConstructionInfo info) {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int x = 0;
        int y = 0;
        String jsonDirection = null;
        EdgeDirection direction = null;
        String isFree = null;
        boolean free = false;
        try {
            System.out.println("We are about to view the JSON");
            System.out.println(info.getJsonBody());
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName(); //This is the roadLocation name
            myTree.beginObject(); // beginning the Road Location object
            myTree.nextName(); //the first integer x name
            x = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y = myTree.nextInt();//the y-coordinate
            myTree.nextName(); // the name of road direction
            jsonDirection = myTree.nextString(); //the road direction in string form
            myTree.endObject();//exiting the Road Location Object
            myTree.nextName(); //the name of the is free boolean
            String test = myTree.peek().toString();
            isFree = myTree.nextString();//the is free boolean
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isFree.equals("true"))
        {
            free = true;
        }
        else
        {
            free = false;
        }
        switch(jsonDirection)
        {
            case "N":
                direction = EdgeDirection.N;
                break;
            case "S":
                direction = EdgeDirection.S;
                break;
            case "NW":
                direction = EdgeDirection.NW;
                break;
            case "NE":
                direction = EdgeDirection.NE;
                break;
            case "SE":
                direction = EdgeDirection.SE;
                break;
            case "SW":
                direction = EdgeDirection.SW;
                break;
        }
        return new BuildRoad(playerIndex, new EdgeLocation(new HexLocation(x, y), direction), free);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuildSettlement makeBuildSettlement(JsonConstructionInfo info) {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int x = 0;
        int y = 0;
        String jsonDirection = "";
        boolean isFree = false;
        VertexDirection direction = null;
        try {
            myTree.beginObject();
            System.out.println(info.getJsonBody());
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName(); //This is the Vertex Location name
            myTree.beginObject();//Begining the vertex location object
            myTree.nextName(); //the first integer x name
            x = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y = myTree.nextInt();//the y-coordinate
            myTree.nextName(); // getting the name of the string vertex direction
            jsonDirection= myTree.nextString(); // the actual vertex direction
            myTree.endObject();//ending the vertexLocation object
            myTree.nextName();//the name of the free boolean
            isFree = myTree.nextBoolean();//the "free" boolean

        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(jsonDirection)
        {
            case "W":
                direction =  VertexDirection.W;
                break;
            case "NW":
                direction = VertexDirection.NW;
                break;
            case "NE":
                direction = VertexDirection.NE;
                break;
            case "E":
                direction = VertexDirection.E;
                break;
            case "SE":
                direction = VertexDirection.SE;
                break;
            case "SW":
                direction = VertexDirection.SW;
                break;
        }
        return new BuildSettlement(playerIndex, x, y, direction, isFree);
    }
    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuyDevCard makeBuyDevCar(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BuyDevCard(playerIndex);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public DiscardCards makeDiscardCards(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int numOfBrick = 0;
        int numOfOre = 0;
        int numOfSheep = 0;
        int numOfWheat = 0;
        int numOfWood = 0;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the resourceList
            myTree.beginObject();//beginning the resourceList object
            myTree.nextName();//the numOfBrick name
            numOfBrick = myTree.nextInt(); //the actual numOfBrick
            myTree.nextName(); // the numOfOre name
            numOfOre = myTree.nextInt(); // the actual numOfOre
            myTree.nextName(); // the numOfSheep name
            numOfSheep = myTree.nextInt(); // the actual numOfSheep
            myTree.nextName(); // the numOfWheat name
            numOfWheat = myTree.nextInt(); // the actual numOfWheat
            myTree.nextName(); // the numOfWood name
            numOfWood = myTree.nextInt(); // the actual numOfWood

        } catch (IOException e) {
            e.printStackTrace();
        }
        ResourceList resources = new ResourceList(numOfBrick,numOfOre,numOfSheep,numOfWheat,numOfWood);
        return new DiscardCards(playerIndex, resources);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public FinishTurn makeFinishTurn(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FinishTurn(playerIndex);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public MaritimeTrade makeMaritimeTrade(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int ratio = 0;
        String input = null;
        String output = null;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the ratio
            myTree.nextInt();//the ratio
            myTree.nextName();//the name of the input resource
            input = myTree.nextString();//the input resource
            myTree.nextName();//the name of the output resource
            output = myTree.nextString();//the output resource

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MaritimeTrade(playerIndex, ratio, input, output);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Monopoly makeMonopoly(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        String resource = null;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName();//the name of the resource
            resource = myTree.nextString();//the resource itself
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Monopoly(playerIndex, resource);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Monument makeMonument(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Monument(playerIndex);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public OfferTrade makeOfferTrade(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int recieverIndex = -1;
        int numOfBrick = 0;
        int numOfOre = 0;
        int numOfSheep = 0;
        int numOfWheat = 0;
        int numOfWood = 0;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the reciever index
            recieverIndex = myTree.nextInt();//the player index of the reciever
            myTree.nextName();//the name of the resourceList
            myTree.beginObject();//beginning the resourceList object
            myTree.nextName();//the numOfBrick name
            numOfBrick = myTree.nextInt(); //the actual numOfBrick
            myTree.nextName(); // the numOfOre name
            numOfOre = myTree.nextInt(); // the actual numOfOre
            myTree.nextName(); // the numOfSheep name
            numOfSheep = myTree.nextInt(); // the actual numOfSheep
            myTree.nextName(); // the numOfWheat name
            numOfWheat = myTree.nextInt(); // the actual numOfWheat
            myTree.nextName(); // the numOfWood name
            numOfWood = myTree.nextInt(); // the actual numOfWood



        } catch (IOException e) {
            e.printStackTrace();
        }
        ResourceList resources = new ResourceList(numOfBrick,numOfOre,numOfSheep,numOfWheat,numOfWood);
        return new OfferTrade(playerIndex, recieverIndex,resources);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RoadBuilding makeRoadBuilding(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        String jsonDirection1 = null;
        String jsonDirection2 = null;
        EdgeDirection direction = null;
        EdgeDirection direction2 = null;
        boolean isFree = false;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName(); //This is the EdgeLocation name
            myTree.beginObject(); // beginning the Edge Location Object
            myTree.nextName(); //beginning the hex location object
            myTree.beginObject(); // beginning the hex location object
            myTree.nextName(); //the first integer x name
            x1 = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y1 = myTree.nextInt();//the y-coordinate
            myTree.endObject();//exiting the hexlocation object
            myTree.nextName(); // the name of Edge Direction
            jsonDirection1 = myTree.nextString(); //the edge direction in string form
            myTree.endObject();//exiting the Edge Location Object
            myTree.nextName(); //the name of the second Edge Location
            myTree.nextName(); //beginning the hex location object
            myTree.beginObject(); // beginning the hex location object
            myTree.nextName(); //the first integer x name
            x2 = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y2 = myTree.nextInt();//the y-coordinate
            myTree.endObject();//exiting the hexlocation object
            myTree.nextName(); // the name of the second Edge Direction
            jsonDirection2 = myTree.nextString(); //the second edge direction in string form
            myTree.endObject();//exiting the second Edge Location Object


        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(jsonDirection1)
        {
            case "N":
                direction = EdgeDirection.N;
                break;
            case "S":
                direction = EdgeDirection.S;
                break;
            case "NW":
                direction = EdgeDirection.NW;
                break;
            case "NE":
                direction = EdgeDirection.NE;
                break;
            case "SE":
                direction = EdgeDirection.SE;
                break;
            case "SW":
                direction = EdgeDirection.SW;
                break;
        }
        switch(jsonDirection2)
        {
            case "N":
                direction2 = EdgeDirection.N;
                break;
            case "S":
                direction2 = EdgeDirection.S;
                break;
            case "NW":
                direction2 = EdgeDirection.NW;
                break;
            case "NE":
                direction2 = EdgeDirection.NE;
                break;
            case "SE":
                direction2 = EdgeDirection.SE;
                break;
            case "SW":
                direction2 = EdgeDirection.SW;
                break;
        }
        return new RoadBuilding(playerIndex, x1, y1, direction, new EdgeLocation(new HexLocation(x2, y2), direction2));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RobPlayer makeRobPlayer(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int victimIndex = -1;
        int x = 0;
        int y = 0;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the victim index
            victimIndex = myTree.nextInt();//the player index of the victim
            myTree.nextName();//the next name is the Hex location
            myTree.beginObject(); //begining the hex location object
            myTree.nextName(); //the first integer x name
            x = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y = myTree.nextInt();//the y-coordinate
            myTree.endObject();//exiting the hexlocation object



        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RobPlayer(playerIndex, victimIndex, Integer.toString(x) , Integer.toString(y));//ask Aaron about his...why is it a string?
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RollNumber makeRollNumber(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int rolled = 0;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the integer of the number rolled
            rolled = myTree.nextInt();//the number rolled


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RollNumber(playerIndex, rolled);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public SendChat makeSendChat(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        String content = null;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the content
            content= myTree.nextString();//the content of the message


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SendChat(playerIndex, content);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Soldier makeSoldier(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        int victimIndex = -1;
        int x = 0;
        int y = 0;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the victim index
            victimIndex = myTree.nextInt();//the player index of the victim
            myTree.nextName();//the next name is the Hex location
            myTree.beginObject(); //begining the hex location object
            myTree.nextName(); //the first integer x name
            x = myTree.nextInt();// the x-coordinate
            myTree.nextName();//the y-coordinate name
            y = myTree.nextInt();//the y-coordinate
            myTree.endObject();//exiting the hexlocation object



        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Soldier(playerIndex, victimIndex, new HexLocation(x, y));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public YearOfPlenty makeYearOfPlenty(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerIndex = -1;
        String resource1 = null;
        String resource2 = null;
        ResourceType resourceType1 = null;
        ResourceType resourceType2 = null;
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first which is just the type
            myTree.nextString(); //This is the Type name
            myTree.nextName(); //This is the name == playerindex
            playerIndex = myTree.nextInt(); //This is the player index
            myTree.nextName();//the name of the first resource type
            resource1 = myTree.nextString();//the first resource type in string form
            myTree.nextName();//the name of the second resource type
            resource2 = myTree.nextString();//the second resource type in string form




        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(resource1)
        {
            case "brick":
                resourceType1 = ResourceType.BRICK;
                break;
            case "ore":
                resourceType1 = ResourceType.ORE;
                break;
            case "sheep":
                resourceType1 = ResourceType.SHEEP;
                break;
            case "wheat":
                resourceType1 = ResourceType.WHEAT;
                break;
            case "wood":
                resourceType1 = ResourceType.WOOD;
        }
        switch(resource2)
        {
            case "brick":
                resourceType2 = ResourceType.BRICK;
                break;
            case "ore":
                resourceType2 = ResourceType.ORE;
                break;
            case "sheep":
                resourceType2 = ResourceType.SHEEP;
                break;
            case "wheat":
                resourceType2 = ResourceType.WHEAT;
                break;
            case "wood":
                resourceType2 = ResourceType.WOOD;
        }
        return new YearOfPlenty(playerIndex, resourceType1, resourceType2);
    }

}
