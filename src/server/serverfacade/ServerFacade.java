package server.serverfacade;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.player.Player;

/**
 * Created by Josh on 3/10/2016.
 */
public class ServerFacade implements IServerFacade
{

	@Override
	public void postLogin(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRegister(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameModel getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postCreate(String title, int id, Player[] players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postJoin(int id, String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSave(int id, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postLoad(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postRest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postCommands() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCommands() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAddAI(String AIType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getListAI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSendChat(int playerIndex, String content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRollNumber(int playerIndex, int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRobPlayer(int plyerIndex, int victimIndex,
			HexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postFinishTurn(int playerIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postBuyDevCard(int playerIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postYearOfPlenty(int playerIndex, ResourceList res1,
			ResourceList res2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRoadBuilding(int playerIndex, EdgeLocation spot1,
			EdgeLocation spot2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSoldier(int playerIndex, int victimIndex,
			HexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postMonopoly(int playerIndex, String resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postMonument(int playerIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postBuildRoad(int playerIndex, EdgeLocation roadLocation,
			Boolean free) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postBuildSettlement(int playerIndex,
			VertexLocation vertexLocation, boolean free) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postBuildCity(int playerIndex, VertexLocation vertexLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postOfferTrade(int playerIndex, ResourceList offer, int receiver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAcceptTrade(int playerIndex, boolean willAccept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postMaritimeTrade(int playerIndex, int ratio,
			String inputResource, String outputResource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postDiscardCards(int playerIndex, ResourceList discardedCards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postChangeLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		
	}
	
}
