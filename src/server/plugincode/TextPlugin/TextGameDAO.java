package server.plugincode.TextPlugin;

import client.model.GameModel;
import com.google.gson.GsonBuilder;
import server.plugincode.iplugin.IGameDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class TextGameDAO implements IGameDAO {


    private String fileName = "TextData/Game";
    public TextGameDAO()
    {

    }

    /**
     * Add a gameModel to the database
     * @param addModel the model that you are adding
     * @param id id to put in with the model
     */
    @Override
    public void addGame(GameModel addModel, int id)
    {
        try {
            String name = fileName + id + ".txt";
            File file = new File(name);
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
            String info = gson.create().toJson(addModel);
            bw.write(info);
            bw.close();

        } catch(IOException e)
        {
            System.out.println("IOException error in TextGameDAO addGame");
        }
    }

    /**
     * reads a game model with an id
     * @param gameId the game to read from the database
     * @return returns a gameModel
     */
    @Override
    public GameModel readGame(int gameId)
    {
        try {
            File file = new File(fileName + gameId + ".txt");
            if (!file.exists())
                return null;

            BufferedReader br = new BufferedReader(new FileReader(file));
            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
            GameModel gm =  gson.create().fromJson(br.readLine(), GameModel.class);
            br.close();
            return gm;

        }catch(IOException e)
        {
            System.out.println("Error in the TextGameDAO readGame");
        }
        return null;
    }

    public List<GameModel> readAllGames()
    {
        List<GameModel> games = new ArrayList<GameModel>();

        try {
            File file = new File("TextData");
            File[] files = file.listFiles();
            for(int i = 0; i < files.length; i++)
            {
                if(files[i].getAbsolutePath().contains("Game"))
                {
                    BufferedReader br = new BufferedReader(new FileReader(files[i]));
                    GsonBuilder gson = new GsonBuilder();
                    gson.enableComplexMapKeySerialization();
                    GameModel gm =  gson.create().fromJson(br.readLine(), GameModel.class);
                    br.close();
                    games.add(gm);

                }
            }

            return games;

        }catch(IOException e)
        {
            System.out.println("Error in the TextGameDAO readGame");
        }


        return games;
    }
    /**
     * Updates a gameModel in the database, from an Id
     * @param gameId The game id that you are grabbing
     * @param updateModel The model that you are updating with
     */
    @Override
    public void updateGame(int gameId, GameModel updateModel)
    {
        try {
            File file = new File(fileName + gameId + ".txt");
            if (file.exists())
                file.delete();
            else
                return;
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            GsonBuilder gson = new GsonBuilder();
            gson.enableComplexMapKeySerialization();
            String info = gson.create().toJson(updateModel);

            bw.write(info);
            bw.close();

        }catch(IOException e)
        {
            System.out.println("Error on the TextGameDAO updateGame");
        }
    }

    /**
     * Drop all tables
     */
    @Override
    public void clearTable()
    {
        File file = new File("TextData");
        File[] files = file.listFiles();
        for(int i = 0 ; i < files.length; i++)
            if(files[i].getAbsolutePath().contains("Game"))
                files[i].delete();

    }
}
