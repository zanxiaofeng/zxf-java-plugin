package zxf.java.plugin.a;

import zxf.java.plugin.api.Plugin;

public class APlugin implements Plugin {
    @Override
    public String type() {
        return "A";
    }

    @Override
    public Object execute(Object obj) {
        return obj.toString() + "From A Plugin";
    }
}
