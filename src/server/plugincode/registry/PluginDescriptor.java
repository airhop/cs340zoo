package server.plugincode.registry;

import server.plugincode.iplugin.IPersistencePlugin;
import sun.plugin2.main.server.Plugin;
//import sun.plugin2.main.server.Plugin;

import java.util.List;

/**
 * Created by The Best Couple Ever on 4/2/2016.
 */
public class PluginDescriptor {

    public PluginDescriptor(String name, String classpath, String description)
    {
        this.name = name;
        this.classPath = classpath;
        this.description = description;
    }

    String name;
    String classPath;
    String description;
}
