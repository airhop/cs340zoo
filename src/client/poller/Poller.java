package client.poller;

import client.model.GameModel;
import client.proxy.IProxy;
import javax.swing.Timer;
import java.awt.event.*;

public class Poller
{
    IProxy myProxy;
    Timer timer;

    public Poller(IProxy givenProxy) {
        myProxy = givenProxy;
        //timer requires (miliseconds, actionlistener)
        timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myProxy.getGameModel();
                timer.restart();
            }
        });
        timer.start();
    }
}
