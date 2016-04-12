package server.plugincode.registry;

import server.plugincode.iplugin.IPersistencePlugin;

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

        File gameJar = new File("plugin/sql.jar");

        File gameDependenciesJar = new File("plugin/text.jar");


        URLClassLoader cl = null;
        try {
            cl = new URLClassLoader(new URL[]{gameJar.toURI().toURL(), gameDependenciesJar.toURI().toURL()});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        for (PluginDescriptor pd : pluginDescriptors) {
            try {

                Class clazz = Class.forName(pd.description, true, cl);

//                JarURLConnection connection = (JarURLConnection) url.openConnection();
//                JarFile file = connection.getJarFile();
//                String jarPath = file.getName();

                Class aClass = cl.loadClass(pd.description);
                if (pd.getName().equals("SQLP") && inDescriptor.name.equals("SQL")) {
                    persPlugin = (IPersistencePlugin) aClass.getConstructors()[0].newInstance();
                } else if (pd.getName().equals("TEXTP") && inDescriptor.name.equals("TXT")) {
                    persPlugin = (IPersistencePlugin) aClass.getConstructors()[0].newInstance();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
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
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
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
