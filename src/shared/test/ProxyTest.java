package shared.test;

import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.map.Map;
import client.proxy.IProxy;
import client.proxy.MockFunctionProxy;
import client.proxy.Proxy;
import org.junit.Test;
import server.servermain.Server;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.exceptions.IllegalBuildException;
import shared.exceptions.InsufficientResourcesException;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;
import shared.locations.*;

import static org.junit.Assert.*;

public class ProxyTest {
    GameModel myGameModel;
    IProxy myProxy;
    int playerIndex = 0;
    int playerId = 0;


    public ProxyTest() {
    }

    public void begin()
    {
        myGameModel = new GameModel();
        myProxy = new MockFunctionProxy(myGameModel);
        String[] send = new String[1];
        send[0] = "mock handle!";
        Server.main(send);
    }

    @Test
    public void testLogin() {
        begin();
        try {
            if(!myProxy.userLogin(new User("David", "david")))
                fail();
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testJoin() {
        begin();
        try {
            myProxy.userLogin(new User("David", "david"));
            myProxy.gamesJoin("blue", 0);
            if(myProxy.getResponseCode() != 200)
                fail();
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetModel() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
        myProxy.getGameModel();
        if(myProxy.getResponseCode() != 200)
            fail();
    }

    //doesn't test something that the proxy needs to test
//    @Test
//    public void testGetPlayerId() {
//        begin();
//        try {
//            myProxy.userLogin(new User("david", "david"));
//            myProxy.gamesJoin("blue", 0);
//            playerId = myProxy.getPlayerId();
//            if(myProxy.getResponseCode() != 200)
//                fail();
//        } catch (InvalidUserException e) {
//            fail();
//            e.printStackTrace();
//        }
//    }

    //mocks don't pass back a game so this would never pass . . .
//    @Test
//    public void testGetPlayerIndex() {
//        try {
//            myProxy.userLogin(new User("david", "david"));
//            myProxy.gamesJoin("blue", 0);
//        } catch (InvalidUserException e) {
//            e.printStackTrace();
//        }
//        playerId = myProxy.getPlayerId();
//        playerIndex = myGameModel.getPlayerIndex(playerId);
//    }

    @Test
    public void testSendChat() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.sendChat("Help", playerIndex);
        if(myProxy.getResponseCode() != 200)
            fail();
    }

    @Test
    public void testRollNumber() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.rollNumber(0, playerIndex);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testRobPlayer() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.robPlayer(playerIndex, 1, myGameModel.getMap().getHexes().firstKey());
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testFinishTurn() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.finishTurn(playerIndex);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testBuyDevCard() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        try {
            myProxy.buyDevCard(playerIndex);
            if(myProxy.getResponseCode() != 200)
                fail();
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPlayYearOfPlenty() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.playYearOfPlenty(playerIndex, ResourceType.BRICK, ResourceType.ORE);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testPlayRoadBuilding() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.playRoadBuilding(playerIndex, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.N), new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SW));
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testPlaySoldier() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.playSoldier(playerIndex, playerIndex + 1, new HexLocation(0, 0));
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testPlayMonopoly() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.playMonopoly(playerIndex, ResourceType.BRICK);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testPlaceMonument() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.placeMonument(playerIndex);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testBuildRoad() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.buildRoad(playerIndex, new EdgeLocation(new HexLocation(1, 0), EdgeDirection.N), true);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testBuildSettlement() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        try {
            myProxy.buildSettlement(playerIndex, new VertexLocation(new HexLocation(1, 2), VertexDirection.NW), true);
            if(myProxy.getResponseCode() != 200)
                fail();
        } catch (IllegalBuildException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBuildCity() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        try {
            myProxy.buildCity(playerIndex, new VertexLocation(new HexLocation(1, 2), VertexDirection.NW));
            if(myProxy.getResponseCode() != 200)
                fail();
        } catch (IllegalBuildException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testOfferTrade() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.offerTrade(playerIndex, playerIndex + 1, new ResourceList(1,1,1,1,1));
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testAcceptTrade() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.acceptTrade(playerIndex, true);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testMaritimeTrade() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.maritimeTrade(playerIndex, 3, ResourceType.BRICK, ResourceType.ORE);
        if(myProxy.getResponseCode() != 200)
            fail();
    }
    @Test
    public void testDiscardCards() {
        begin();
        try {
            myProxy.userLogin(new User("david", "david"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        try {
            myProxy.discardCards(playerIndex, new ResourceList(1,1,1,1,1));
            if(myProxy.getResponseCode() != 200)
                fail();
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        }
    }


}
