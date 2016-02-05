package shared.test;

import client.model.GameModel;
import client.proxy.IProxy;
import client.proxy.MockProxy;
import org.junit.Test;
import java.swing.Timer;
import java.awt.event.*;


public class PollerTest 
{
    @Test
    public void pollerTest() 
    {
        Poller poller = new Poller(new MockProxy(new GameModel());
        timer = new Timer(30000, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                assert(amount == 9 || amount == 10);
            }
        }
        
    }
}
