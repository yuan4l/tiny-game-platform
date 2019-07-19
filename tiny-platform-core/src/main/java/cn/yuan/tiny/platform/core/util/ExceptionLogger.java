package cn.yuan.tiny.platform.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:15
 */
public class ExceptionLogger {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);

    public ExceptionLogger() {
    }

    public static void printLogger(String paramString, Throwable throwable) {
        logger.info(paramString, throwable);
    }

    public static void printLogger(Throwable throwable) {
        logger.info((String)null, throwable);
    }
}
