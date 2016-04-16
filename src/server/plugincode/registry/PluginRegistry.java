package server.plugincode.registry;

import server.plugincode.TextPlugin.TextPersistencePlugin;
import server.plugincode.iplugin.IPersistencePlugin;
import server.plugincode.sql.SqlPersistencePlugin;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

/**
 * Created by The Best Couple Ever on 4/2/2016.
 */
public class PluginRegistry {

    private List<PluginDescriptor> pluginDescriptors;



    public PluginRegistry(){
        pluginDescriptors = new ArrayList<>();
    }

    public void setPluginDescriptors(List<PluginDescriptor> pluginDescriptors) {
        this.pluginDescriptors = pluginDescriptors;
    }

    public List<PluginDescriptor> getPluginDescriptors() {
        return pluginDescriptors;
    }

    /**
     * Method will be called to register a plugin to be created
     *
     * @param inDescriptor - the descriptor to be registered
     */


    public void registerPlugin(PluginDescriptor inDescriptor) {

    }

    /**
     * called to get a list of all registered plugins
     *
     * @return the list of available plugins
     */
    public List<PluginDescriptor> getAvailablePlugins() {
        return this.pluginDescriptors;
    }

    /**
     * Called to get a specific type of plugin based upon it's descriptor
     *
     * @param inDescriptor - the information to get the right plugin
     * @return the plugin that matches the descriptor
     */
    public IPersistencePlugin createPlugin(PluginDescriptor inDescriptor) {
        IPersistencePlugin persPlugin = null;
        ClassLoader classLoader = PluginRegistry.class.getClassLoader();

        String urlOne = "jar:file:plugin/text.jar!/";
        URL myUrlOne = null;
        try {
            myUrlOne = new URL(urlOne);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String urlTwo = "jar:file:plugin/sql.jar!/";
        URL myUrlTwo = null;
        try {
            myUrlTwo = new URL(urlTwo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            myUrlOne.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


        URLClassLoader cl = new URLClassLoader(new URL[]{myUrlOne, myUrlTwo});

        ClassLoader loader = this.getClass().getClassLoader();
        for (PluginDescriptor pd : pluginDescriptors) {
            try {



                String url = "jar:file:" + pd.classPath + "!/" + pd.description;
                URL myUrl = new URL(url);
                URLConnection connection = myUrl.openConnection();

                loader.getResource(url);

//                Class clazz = Class.forName(url);
//
//                Class aClass = cl.loadClass(pd.description);
                if (pd.getName().equals("SQLP") && inDescriptor.name.equals("SQL")) {
                    persPlugin = new SqlPersistencePlugin();
                } else if (pd.getName().equals("TXTP") && inDescriptor.name.equals("TXT")) {
                    persPlugin = new TextPersistencePlugin();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return persPlugin;
    }

    /**
     * unregisters a plugin
     *
     * @param inDescriptor the plugin to unregister
     */
    public void unregisterPlugin(PluginDescriptor inDescriptor) {
    }

    /**
     * loads in the plugin config file that we will be putting the descriptors of the plugins
     */
    public void loadConfig() {
        String fileName = "plugin/config.txt";
        String line = null;

        try {
//            File f = new File(fileName);
//            System.out.println(f.getAbsolutePath());

            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String stringLine = line.toString();
                String[] tokens = stringLine.split(" ");
                String desName = tokens[0];
                String classpath = tokens[1];
                String descriptor = tokens[2];
                PluginDescriptor newDescriptor = new PluginDescriptor(desName, classpath, descriptor);
                this.pluginDescriptors.add(newDescriptor);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }


    /**
     * not really used
     */
    public void saveConfig() {
    }
}
