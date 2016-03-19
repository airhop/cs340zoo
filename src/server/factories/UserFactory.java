package server.factories;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.commandobjects.user.LoginUser;
import server.commandobjects.user.RegisterUser;
import server.servermain.JsonConstructionInfo;

import java.io.IOException;

/**
 * Created by Joshua on 3/10/2016.
 */
public class UserFactory {
    public UserFactory(){

    }
    /**
     * This is the method that you call when you want a Command object that is in the User category
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand command = new AcceptTrade(1, false);
        switch(info.getName()){
            case login:
                return makeLoginUser(info);
            case register:
                return makeRegisterUser(info);
        }
        return command;
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public LoginUser makeLoginUser(JsonConstructionInfo info){
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);

        String username = "";
        String password = "";


        try {
            myTree.beginObject();
            myTree.nextName(); //This is the first thing which is the String for the username
            username = myTree.nextString(); //This is the Username
            myTree.nextName(); //This is the String password name
            password = myTree.nextString(); //This is the Password

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Username = " + username + " Password = " + password);
        return new LoginUser(username, password);
    }

    /**
     * This method will create the appropriate Command Object
     * @param info - Passed to the function to create
     * @return - Returns the appropriate Command Object
     */
    public RegisterUser makeRegisterUser(JsonConstructionInfo info)
    {
        JsonParser myParse = new JsonParser();
        JsonElement myEle = myParse.parse(info.getJsonBody());
        JsonTreeReader myTree = new JsonTreeReader(myEle);

        String username = "";
        String password = "";

        try {
            myTree.beginObject();
            myTree.nextName(); //This is the first thing which is the String for the username
            username = myTree.nextString(); //This is the Username
            myTree.nextName(); //This is the String password name
            password = myTree.nextString(); //This is the Password

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RegisterUser(username,password);
    }

}
