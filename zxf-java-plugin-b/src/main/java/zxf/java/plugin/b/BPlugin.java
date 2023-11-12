package zxf.java.plugin.b;

import zxf.java.plugin.api.Plugin;

public class BPlugin implements Plugin {
    @Override
    public String type() {
        return "B";
    }

    @Override
    public Object execute(Object obj) {
        return obj.toString() + "From B Plugin";
    }
}
