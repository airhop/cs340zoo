package server.commandfactories;

import client.model.bank.ResourceList;
import client.model.map.Road;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import server.commandobjects.ICommand;
import server.commandobjects.moves.*;
import server.servermain.JsonConstructionInfo;
import server.shared.CommandType;
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
        int playerIndex = 0;
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
        return new BuildCity(1, new VertexLocation(new HexLocation(1, 1), VertexDirection.NE));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuildRoad makeBuildRoad(JsonConstructionInfo info) {
        return new BuildRoad(1, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NE), false);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuildSettlement makeBuildSettlement(JsonConstructionInfo info) {
        return new BuildSettlement(1, 1, 1, VertexDirection.E, false);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public BuyDevCard makeBuyDevCar(JsonConstructionInfo info) {
        return new BuyDevCard(1);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public DiscardCards makeDiscardCards(JsonConstructionInfo info) {
        return new DiscardCards(1, new ResourceList());
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public FinishTurn makeFinishTurn(JsonConstructionInfo info) {
        return new FinishTurn(1);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public MaritimeTrade makeMaritimeTrade(JsonConstructionInfo info) {
        return new MaritimeTrade(1, 1, "", "");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Monopoly makeMonopoly(JsonConstructionInfo info) {
        return new Monopoly(1, "");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Monument makeMonument(JsonConstructionInfo info) {
        return new Monument(1);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public OfferTrade makeOfferTrade(JsonConstructionInfo info) {
        return new OfferTrade(1, 1, new ResourceList());
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RoadBuilding makeRoadBuilding(JsonConstructionInfo info) {
        return new RoadBuilding(1, 1, 1, EdgeDirection.N, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.N));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RobPlayer makeRobPlayer(JsonConstructionInfo info) {
        return new RobPlayer(1, 1, "", "");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RollNumber makeRollNumber(JsonConstructionInfo info) {
        return new RollNumber(1, 1);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public SendChat makeSendChat(JsonConstructionInfo info) {
        return new SendChat(1, "");
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Soldier makeSoldier(JsonConstructionInfo info) {
        return new Soldier(1, 1, new HexLocation(1, 1));
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public YearOfPlenty makeYearOfPlenty(JsonConstructionInfo info) {
        return new YearOfPlenty(1, ResourceType.BRICK, ResourceType.WHEAT);
    }

}
