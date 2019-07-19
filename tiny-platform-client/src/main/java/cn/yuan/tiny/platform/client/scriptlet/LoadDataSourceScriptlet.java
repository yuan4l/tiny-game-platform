package cn.yuan.tiny.platform.client.scriptlet;

import cn.yuan.tiny.platform.client.auth.AuthSqlDataProvider;
import cn.yuan.tiny.platform.client.sql.SqlEnvs;
import cn.yuan.tiny.platform.core.sql.SqlEnvCache;
import cn.yuan.tiny.platform.core.util.ExceptionLogger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001917:30
 */
public class LoadDataSourceScriptlet implements IScriptlet {
    private static Logger logger = LoggerFactory.getLogger(LoadDataSourceScriptlet.class);

    @Override
    public void onReady() throws ScriptletException {
        logger.error("初始化数据源");
        Stream<SqlEnvs> stream = Arrays.stream(SqlEnvs.values());
        stream.forEach(sqlEnvs -> {
            Stream<String> stream1 = Arrays.stream(sqlEnvs.getOpenEnv());
            stream1.forEach(openEnv -> {
                try {
                    logger.error("开始为" + sqlEnvs.name() + "创建数据库会话工厂" + " 读取路径->" + sqlEnvs.getValue());
                    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(sqlEnvs.getValue()), openEnv);
                    SqlEnvCache.getInstance().addObject(sqlEnvs, openEnv, factory);
                    logger.error(sqlEnvs.name() + "数据库会话工厂创建完毕");
                } catch (IOException e) {
                    ExceptionLogger.printLogger("生成SqlSessionFactory时异常了", e);
                }
            });
        });
        logger.error("服务器信息：" + AuthSqlDataProvider.getInstance().getServerInfo());
    }

    @Override
    public void onStart() throws ScriptletException {

    }

    @Override
    public void onStop() throws ScriptletException {

    }
}
