package client.proxy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import shared.jsonobject.Login;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Josh on 1/31/2016.
 */
public class Cookie {
    private String cookieName = "";
    private String cookieValue = "";
    private String decodedValue = "";
    private String myId;

    public Cookie() {
        myId = "";
    }

    public Cookie(Login l)
    {
        cookieName = l.getUsername();
        cookieValue = l.getPassword();
        decodedValue = "";
        myId = Integer.toString(l.getID());
    }

    public Cookie(String n, String v, String id)
    {
        cookieName = n;
        cookieValue = v;
        decodedValue = "";
        myId = id;
    }

    public Cookie(int GameID)
    {
        cookieName = GameID + ""; //need to set to something in order to keep the isActive() method valid
        cookieValue = "";
        decodedValue = "";
        myId = GameID +"";
    }

    public boolean isActive() {
        if (cookieName.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public int retrieveID()
    {
        try {
            return Integer.parseInt(myId);
        }
        catch(NumberFormatException e)
        {
            return Integer.parseInt(myId.substring(0, myId.length() - 1));
        }
    }

    public String getDecode() {
        decodedValue = URLDecoder.decode(cookieValue);
        return decodedValue;
    }

    public String getPlayerId() {
        String temp;
        if (decodedValue.equals("")) {
            getDecode();
        }
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(decodedValue);
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        try {
            if (myTree.hasNext()) {
                myTree.beginObject();
                myTree.nextName();
                myTree.nextString();
                myTree.nextName();
                myTree.nextString();
                myTree.nextName();
                System.out.println(myTree.peek().name());
                if(!myTree.peek().name().equals("STRING")){
                    myId = myTree.nextString();
                }else{
                    myTree.nextString();
                    myTree.nextName();
                    myId = myTree.nextString();
                }


                System.out.println(myId);
            }
            return myId;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return myId;
    }

    public String getFullCookie() {
        return cookieName + "=" + cookieValue;
    }

    public void setFullCookie(String given) {
        if(given == null)
            return;
        System.out.println("Given = " + given);
        cookieName = given.substring(0, 10);
        cookieValue = given.substring(11);
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        while (cookieValue.charAt(cookieValue.length() - 1) != ';') {
            cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        }
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
    }

    public String toString()
    {
        System.out.println("Cookie.toString() cn " + cookieName + " cv " + cookieValue + " id "  + myId + " dc " + decodedValue);
        return cookieName + " " + cookieValue + " " + myId;
    }

    public void createCookie(String encoded)
    {
        if(encoded == null)
            return;
        System.out.println("Given = " + encoded + "\nSize = " + encoded.length());
        encoded = encoded.substring(17);  //catan.user=%xx%xx%xx

        System.out.println("Given = " + encoded + "\nSize = " + encoded.length());
        Scanner scan = new Scanner(encoded);
        scan.useDelimiter("%..%..%..");
        scan.next();
        cookieName = scan.next();
        System.out.println("Name " + cookieName);
        scan.next();
        cookieValue = scan.next();
        System.out.println("password " + cookieValue);

        String ID = scan.next();
        ID = ID.substring(14);
        myId = ID.substring(0, ID.length() - 3);
        System.out.println("ID " + myId);
        System.out.println("survived?");
    }

    public String encode()
    {
        String user = "catan.user=";
        String inbetween = "%22%3A%22";
       // String passback = user + inbetween + "name" + inbetween + cookieName + inbetween + "password" + inbetween
       //         + "playerID" + "%22%3A" + myId + "%7D";



        String cookie = user + "name " + cookieName + " password " + cookieValue + " playerID " + myId;
        try{
            System.out.println(URLEncoder.encode(cookie, "UTF-8"));
            return URLEncoder.encode(cookie, "UTF-8");
        }
        catch( UnsupportedEncodingException e)
        {
            System.out.println("Bad Encoder");
            return URLEncoder.encode(cookie);

        }

    }
}