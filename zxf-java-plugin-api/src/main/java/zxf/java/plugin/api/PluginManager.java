package zxf.java.plugin.api;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class PluginManager {
    private static volatile PluginManager instance;

    public static PluginManager getInstance() throws Exception {
        if (instance == null) {
            Enumeration<URL> pluginConfigUrls = PluginManager.class.getClassLoader().getResources("META-INF/zxf/java/plugin/plugin.conf");

            ConcurrentHashMap<String, Plugin> registeredPlugins = new ConcurrentHashMap<>();
            while (pluginConfigUrls.hasMoreElements()) {
                URL pluginConfigUrl = pluginConfigUrls.nextElement();
                try (InputStream stream = pluginConfigUrl.openStream()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    Class klass = Class.forName(reader.readLine());
                    Plugin plugin = (Plugin) klass.getDeclaredConstructor().newInstance();
                    registeredPlugins.put(plugin.type(), plugin);
                }
            }
            instance = new PluginManager(registeredPlugins);
        }
        return instance;
    }

    private final ConcurrentHashMap<String, Plugin> registeredPlugins;

    private PluginManager(ConcurrentHashMap<String, Plugin> registeredPlugins) {
        this.registeredPlugins = registeredPlugins;
    }

    public Plugin getPlugin(String type) {
        return registeredPlugins.get(type);
    }
}
