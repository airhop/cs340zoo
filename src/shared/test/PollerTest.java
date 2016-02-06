package shared.test;

import client.model.GameModel;
import client.poller.Poller;
import client.proxy.IProxy;
import client.proxy.MockProxy;
import org.junit.Test;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;


public class PollerTest 
{
    @Test
    public void pollerTest() 
    {
        Poller poller = new Poller(new MockProxy(new GameModel()));
        try
        {   TimeUnit.SECONDS.sleep(15); }
        catch(java.lang.InterruptedException e)
        {
            System.out.println("Error with the sleeping timer . . . ");
        }
        assert(poller.getAmount() >= 4 && poller.getAmount() <= 6);
        
    }
}
