package server.serverfacade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

/**
 * Created by Joshua on 3/9/2016.
 */
public interface IServerFacade 
{
	// user: total 2 methods
	void postLogin(String username, String password);
	void postRegister(String username, String password);
	//games: total 5 methods
	GameModel getList();
	void postCreate(String title, int id, Player[] players);
	void postJoin(int id, String color);
	void postSave(int id, String name);
	void postLoad(String name);
	//game: total 6 methods
	GameModel getModel();
	void postRest();
	void postCommands();
	void getCommands();
	void postAddAI(String AIType);
	void getListAI();
	//moves: total 17 methods
	void postSendChat(int playerIndex, String content);
	void postRollNumber(int playerIndex, int number);
	void postRobPlayer(int plyerIndex, int victimIndex, HexLocation location);
	void postFinishTurn(int playerIndex);
	void postBuyDevCard(int playerIndex);
	void postYearOfPlenty(int playerIndex, ResourceList res1, ResourceList res2);
	void postRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2);
	void postSoldier(int playerIndex, int victimIndex, HexLocation location);
	void postMonopoly(int playerIndex, String resource);
	void postMonument(int playerIndex);
	void postBuildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free);
	void postBuildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free);
	void postBuildCity(int playerIndex, VertexLocation vertexLocation);
	void postOfferTrade(int playerIndex, ResourceList offer, int receiver);
	void postAcceptTrade(int playerIndex, boolean willAccept);
	void postMaritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource);
	void postDiscardCards(int playerIndex, ResourceList discardedCards);
	//util: total 1 mehtod
	void postChangeLogLevel(String logLevel);
}
