package client.poller;

import client.facade.Facade;
import client.model.GameModel;
import client.proxy.IProxy;
import javax.swing.Timer;
import java.awt.event.*;

public class Poller
{
    Facade facade;
    Timer timer;
    int amount = 0;

    public Poller(IProxy givenProxy) {
        facade = Facade.getInstance();
        facade.setProxy(givenProxy);
        //timer requires (miliseconds, actionlistener)
        timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                facade.retrieveGameModel();
                timer.restart();
                amount++;
            }
        });
        timer.start();
    }
    
    public int getAmount() {return amount;}
}
