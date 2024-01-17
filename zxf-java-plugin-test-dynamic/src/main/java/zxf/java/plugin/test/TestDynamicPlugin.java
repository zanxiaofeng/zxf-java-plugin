package zxf.java.plugin.test;

import zxf.java.plugin.api.Plugin;
import zxf.java.plugin.api.PluginManager;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;


public class TestDynamicPlugin {
    public static void main(String[] args) throws Exception {
        URL[] depends = new URL[]{new URL("file:" + Paths.get("zxf-java-plugin-test-dynamic/libraries/zxf-java-plugin-a-1.0-SNAPSHOT.jar").toAbsolutePath()), new URL("file:" + Paths.get("zxf-java-plugin-test-dynamic/libraries/zxf-java-plugin-b-1.0-SNAPSHOT.jar").toAbsolutePath())};
        System.out.println(depends[0]);

        ClassLoader originClassloader = Thread.currentThread().getContextClassLoader();
        try (URLClassLoader urlClassLoader = new URLClassLoader(depends)) {
            Thread.currentThread().setContextClassLoader(urlClassLoader);
            Plugin pluginA = PluginManager.getInstance().getPlugin("A");
            System.out.println(pluginA.execute("test for Plugin A: "));
            Plugin pluginB = PluginManager.getInstance().getPlugin("B");
            System.out.println(pluginB.execute("test for Plugin B: "));
        } finally {
            Thread.currentThread().setContextClassLoader(originClassloader);
        }
    }
}
