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
        System.out.println(jsonString);
        JsonObject myObj = new JsonObject();
        String action = "";
        String myCurrent = "";
        int myIn = 0;
        boolean myBool = false;
        System.out.println(myTree.toString());
        try {
            while (!myTree.peek().name().equals("END_DOCUMENT")) {
                System.out.println(myTree.peek().name());
                switch (myTree.peek().name()) {
                    case "BEGIN_OBJECT":
                        myTree.beginObject();
                        break;
                    case "NAME":
                        myCurrent = myTree.nextName();
                        if (myCurrent.equals("deck")) {
                            action = myCurrent;
                        }
                        if(myCurrent.equals("map")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("players")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("log")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("chat")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("bank")){
                            action = myCurrent;
                        }
                        if(myCurrent.equals("turnTracker")){
                            action = myCurrent;
                        }
                        System.out.println(myCurrent);
                        break;
                    case "STRING":
                        myCurrent = myTree.nextString();
                        System.out.println(myCurrent);
                        break;
                    case "NUMBER":
                        myIn = myTree.nextInt();
                        System.out.println(myIn);
                        break;
                    case "END_OBJECT":
                        myTree.endObject();
                        break;
                    case "BEGIN_ARRAY":
                        myTree.beginArray();
                        break;
                    case "END_ARRAY":
                        myTree.endArray();
                        break;
                    case "BOOLEAN":
                        myBool = myTree.nextBoolean();
                        System.out.println(myBool);
                        break;
                }
            }
            myTree.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return myModel;
    }


}
