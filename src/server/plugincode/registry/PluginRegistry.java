package server.plugincode.registry;

import server.plugincode.iplugin.IPersistencePlugin;

import java.util.List;

/**
 * Created by The Best Couple Ever on 4/2/2016.
 */
public class PluginRegistry {
    /**
     * Method will be called to register a plugin to be created
     * @param inDescriptor - the descriptor to be registered
     */
    public void registerPlugin(PluginDescriptor inDescriptor) {
    }

    /**
     * called to get a list of all registered plugins
     * @return the list of available plugins
     */
    public List<PluginDescriptor> getAvailablePlugins() {
        return null;
    }

    /**
     * Called to get a specific type of plugin based upon it's descriptor
     * @param inDescriptor - the information to get the right plugin
     * @return the plugin that matches the descriptor
     */
    public IPersistencePlugin createPlugin(PluginDescriptor inDescriptor) {
        return null;
    }

    /**
     * unregisters a plugin
     * @param inDescriptor the plugin to unregister
     */
    public void unregisterPlugin(PluginDescriptor inDescriptor) {
    }

    /**
     * loads in the plugin config file that we will be putting the descriptors of the plugins
     */
    public void loadConfig() {
    }

    /**
     * not really used
     */
    public void saveConfig() {
    }
}
