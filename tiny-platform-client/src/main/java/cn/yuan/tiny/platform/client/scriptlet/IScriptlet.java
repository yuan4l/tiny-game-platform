package cn.yuan.tiny.platform.client.scriptlet;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:09
 */
public interface IScriptlet {
    void onReady() throws ScriptletException;

    void onStart() throws ScriptletException;

    void onStop() throws ScriptletException;
}
