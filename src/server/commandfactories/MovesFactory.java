package server.commandfactories;

import client.model.bank.ResourceList;
import client.model.map.Road;
import server.commandobjects.ICommand;
import server.commandobjects.moves.*;
import server.servermain.JsonConstructionInfo;
import server.shared.CommandType;
import shared.definitions.ResourceType;
import shared.locations.*;

/**
 * Created by Joshua on 3/10/2016.
 */
public class MovesFactory {
    public MovesFactory() {

    }

    /**
     * This is the method that you call when you want a Command object that is in the move category
     *
     * @param command - this is the command that you want from the factory
     * @param info    - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(CommandType command, JsonConstructionInfo info) {
        ICommand commandObject;
        commandObject = new AcceptTrade(1, true);
//        switch (){
//
//        }
        return commandObject;
    }

    private AcceptTrade makeAcceptTrade(JsonConstructionInfo info) {
        return new AcceptTrade(1, false);
    }

    private BuildCity makeBuildCity(JsonConstructionInfo info) {
        return new BuildCity(1, new VertexLocation(new HexLocation(1, 1), VertexDirection.NE));
    }

    private BuildRoad makeBuildRoad(JsonConstructionInfo info) {
        return new BuildRoad(1, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NE), false);
    }

    private BuildSettlement makeBuildSettlement(JsonConstructionInfo info) {
        return new BuildSettlement(1, 1, 1, VertexDirection.E, false);
    }

    private BuyDevCard makeBuyDevCar(JsonConstructionInfo info) {
        return new BuyDevCard(1);
    }

    private DiscardCards makeDiscardCards(JsonConstructionInfo info) {
        return new DiscardCards(1, new ResourceList());
    }

    private FinishTurn makeFinishTurn(JsonConstructionInfo info) {
        return new FinishTurn(1);
    }

    private MaritimeTrade makeMaritimeTrade(JsonConstructionInfo info) {
        return new MaritimeTrade(1, 1, "", "");
    }

    private Monopoly makeMonopoly(JsonConstructionInfo info) {
        return new Monopoly(1, "");
    }

    private Monument makeMonument(JsonConstructionInfo info) {
        return new Monument(1);
    }

    private OfferTrade makeOfferTrade(JsonConstructionInfo info) {
        return new OfferTrade(1, 1, new ResourceList());
    }

    private RoadBuilding makeRoadBuilding(JsonConstructionInfo info) {
        return new RoadBuilding(1, 1, 1, EdgeDirection.N, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.N));
    }

    private RobPlayer makeRobPlayer(JsonConstructionInfo info) {
        return new RobPlayer(1, 1, "", "");
    }

    private RollNumber makeRollNumber(JsonConstructionInfo info) {
        return new RollNumber(1, 1);
    }

    private SendChat makeSendChat(JsonConstructionInfo info) {
        return new SendChat(1, "");
    }

    private Soldier makeSoldier(JsonConstructionInfo info) {
        return new Soldier(1, 1, new HexLocation(1, 1));
    }

    private YearOfPlenty makeYearOfPlenty(JsonConstructionInfo info) {
        return new YearOfPlenty(1, ResourceType.BRICK, ResourceType.WHEAT);
    }

}
