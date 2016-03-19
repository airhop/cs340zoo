package server.serverfacade;

import client.MVC.data.GameInfo;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

import java.util.List;

/**
 * Created by Joshua on 3/9/2016.
 */
public interface IServerFacade 
{
	// user: total 2 methods
	boolean userLogin(String username, String password);
	boolean userRegister(String username, String password);
	//games: total 5 methods
	List<GameInfo> getGamesList();
	void createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name);
	void joinGame(int id, String color);
	void save(int id, String name);
	void load(String name);
	//game: total 6 methods
	GameModel getModel(int gameId);
	void addAI(String AIType);
	void listAI();
	//moves: total 17 methods
	void sendChat(int playerIndex, String content);
	void rollNumber(int playerIndex, int number);
	void robPlayer(int playerIndex, int victimIndex, HexLocation location);
	void finishTurn(int playerIndex);
	void buyDevCard(int playerIndex);
	void yearOfPlenty(int playerIndex, ResourceType res1, ResourceType res2);
	void roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2);
	void soldier(int playerIndex, int victimIndex, HexLocation location);
	void monopoly(int playerIndex, String resource);
	void monument(int playerIndex);
	void buildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free);
	void buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free);
	void buildCity(int playerIndex, VertexLocation vertexLocation);
	void offerTrade(int playerIndex, ResourceList offer, int receiver);
	void acceptTrade(int playerIndex, boolean willAccept);
	void maritimeTrade(int playerIndex, int ratio, String inputResource, String outputResource);
	void discardCards(int playerIndex, ResourceList discardedCards);
}
