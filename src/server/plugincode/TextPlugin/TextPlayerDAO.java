package server.plugincode.TextPlugin;

import client.model.GameModel;
import com.google.gson.GsonBuilder;
import server.plugincode.iplugin.IPlayerDAO;
import shared.jsonobject.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 4/2/2016.
 */
public class TextPlayerDAO implements IPlayerDAO{

    private String fileName = "TextData/Players.txt";
    public TextPlayerDAO()
    {
        try {
            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();
        } catch (IOException e){
            System.out.println("Error creating the TextPlayerDAO");
        }
    }
    /**
     * Adds a player to the table
     * @param player The player that is going to be put into the table
     */
    @Override
    public void addPlayer(Login player)
    {
        try {
            File file = new File(fileName);

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            GsonBuilder gson = new GsonBuilder();
            String info = gson.create().toJson(player);
            bw.write(info);
            bw.write("\n");
            bw.close();

        } catch(IOException e)
        {
            System.out.println("IOException error in TextPlayerDAO addPlayer");
        }
    }

    /**
     * Reads all the players from the table
     * @return List of our players
     */
    @Override
    public List<Login> readAllPlayers()
    {
        List<Login> logins = new ArrayList<Login>();
        try{
            File file = new File(fileName);
            if(!file.exists())
                return logins;

            GsonBuilder gson = new GsonBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null)
            {
                   logins.add((Login)gson.create().fromJson(line, Login.class));
                    line = br.readLine();
            }
            br.close();

        }catch (IOException e)
        {
            System.out.println("Error in TextPlayerDAO readAllPlayers");
        }
        return logins;
    }

    /**
     * Clears all the tables
     */
    @Override
    public void clearTable()
    {
        File file = new File(fileName);
        if(file.exists())
            file.delete();

    }
}
