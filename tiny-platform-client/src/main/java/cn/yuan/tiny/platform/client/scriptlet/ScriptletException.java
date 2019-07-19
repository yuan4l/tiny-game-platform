package cn.yuan.tiny.platform.client.scriptlet;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:10
 */
public class ScriptletException extends Exception {
    private static final long serialVersionUID = 1L;

    public ScriptletException(String name) {
        super(name);
    }

    public ScriptletException(String name, Throwable t) {
        super(name, t);
    }
}
