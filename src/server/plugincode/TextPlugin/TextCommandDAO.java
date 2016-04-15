package server.plugincode.TextPlugin;

import com.google.gson.GsonBuilder;
import server.commandobjects.ICommand;
import server.plugincode.iplugin.ICommandDAO;
import server.shared.CommandType;
import shared.jsonobject.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Joshua on 4/2/2016.
 */
public class TextCommandDAO implements ICommandDAO {

    private String fileName = "TextData/Commands";
    public TextCommandDAO() {

    }

    /**
     * Adds a command to the command table
     *
     * @param command Command to add
     * @param gameId  Game command to be added
     */

    @Override
    public void addCommand(ICommand command, int gameId)
    {
        try {
            String name = fileName + gameId + ".txt";
            File file = new File(name);
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            GsonBuilder gson = new GsonBuilder();
            String info = gson.create().toJson(command);
            bw.write(command.getType() + " " + info + "\n");
            bw.close();

        } catch(IOException e)
        {
            System.out.println("IOException error in TextGameDAO addGame");
        }

    }

    /**
     * Takes all commands from the database
     *
     * @param gameId game to get the commands for
     * @return list of all commands
     */
    @Override
    public List<ICommand> readAllCommands(int gameId)
    {
        List<ICommand> commands = new ArrayList<>();
        try{
            File file = new File(fileName + gameId + ".txt");
            if(!file.exists())
                return commands;

            GsonBuilder gson = new GsonBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null)
            {
                Scanner scan = new Scanner(line);
                String type = scan.next();
                String newCommand = scan.nextLine();
                CommandType ct = CommandType.convert(type);
                commands.add(CommandType.getClass(ct, newCommand));
                line = br.readLine();
            }
            br.close();

        }catch (IOException e)
        {
            System.out.println("Error in TextCommandDAO readAllPlayers");

        }
        return commands;
    }

    /**
     * removes a game, may not need this
     *
     * @param gameId GameId to be dropped
     */
    @Override
    public void clearGame(int gameId) {
        File file = new File(fileName + gameId + ".txt");
        if(file.exists())
            file.delete();
    }

    /**
     * Drops all tables
     */
    @Override
    public void clearAll()
    {
        File file = new File("TextData");
        File[] files = file.listFiles();
        for(int i = 0 ; i < files.length; i++)
            if(files[i].getAbsolutePath().contains("Commands"))
                files[i].delete();
    }
}
