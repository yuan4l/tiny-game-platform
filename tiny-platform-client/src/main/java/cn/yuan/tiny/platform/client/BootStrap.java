package cn.yuan.tiny.platform.client;

import cn.yuan.tiny.platform.client.scriptlet.IScriptlet;
import cn.yuan.tiny.platform.client.scriptlet.LoadConfigScriptlet;
import cn.yuan.tiny.platform.client.scriptlet.LoadDataSourceScriptlet;
import cn.yuan.tiny.platform.client.scriptlet.ScriptletException;
import cn.yuan.tiny.platform.core.util.ExceptionLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:45
 */
public class BootStrap {
    private static Logger logger = LoggerFactory.getLogger(BootStrap.class);
    public static byte SERVICE_FLAG = 0;
    public static int SERVICE_PORT = 0;


    public static void main(String[] args) {
        String nymph = "\n\r" +
                "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓【女神保佑，永无BUG】↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓\n\r" +
                "                       .::::.\n\r" +
                "                     .::::::::.\n\r" +
                "                    :::::::::::\n\r" +
                "                 ..:::::::::::'\n\r" +
                "              '::::::::::::'\n\r" +
                "                .::::::::::\n\r" +
                "           '::::::::::::::..\n\r" +
                "                ..::::::::::::.\n\r" +
                "              ``::::::::::::::::\n\r" +
                "               ::::``:::::::::'        .:::.\n\r" +
                "              ::::'   ':::::'       .::::::::.\n\r" +
                "            .::::'      ::::     .:::::::'::::.\n\r" +
                "           .:::'       :::::  .:::::::::' ':::::.\n\r" +
                "          .::'        :::::.:::::::::'      ':::::.\n\r" +
                "         .::'         ::::::::::::::'         ``::::.\n\r" +
                "     ...:::           ::::::::::::'              ``::.\n\r" +
                "    ```` ':.          ':::::::::'                  ::::..\n\r" +
                "                       '.:::::'                    ':'````..\n\r" +
                "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑【女神保佑，永无BUG】↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑\n\r";
        logger.error(nymph);
        System.out.println(nymph);
        try {
            registerScriptlet();

            GameStartScriptletProxy.getInstance().onReady();
            GameStartScriptletProxy.getInstance().onStart();

        } catch (ScriptletException e) {
            ExceptionLogger.printLogger("服务启动失败，系统退出！", e);
            System.exit(0);
        }
    }

    private static void registerScriptlet() {
        //起服有序执行脚本
        GameStartScriptletProxy.getInstance().newScriptlet(LoadConfigScriptlet::new);
        GameStartScriptletProxy.getInstance().newScriptlet(LoadDataSourceScriptlet::new);
    }

    public static class GameStartScriptletProxy implements IScriptlet {
        private static class InstHolder {
            private static GameStartScriptletProxy inst = new GameStartScriptletProxy();
        }

        public static GameStartScriptletProxy getInstance() {
            return InstHolder.inst;
        }

        private static List<IScriptlet> list = new LinkedList<IScriptlet>();

        public void newScriptlet(Supplier<? extends IScriptlet> supplier) {
            list.add(supplier.get());
        }

        @Override
        public void onReady() throws ScriptletException {
            list.forEach(action -> {
                try {
                    action.onReady();
                } catch (ScriptletException e) {
                    ExceptionLogger.printLogger(e);
                }
            });
        }

        @Override
        public void onStart() throws ScriptletException {
            list.forEach(action -> {
                try {
                    action.onStart();
                } catch (ScriptletException e) {
                    ExceptionLogger.printLogger(e);
                }
            });

            logger.error("服务器启动成功！");
        }

        @Override
        public void onStop() throws ScriptletException {
            list.forEach(action -> {
                try {
                    action.onStop();
                } catch (ScriptletException e) {
                    ExceptionLogger.printLogger(e);
                }
            });

            logger.error("服务器关闭成功！");
        }
    }
}
