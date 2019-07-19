package cn.yuan.tiny.platform.client.scriptlet;

import cn.yuan.tiny.platform.client.BootStrap;
import cn.yuan.tiny.platform.core.sql.SqlConstants;
import cn.yuan.tiny.platform.core.util.ExceptionLogger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:09
 */
public class LoadConfigScriptlet implements IScriptlet {
    private static final String SERVER_CONFIG_FILE = "debug_server.xml";
    private static Logger logger = LoggerFactory.getLogger(LoadConfigScriptlet.class);

    public void onReady() throws ScriptletException {
        SAXReader saxReader = new SAXReader();
        try {
            Document paramsXMLDoc = saxReader.read(LoadConfigScriptlet.class.getClassLoader().getResourceAsStream(SERVER_CONFIG_FILE));
            Element root = paramsXMLDoc.getRootElement();

            BootStrap.SERVICE_PORT = Integer.valueOf(root.elementTextTrim("service_port"));
            logger.error("服务端口：" + BootStrap.SERVICE_PORT);
            System.out.println("服务端口：" + BootStrap.SERVICE_PORT);
            BootStrap.SERVICE_FLAG = Byte.valueOf(root.elementTextTrim("service_flag"));
            logger.error("服务标识：" + SqlConstants.DB_ENV[BootStrap.SERVICE_FLAG]);
            System.out.println("服务标识：" + SqlConstants.DB_ENV[BootStrap.SERVICE_FLAG]);
        } catch (Exception e) {
            ExceptionLogger.printLogger("初始化服务器相关配置参数失败，错误信息：" + e.getMessage(), e);
        }

    }

    public void onStart() throws ScriptletException {

    }

    public void onStop() throws ScriptletException {

    }
}
