package shared.serialization;

import client.MVC.data.GameInfo;
import client.MVC.data.PlayerInfo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import shared.definitions.CatanColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 2/22/2016.
 */
public class GameListDeserialize {
    JsonParser myParse;
    JsonElement myEle;
    JsonTreeReader myTree;


    public GameListDeserialize(String jsonString) {
        myParse = new JsonParser();
        myEle = myParse.parse(jsonString);
        myTree = new JsonTreeReader(myEle);

    }

    public List<GameInfo> deserialize() {
        List<GameInfo> games = new ArrayList<>();
        GameInfo game;
        PlayerInfo player;
        try {
            myTree.beginArray();
            while (myTree.peek().name().equals("BEGIN_OBJECT")) {
                game = new GameInfo();
                myTree.beginObject();
                myTree.nextName();
                game.setTitle(myTree.nextString());
                myTree.nextName();
                game.setId(myTree.nextInt());
                myTree.nextName();
                myTree.beginArray();
                int i;
                while (myTree.peek().name().equals("BEGIN_OBJECT")) {
                    i = 0;
                    player = new PlayerInfo();
                    player.setPlayerIndex(i);
                    i++;
                    myTree.beginObject();
                    myTree.nextName();
                    player.setColor(CatanColor.convert(myTree.nextString()));
                    myTree.nextName();
                    player.setName(myTree.nextString());
                    myTree.nextName();
                    player.setId(myTree.nextInt());
                    game.addPlayer(player);
                    myTree.endObject();
                }
                myTree.endArray();
                myTree.endObject();
                games.add(game);
//                System.out.println("Here i am " + myTree.peek().name());
//                System.out.println("Here i am " + myTree.nextName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return games;
    }


}
