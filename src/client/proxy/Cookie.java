package client.proxy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

    public String getDecode() {
        decodedValue = URLDecoder.decode(cookieValue);
        return decodedValue;
    }

    public String getPlayerId() {
        String temp;
        if (myId.equals("")) {
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
                    myId = myTree.nextString();
                    System.out.println(myId);
                }
                return myId;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return myId;
        }
        return myId;
    }

    public String getFullCookie() {
        return cookieName + "=" + cookieValue;
    }

    public void setFullCookie(String given) {
        cookieName = given.substring(0, 10);
        cookieValue = given.substring(11);
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        while (cookieValue.charAt(cookieValue.length() - 1) != ';') {
            cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        }
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
    }
}
