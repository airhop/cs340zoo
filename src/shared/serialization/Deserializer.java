package shared.serialization;

import client.model.GameModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;

import java.io.IOException;

public class Deserializer {

    /**
     * deserializes the JSON formatted data from the server
     *
     * @return object, you must cast this object to the thing you are deseralizing.
     */
    public GameModel deserialize(String jsonString, GameModel myModel) {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(jsonString);
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        JsonObject myObj = new JsonObject();
        try {
            while (myTree.hasNext()) {
                System.out.println(myTree.peek().name());
                switch (myTree.peek().name()) {
                    case "BEGIN_OBJECT":
                        myTree.beginObject();
                        break;
                    case "NAME":
                        System.out.println(myTree.nextName());
                        break;
                    case "STRING":
                        System.out.println(myTree.nextString());
                        break;


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return myModel;
    }


}
