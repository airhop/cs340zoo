package client.proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Josh on 1/31/2016.
 */
public class Cookie {
    private String cookieName;
    private String cookieValue;
    private String decodedValue;

    public Cookie(){

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

    public String getDecode(){
        decodedValue = URLDecoder.decode(cookieValue);
        return decodedValue;
    }

    public String getFullCookie(){
        return cookieName + "=" + cookieValue;
    }
    public void setFullCookie(String given){
        cookieName = given.substring(0, 10);
        cookieValue = given.substring(11);
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        while(cookieValue.charAt(cookieValue.length() - 1) != ';'){
            cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
        }
        cookieValue = cookieValue.substring(0, cookieValue.length() - 1);
    }
}
