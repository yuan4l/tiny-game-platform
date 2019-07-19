package cn.yuan.tiny.platform.client.sql;

import cn.yuan.tiny.platform.client.BootStrap;
import cn.yuan.tiny.platform.core.sql.ISqlEnvs;
import cn.yuan.tiny.platform.core.sql.SqlConstants;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:59
 */
public enum SqlEnvs implements ISqlEnvs {
    /**
     * 游戏服
     */
    GAME_SERVER("game_server_config.xml", SqlConstants.DB_ENV_DEFAULT),
    /**
     * 登录服
     */
    AUTH_SERVER("auth_config.xml", SqlConstants.DB_ENV_DEFAULT),;

    private String file;
    private String[] openEnv;

    private SqlEnvs(String file, String... openEnv) {
        this.file = file;
        this.openEnv = openEnv;
    }

    public String getValue() {
        return "jdbc/" + SqlConstants.DB_ENV[BootStrap.SERVICE_FLAG] + "/" + file;
    }

    public String[] getOpenEnv() {
        return this.openEnv;
    }
}
