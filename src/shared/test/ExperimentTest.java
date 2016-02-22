package shared.test;

import client.facade.Facade;
import client.model.GameModel;
import client.proxy.Proxy;
import org.junit.Test;

public class ExperimentTest
{
    @Test
    public void testOne()
    {
        Facade f = Facade.getInstance();
        f.setProxy(new Proxy(new GameModel()));

        f.playerLogin("Sam", "sam");
        f.playerLogin("Pete", "pete");
        f.playerLogin("Mark", "mark");
        f.playerLogin("Brooke", "brooke");
//        f.gamesJoin("blue", 0);
//        f.retrieveGameModel();
//        System.out.println("After: " + f.getGameModel().toString());


        //fail();
        assert(true);
    }

}
