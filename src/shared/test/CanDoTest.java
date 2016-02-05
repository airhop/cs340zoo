package shared.test;

import client.model.GameModel;
import client.proxy.IProxy;
import client.proxy.Proxy;
import org.junit.Test;

/**
 * Created by GaryPetersen on 2/5/2016.
 */
public class CanDoTest {

    private GameModel myGameModel = new GameModel();
    private IProxy myProxy = new Proxy(myGameModel);
    private int playerIndex;
    private int playerId;

    public CanDoTest(){

    }


    @Test
    public void testCanBuildSettlement() {

    }
}
