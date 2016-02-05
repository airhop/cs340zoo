package shared.test;

import client.model.GameModel;
import client.proxy.IProxy;
import client.proxy.Proxy;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;

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
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testJoin() {
        try {
            myProxy.gamesJoin("blue", 0);
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetModel() {
        myProxy.getGameModel();
    }

    @Test
    public void testGetPlayerId() {
        playerId = myProxy.getPlayerId();
    }

    @Test
    public void testGetPlayerIndex() {
        playerIndex = myGameModel.getPlayerIndex(playerId);
    }

    @Test
    public void testSendChat() {
        myProxy.sendChat("Help", playerIndex);
    }

    @Test
    public void testRollNumber() {
        myProxy.rollNumber(0, playerIndex);
    }

//    @Test
//    public void testRollNumber() {
//        myProxy.rollNumber(0, playerIndex);
//    }

//    @Test
//    public void testGameModelAfter() {
//        CatanColor myColor = CatanColor.BLUE;
//        try {
//            myProxy.gamesJoin(myColor.toString(), 0);
//        } catch (InvalidUserException e) {
//            e.printStackTrace();
//        }
//        myProxy.getGameModel();
//        playerId = myProxy.getPlayerId();
//        playerIndex = myGameModel.getPlayerIndex(playerId);
////        myProxy.rollNumber(0, playerIndex);
//
//        myProxy.sendChat("Help", playerIndex);
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }
//
//    @Test
//    public void testGameModelAfter() {
//
//    }


}
