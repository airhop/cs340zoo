package server.plugincode.registry;

import server.plugincode.iplugin.IPersistencePlugin;

import java.util.List;

/**
 * Created by The Best Couple Ever on 4/2/2016.
 */
public class PluginRegistry {
    public void registerPlugin(PluginDescriptor inDescriptor) {
    }

    public List<PluginDescriptor> getAvailablePlugins() {
        return null;
    }

    public IPersistencePlugin createPlugin(PluginDescriptor inDescriptor) {
        return null;
    }

    public void unregisterPlugin(PluginDescriptor inDescriptor) {
    }

    public void loadConfig() {
    }

    public void saveConfig() {
    }
}
