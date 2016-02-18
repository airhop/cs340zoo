package shared.test;

import client.facade.Facade;
import client.model.GameModel;
import client.proxy.Proxy;
import org.junit.Test;
import shared.jsonobject.User;

import static org.junit.Assert.*;

public class ExperimentTest
{
    @Test
    public void testOne()
    {
        Facade f = Facade.getInstance();
        f.setProxy(new Proxy(new GameModel()));
        System.out.println("First: " + f.getGM().toString());
//        f.buyDevCard(0);
//        System.out.println("Before: " + f.getGM().toString());

        f.Login("Sam", "sam");
        f.gamesJoin("blue", 0);
        f.retrieveGameModel();
        System.out.println("After: " + f.getGM().toString());
//        System.out.println(f.getGM().getPlayers().get(0).getNewDevCards().getSize());
//        System.out.println(f.getGM().getPlayers().get(0).getOldDevCards().getSize());

        //fail();
        assert(true);
    }

}
