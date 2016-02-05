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
        GameModel myGameModel = new GameModel();
        IProxy myProxy = new Proxy(myGameModel);
    }

    @Test
    public void testGameModelBefore() {
        try {
            myProxy.userLogin(new User("Sam", "sam"));
        } catch (InvalidUserException e) {
            fail();
            e.printStackTrace();
        }
    }
//
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
