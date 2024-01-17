package zxf.java.plugin.test;

import zxf.java.plugin.api.Plugin;
import zxf.java.plugin.api.PluginManager;


public class TestStaticPlugin {
    public static void main(String[] args) throws Exception {
        Plugin pluginA = PluginManager.getInstance().getPlugin("A");
        System.out.println(pluginA.execute("test for Plugin A: "));
        Plugin pluginB = PluginManager.getInstance().getPlugin("B");
        System.out.println(pluginB.execute("test for Plugin B: "));
    }
}
