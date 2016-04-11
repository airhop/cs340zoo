package server.servermain;

import server.plugincode.TextPlugin.TextPersistencePlugin;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.sql.SqlPersistencePlugin;

/**
 * Created by GaryPetersen on 4/11/2016.
 */
public class Clearing
{
    public static void main(String[] args)
    {
        if(args.length == 0)
            return;
        String type = args[0];
        IPersistencePlugin ipp;
        if(type.equals("TXT"))
            ipp = new TextPersistencePlugin();
        else if(type.equals("SQL"))
            ipp = new SqlPersistencePlugin();
        else
            return;

        ipp.getGameDAO().clearTable();
        ipp.getPlayerDAO().clearTable();
        ipp.getCommandDAO().clearAll();
    }


}
