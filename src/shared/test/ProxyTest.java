package shared.test;

import client.model.GameModel;
import client.model.bank.ResourceList;
import client.model.map.Map;
import client.proxy.IProxy;
import client.proxy.Proxy;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.exceptions.IllegalBuildException;
import shared.exceptions.InsufficientResourcesException;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import static org.junit.Assert.*;

/**
 * Created by GaryPetersen on 2/5/2016.
 */
public class ProxyTest {
    GameModel myGameModel;
    IProxy myProxy;
    int playerIndex;
    int playerId;

    public ProxyTest() {
        myGameModel = new GameModel();
        myProxy = new Proxy(myGameModel);
    }


    @Test
    public void testLogin() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
            playerId = myProxy.getPlayerId();
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testJoin() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
            playerId = myProxy.getPlayerId();
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetModel() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        myProxy.getGameModel();
    }

    @Test
    public void testGetPlayerId() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
            playerId = myProxy.getPlayerId();
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPlayerIndex() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
    }

    @Test
    public void testSendChat() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.sendChat("Help", playerIndex);
    }

    @Test
    public void testRollNumber() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.rollNumber(0, playerIndex);
    }
    @Test
    public void testRobPlayer() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        myProxy.robPlayer(playerIndex, 1, myGameModel.getMap().getHexes().firstKey());
    }
    @Test
    public void testFinishTurn() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        myProxy.finishTurn(playerIndex);
    }
    @Test
    public void testBuyDevCard() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        try {
            myProxy.buyDevCard(playerIndex);
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPlayYearOfPlenty() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        myProxy.playYearOfPlenty(playerIndex, ResourceType.BRICK, ResourceType.ORE);
    }
    @Test
    public void testPlayRoadBuilding() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.playRoadBuilding(playerIndex, myMap.getRoads().get(0).getLocation(), myMap.getRoads().get(1).getLocation());
    }
    @Test
    public void testPlaySoldier() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.playSoldier(playerIndex, playerIndex + 1, myMap.getHexes().firstKey());
    }
    @Test
    public void testPlayMonopoly() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.playMonopoly(playerIndex, ResourceType.BRICK);
    }
    @Test
    public void testPlaceMonument() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.placeMonument(playerIndex);
    }
    @Test
    public void testBuildRoad() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.buildRoad(playerIndex, new EdgeLocation(new HexLocation(1, 0), EdgeDirection.N), true);
    }
    @Test
    public void testBuildSettlement() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        try {
            myProxy.buildSettlement(playerIndex, myMap.getBuildings().get(1).getLocation(), true);
        } catch (IllegalBuildException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBuildCity() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        try {
            myProxy.buildCity(playerIndex, myMap.getBuildings().get(1).getLocation());
        } catch (IllegalBuildException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testOfferTrade() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.offerTrade(playerIndex, playerIndex + 1, new ResourceList(1,1,1,1,1));
    }
    @Test
    public void testAcceptTrade() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.acceptTrade(playerIndex, true);
    }
    @Test
    public void testMaritimeTrade() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        myProxy.maritimeTrade(playerIndex, 3, ResourceType.BRICK, ResourceType.ORE);
    }
    @Test
    public void testDiscardCards() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        playerId = myProxy.getPlayerId();
        playerIndex = myGameModel.getPlayerIndex(playerId);
        myProxy.getGameModel();
        Map myMap = myGameModel.getMap();
        try {
            myProxy.discardCards(playerIndex, new ResourceList(1,1,1,1,1));
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        }
    }


}
