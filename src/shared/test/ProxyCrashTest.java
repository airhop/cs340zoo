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

public class ProxyCrashTest {
    GameModel myGameModel;
    IProxy myProxy;
    int playerIndex = 0;
    int playerId = 0;

    @Test
    public void ProxyCrashTest() {

        Server.kill();
        int x = 0;
        for(int i =0 ; i < 1000000000; i++)
            x++;
        myGameModel = new GameModel();
        myProxy = new MockFunctionProxy(myGameModel);
        String[] send = new String[1];
        send[0] = "mock handle!";
        Server.main(send);

        try {
            if (!myProxy.userLogin(new User("David", "david")))
                fail();
            myProxy.gamesJoin("blue", 0);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.getGameModel();
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.sendChat("Help", playerIndex);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.rollNumber(0, playerIndex);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.robPlayer(playerIndex, 1, myGameModel.getMap().getHexes().firstKey());
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.finishTurn(playerIndex);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.buyDevCard(playerIndex);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.playYearOfPlenty(playerIndex, ResourceType.BRICK, ResourceType.ORE);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.playRoadBuilding(playerIndex, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.N), new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SW));
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.playSoldier(playerIndex, playerIndex + 1, new HexLocation(0, 0));
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.playMonopoly(playerIndex, ResourceType.BRICK);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.placeMonument(playerIndex);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.buildRoad(playerIndex, new EdgeLocation(new HexLocation(1, 0), EdgeDirection.N), true);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.buildSettlement(playerIndex, new VertexLocation(new HexLocation(1, 2), VertexDirection.NW), true);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.buildCity(playerIndex, new VertexLocation(new HexLocation(1, 2), VertexDirection.NW));
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.offerTrade(playerIndex, playerIndex + 1, new ResourceList(1, 1, 1, 1, 1));
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.acceptTrade(playerIndex, true);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.maritimeTrade(playerIndex, 3, ResourceType.BRICK, ResourceType.ORE);
            if (myProxy.getResponseCode() != 200)
                fail();
            myProxy.discardCards(playerIndex, new ResourceList(1, 1, 1, 1, 1));
            if (myProxy.getResponseCode() != 200)
                fail();


        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        } catch (InsufficientResourcesException e) {
            e.printStackTrace();
        } catch (IllegalBuildException e) {
            e.printStackTrace();
        }
    }
}
