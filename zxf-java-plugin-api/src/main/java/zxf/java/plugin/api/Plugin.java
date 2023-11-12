package zxf.java.plugin.api;

public interface Plugin {
    public String type();
    public Object execute(Object obj);
}
