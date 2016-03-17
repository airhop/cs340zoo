package client.gui;

import client.MVC.data.GameInfo;
import client.facade.Facade;
import client.model.GameModel;
import client.poller.Poller;
import client.proxy.Cookie;
import client.proxy.IProxy;
import client.proxy.Proxy;
import shared.definitions.CatanColor;
import shared.exceptions.FailedCreateGameException;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;

import java.util.ArrayList;
import java.util.List;

public class NewMain {
    public static void main(String[] args) {
        GameModel myGameModel = new GameModel();
        IProxy myProxy = new Proxy(myGameModel);
        List<GameInfo> myList = new ArrayList<>();
        int playerIndex;
        int playerId;
        User testUser = new User("Sam", "Sam");
        try {
            myProxy.userLogin(testUser);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.acceptTrade(0, true);

        CatanColor myColor = CatanColor.BLUE;


        try {
            myProxy.gamesJoin(myColor.toString(), 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.getGameModel();


    }

}
