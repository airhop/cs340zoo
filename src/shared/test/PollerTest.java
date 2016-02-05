package shared.test;

import client.model.GameModel;
import client.poller.Poller;
import client.proxy.IProxy;
import client.proxy.MockProxy;
import org.junit.Test;

/**
 * Created by GaryPetersen on 2/5/2016.
 */

public class PollerTest {
    @Test
    public void pollerTest() {
        GameModel gameModel = new GameModel();
        IProxy iProxy = new MockProxy(gameModel);
        Poller poller = new Poller(iProxy);
    }
}
