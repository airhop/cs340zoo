package client.gui;

import client.facade.Facade;
import client.proxy.Cookie;
import client.proxy.IProxy;
import client.proxy.Proxy;
import shared.exceptions.InvalidUserException;
import shared.jsonobject.User;

/**
 * Created by Joshua on 1/22/2016.
 */
public class NewMain {
    public static void main(String[] args){
        Cookie myCook = new Cookie();
        System.out.println("This cow");
        String temp = "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D;Path=/;";
        myCook.setFullCookie(temp);
        System.out.println(myCook.getCookieName());
        System.out.println(myCook.getCookieValue());
        IProxy myProxy = new Proxy();
//        try {
//            myProxy.userLogin(new User("Sam", "sam"));
//        } catch (InvalidUserException e) {
//            e.printStackTrace();
//        }
        Facade myFacade = new Facade();

    }

}
