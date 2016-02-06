package shared.test;

import client.model.GameModel;
import client.poller.Poller;
import client.proxy.IProxy;
import client.proxy.MockProxy;
import org.junit.Test;
import javax.swing.Timer;
import java.awt.event.*;


public class PollerTest 
{
    @Test
    public void pollerTest() 
    {
        Poller poller = new Poller(new MockProxy(new GameModel()));
        Timer timer = new Timer(30000, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                System.out.println(poller.getAmount());
                assert(poller.getAmount() == 9 || poller.getAmount() == 10);
            }
        });

        //pause here!!!
        
    }
}
