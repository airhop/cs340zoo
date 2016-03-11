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

    /**
     *
     * @param info -
     * @return -
     */
    private AcceptTrade makeAcceptTrade(JsonConstructionInfo info) {
        return new AcceptTrade(1, false);
    }

    /**
     *
     * @param info -
     * @return -
     */
    private BuildCity makeBuildCity(JsonConstructionInfo info) {
        return new BuildCity(1, new VertexLocation(new HexLocation(1, 1), VertexDirection.NE));
    }

    /**
     *
     * @param info -
     * @return -
     */
    private BuildRoad makeBuildRoad(JsonConstructionInfo info) {
        return new BuildRoad(1, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.NE), false);
    }

    /**
     *
     * @param info -
     * @return -
     */
    private BuildSettlement makeBuildSettlement(JsonConstructionInfo info) {
        return new BuildSettlement(1, 1, 1, VertexDirection.E, false);
    }

    /**
     *
     * @param info
     * @return
     */
    private BuyDevCard makeBuyDevCar(JsonConstructionInfo info) {
        return new BuyDevCard(1);
    }

    /**
     *
     * @param info
     * @return
     */
    private DiscardCards makeDiscardCards(JsonConstructionInfo info) {
        return new DiscardCards(1, new ResourceList());
    }

    /**
     *
     * @param info
     * @return
     */
    private FinishTurn makeFinishTurn(JsonConstructionInfo info) {
        return new FinishTurn(1);
    }

    /**
     *
     * @param info
     * @return
     */
    private MaritimeTrade makeMaritimeTrade(JsonConstructionInfo info) {
        return new MaritimeTrade(1, 1, "", "");
    }

    /**
     *
     * @param info
     * @return
     */
    private Monopoly makeMonopoly(JsonConstructionInfo info) {
        return new Monopoly(1, "");
    }

    /**
     *
     * @param info
     * @return
     */
    private Monument makeMonument(JsonConstructionInfo info) {
        return new Monument(1);
    }

    /**
     *
     * @param info
     * @return
     */
    private OfferTrade makeOfferTrade(JsonConstructionInfo info) {
        return new OfferTrade(1, 1, new ResourceList());
    }

    /**
     *
     * @param info
     * @return
     */
    private RoadBuilding makeRoadBuilding(JsonConstructionInfo info) {
        return new RoadBuilding(1, 1, 1, EdgeDirection.N, new EdgeLocation(new HexLocation(1, 1), EdgeDirection.N));
    }

    /**
     *
     * @param info
     * @return
     */
    private RobPlayer makeRobPlayer(JsonConstructionInfo info) {
        return new RobPlayer(1, 1, "", "");
    }

    /**
     *
     * @param info
     * @return
     */
    private RollNumber makeRollNumber(JsonConstructionInfo info) {
        return new RollNumber(1, 1);
    }

    /**
     *
     * @param info
     * @return
     */
    private SendChat makeSendChat(JsonConstructionInfo info) {
        return new SendChat(1, "");
    }

    /**
     *
     * @param info
     * @return
     */
    private Soldier makeSoldier(JsonConstructionInfo info) {
        return new Soldier(1, 1, new HexLocation(1, 1));
    }

    /**
     *
     * @param info
     * @return
     */
    private YearOfPlenty makeYearOfPlenty(JsonConstructionInfo info) {
        return new YearOfPlenty(1, ResourceType.BRICK, ResourceType.WHEAT);
    }

}
