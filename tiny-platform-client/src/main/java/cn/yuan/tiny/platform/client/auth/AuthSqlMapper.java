package cn.yuan.tiny.platform.client.auth;


import cn.yuan.tiny.platform.client.sql.SingleAuthServerSqlMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例
 */
public class AuthSqlMapper extends SingleAuthServerSqlMapper {

    @SuppressWarnings("unchecked")
    public Map<String, Object> getServerInfo() {
        return this.selectOne("getServerInfo", HashMap.class, 4);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getAuthUser(int id) {
        return this.selectOne("getAuthUser", HashMap.class, id);
    }
}
