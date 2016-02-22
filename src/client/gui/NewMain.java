package client.gui;

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

public class NewMain {
    public static void main(String[] args){
        GameModel myGameModel = new GameModel();
        IProxy myProxy = new Proxy(myGameModel);
        int playerIndex;
        int playerId;
//        Poller myPoller = new Poller(myProxy);
        try {
            myProxy.userLogin(new User("Sam", "sam"));
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        CatanColor myColor = CatanColor.BLUE;
        try {
            myProxy.gamesJoin(myColor.toString(), 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        myProxy.getGameModel();
        System.out.println(myGameModel.getPlayerIndex(0));
        System.out.println(myGameModel.getPlayerIndex(1));
        System.out.println(myGameModel.getPlayerIndex(2));
        System.out.println(myGameModel.getPlayerIndex(3));


//        playerId = myProxy.getPlayerId();
//        playerIndex = myGameModel.getPlayerIndex(playerId);
////        myProxy.rollNumber(0, playerIndex);
//
//        myProxy.sendChat("Help", playerIndex);



//        myProxy.sendChat("plants", 0);
//        try {
//            myProxy.gamesCreate("hello");
//        } catch (FailedCreateGameException e) {
//            e.printStackTrace();
//        }
//        try {
//            myProxy.gamesJoin(myColor.toString(), 0);
//        } catch (InvalidUserException e) {
//            e.printStackTrace();
//        }

    }

}
