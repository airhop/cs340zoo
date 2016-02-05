package client.proxy;


import client.model.bank.ResourceList;
import client.model.history.MessageList;
import shared.definitions.ResourceType;
import shared.exceptions.*;
import shared.jsonobject.Resources;
import shared.jsonobject.User;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class MockProxy implements IProxy{

    public MockProxy(){

    }

    /**
     * Sets up a mock Proxy
     */
    public void runMock(){

    }


    @Override
    public int getPlayerId() 
    {
        return 0;
    }

    @Override
    public void userLogin(User u) throws InvalidUserException 
    {
    	System.out.println("MockProxy.java: userLogin(User u) is called");
    }

    @Override
    public void userRegister(User u) throws InvalidUserException 
    {
    	System.out.println("MockProxy.java: userRegister(User u) is called");
    }

    @Override
    public String[] gamesList() 
    {
    	System.out.println("MockProxy.java: gamesList() is called");
        return new String[0];
    }

    @Override
    public void gamesCreate(String s) throws FailedCreateGameException 
    {
    	System.out.println("MockProxy.java: gamesCreate(String s) is called");
    }

    @Override
    public void gamesJoin(String color, int playerId) throws InvalidUserException 
    {
    	System.out.println("MockProxy.java: gamesJoin(String color, int playerId) is called");
    }

    @Override
    public void gamesSave() 
    {
    	System.out.println("MockProxy.java: gamesSave() is called");
    }

    @Override
    public void gamesLoad() 
    {
    	System.out.println("MockProxy.java: gamesLoad() is called");
    }

    @Override
    public void getGameModel() 
    {
    	System.out.println("MockProxy.java: getGameModel() is called");
    }

    @Override
    public void resetCommands() 
    {
    	System.out.println("MockProxy.java: resetCommands() is called");
    }

    @Override
    public void runCommand() 
    {
    	System.out.println("MockProxy.java: runCommand() is called");
    }

    @Override
    public void listCommands() 
    {
    	System.out.println("MockProxy.java: listCommands() is called");
    }

    @Override
    public boolean gameAddAI() 
    {
    	System.out.println("MockProxy.java: gameAddAI() is called");
        return false;
    }

    @Override
    public String[] gameListAI() 
    {
    	System.out.println("MockProxy.java: gameListAI() is called");
        return new String[0];
    }

    @Override
    public void sendChat(String content, int id) 
    {
    	System.out.println("MockProxy.java: sendChat() is called");
    }

    @Override
    public void rollNumber(int numRoled, int playerIndex) 
    {
    	System.out.println("MockProxy.java: rollNumber() is called");
    }

    @Override
    public void robPlayer(int playerIdOne, int playerIdTwo, HexLocation Hl) 
    {
    	System.out.println("MockProxy.java: robPlayer(int playerIdOne, int playerIdTwo, HexLocation Hl) is called");
    }

    @Override
    public void finishTurn(int playerId) 
    {
    	System.out.println("MockProxy.java:n finishTurn(int playerId) is called");
    }

    @Override
    public void buyDevCard(int playerId) throws InsufficientResourcesException 
    {
    	System.out.println("MockProxy.java: buyDevCard(int playerId) is called");
    }

    @Override
    public void playYearOfPlenty(int playerId, ResourceType r1, ResourceType r2) 
    {
    	System.out.println("MockProxy.java: playYearOfPlenty(int playerId, ResourceType r1, ResourceType r2) is called");
    }

    @Override
    public void playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2) 
    {
    	System.out.println("MockProxy.java: playRoadBuilding(int playerId, EdgeLocation e1, EdgeLocation e2) is called");
    }

    @Override
    public void playSoldier(int playerId, int victimId, HexLocation El) 
    {
    	System.out.println("MockProxy.java: playSoldier(int playerId, int victimId, HexLocation El) is called");
    }

    @Override
    public void playMonopoly(int playerId, ResourceType r1) 
    {
    	System.out.println("MockProxy.java: playMonopoly(int playerId, ResourceType r1) is called");
    }

    @Override
    public void placeMonument(int playerId) 
    {
    	System.out.println("MockProxy.java: placeMonument(int playerId) is called");
    }

    @Override
    public void buildRoad(int playerId, EdgeLocation el) 
    {
    	System.out.println("MockProxy.java: buildRoad(int playerId, EdgeLocation el) is called");
    }

    @Override
    public void buildSettlement(int playerId, VertexLocation vl) throws IllegalBuildException 
    {
    	System.out.println("MockProxy.java: buildSettlement(int playerId, VertexLocation vl) throws IllegalBuildException is called");
    }

    @Override
    public void buildCity(int playerId, VertexLocation vl) throws IllegalBuildException 
    {
    	System.out.println("MockProxy.java: buildCity(int playerId, VertexLocation vl) throws IllegalBuildException is called");
    }

    @Override
    public void offerTrade(int playerIdOne, int playerIdTwo, ResourceList rl) 
    {
    	System.out.println("MockProxy.java: offerTrade(int playerIdOne, int playerIdTwo, ResourceList rl) is called");
    }

    @Override
    public void acceptTrade(int playerIdOne, boolean accept) 
    {
    	System.out.println("MockProxy.java: acceptTrade(int playerIdOne, boolean accept) is called");
    }

    @Override
    public void meritimeTrade(int playerId, int ratio, ResourceList in, ResourceList out) 
    {
    	System.out.println("MockProxy.java: meritimeTrade(int playerId, int ratio, ResourceList in, ResourceList out) is called");
    }

    @Override
    public void discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException 
    {
    	System.out.println("MockProxy.java: discardCards(int playerId, ResourceList rl) throws InsufficientResourcesException is called");
    }
}
