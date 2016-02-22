package shared.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 2/22/2016.
 */
public class GameListDeserialize {
    JsonParser myParse;
    JsonElement myEle;
    JsonTreeReader myTree;
    JsonObject myObj;


    public GameListDeserialize(String jsonString){
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(jsonString);
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        JsonObject myObj = new JsonObject();
    }
    public List<String> deserialize(){
        List<String> games = new ArrayList<>();


        return games;
    }


}
