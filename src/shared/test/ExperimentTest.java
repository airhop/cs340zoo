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

//        f.Login("Sam", "sam");
//        f.gamesJoin("blue", 0);
//        f.retrieveGameModel();
//        System.out.println("After: " + f.getGM().toString());

        Map map = new Map();
        try {
            map.addHex(0, 0, "wood", 1);
            map.addHex(1, 2, "wood", 1);
            map.addHex(2, 2, "wood", 1);
            map.addHex(1, 0, "wood", 1);
            map.addHex(1, 1, "wood", 1);
            map.addHex(-1, -1, "wood", 1);
        }
        catch(FailureToAddException e)
        {
            e.printStackTrace();
        }

        System.out.println(map.getHexes().size() + " ");
        //fail();
        assert(true);
    }

}
