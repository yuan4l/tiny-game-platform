package cn.yuan.tiny.platform.client.auth;


import cn.yuan.tiny.platform.core.sql.AbstractSqlDataProvider;

import java.util.Map;

/**
 * 示例
 */
public class AuthSqlDataProvider extends AbstractSqlDataProvider<AuthSqlMapper> {

    private static class InstHolder {
        private static AuthSqlDataProvider inst = new AuthSqlDataProvider();
    }

    protected AuthSqlDataProvider() {
        super(AuthSqlMapper::new);
    }

    public static AuthSqlDataProvider getInstance() {
        return InstHolder.inst;
    }

    public Map<String, Object> getServerInfo() {
        return sqlMapper.getServerInfo();
    }

    public Map<String, Object> getAuthUser(int id) {
        return sqlMapper.getAuthUser(id);
    }
}
