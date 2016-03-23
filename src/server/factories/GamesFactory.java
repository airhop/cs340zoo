package server.factories;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import jdk.nashorn.internal.parser.JSONParser;
import server.commandobjects.ICommand;
import server.commandobjects.games.*;
import server.servermain.JsonConstructionInfo;

import java.io.IOException;

/**
 * Created by Joshua on 3/10/2016.
 */
public class GamesFactory {
    public GamesFactory() {

    }

    /**
     * This is the method that you call when you want a Command object that is in the games category
     *
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info) {
        ICommand commandObject;
        commandObject = new Create(true, true, true, "GameName");
        switch(info.getName()){
            case create:
                return makeCreate(info);
            case join:
                return makeJoin(info);
        }

        return commandObject;
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Create makeCreate(JsonConstructionInfo info) {
        System.out.println("Making a game creation for " + info.getJsonBody());
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);

        boolean randomTiles = false;
        boolean randomPorts = false;
        boolean randomNumbers = false;
        String gameName = "";

        int i = 0;
        System.out.println("Parsing the game . . ." + i++);
        try {
          //  System.out.println("Parsing the game . . ." + i++ + " " + myTree.peek().name());
          //  myTree.beginObject();
            System.out.println("Parsing the game . . ." + i++ + " " + myTree.peek().name() + " " + myTree.peek());
            String information = myTree.nextString();  //This is the first boolean which is the random tiles
            System.out.println("information " + information);
            System.out.println("Parsing the game . . ." + i++ + " " + myTree.peek().name());
            randomTiles = myTree.nextBoolean(); //This is the Random Tiles Boolean
            System.out.println("Parsing the game . . ." + i++);
            myTree.nextName(); //This is the name == randomNumbers
            System.out.println("Parsing the game . . ." + i++);
            randomNumbers = myTree.nextBoolean(); //This is the boolean randomNumbers
            System.out.println("Parsing the game . . ." + i++);
            myTree.nextName(); //This is the randomPorts
            System.out.println("Parsing the game . . ." + i++);
            randomPorts = myTree.nextBoolean(); //This is the boolean randomPorts
            System.out.println("Parsing the game . . ." + i++);
            myTree.nextName(); //this is the gameName == name
            System.out.println("Parsing the game . . ." + i++);
            gameName = myTree.nextString(); //this is the name of the game
            System.out.println("Parsing the game . . ." + i++);

        } catch (IOException e) {
            System.out.println("error with an exception being tossed?");
            e.printStackTrace();
        }



        System.out.println("Information " + randomTiles + " " + randomPorts + " " + randomNumbers + " " + gameName);
        return new Create(randomTiles, randomPorts, randomNumbers, gameName);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public Join makeJoin(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);
        int playerID = -1;
        String playerColor = "";
        try {
            myTree.beginObject();
            myTree.nextName();  //This is the first ind which is the gameID
            playerID = myTree.nextInt(); //This is the gameID
            myTree.nextName(); //This is the name of the Color
            playerColor = myTree.nextString(); //This is the color of the player
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Join(playerID, playerColor);
    }
}
