package client.poller;

import client.model.GameModel;
import client.proxy.IProxy;
import javax.swing.Timer;
import java.awt.event.*;

public class Poller
{
    IProxy myProxy;
    Timer timer;
    int amount = 0;

    public Poller(IProxy givenProxy) {
        myProxy = givenProxy;
        //timer requires (miliseconds, actionlistener)
        timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myProxy.getGameModel();
                timer.restart();
                amount++;
            }
        });
        timer.start();
    }
    
    public int getAmount() {return amount);
}
