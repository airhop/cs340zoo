package client.gui;

import client.facade.Facade;
import client.proxy.Cookie;
import client.proxy.IProxy;
import client.proxy.Proxy;
import shared.definitions.CatanColor;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;

/**
 * Created by Joshua on 1/22/2016.
 */
public class NewMain {
    public static void main(String[] args){
        IProxy myProxy = new Proxy();
        try {
            myProxy.userLogin(new User("Pete", "pete"));
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }
        CatanColor myColor = CatanColor.BLUE;
        try {
            myProxy.gamesJoin(myColor.toString(), 0);
        } catch (InvalidUserException e) {
            e.printStackTrace();
        }

    }

}
