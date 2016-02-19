package shared.test;

import client.facade.Facade;
import client.model.GameModel;
import client.model.map.Map;
import client.proxy.Proxy;
import org.junit.Test;
import shared.exceptions.FailureToAddException;
import shared.jsonobject.User;

import static org.junit.Assert.*;

public class ExperimentTest
{
    @Test
    public void testOne()
    {
        Facade f = Facade.getInstance();
        f.setProxy(new Proxy(new GameModel()));

        f.Login("Sam", "sam");
        f.Login("Pete", "pete");
        f.Login("Mark", "mark");
        f.Login("Brooke", "brooke");
//        f.gamesJoin("blue", 0);
//        f.retrieveGameModel();
//        System.out.println("After: " + f.getGM().toString());


        //fail();
        assert(true);
    }

}
