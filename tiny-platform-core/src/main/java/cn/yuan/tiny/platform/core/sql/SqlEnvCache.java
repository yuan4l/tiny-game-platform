package cn.yuan.tiny.platform.core.sql;

import cn.yuan.tiny.platform.core.cache.AbstractObjectCache;
import cn.yuan.tiny.platform.core.cache.IObjectCacheManager;
import cn.yuan.tiny.platform.core.cache.ObjectCacheFactory;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:12
 */
public class SqlEnvCache extends AbstractObjectCache<ISqlEnvs, Map<String, SqlSessionFactory>> {
    private IObjectCacheManager<ISqlEnvs, Map<String, SqlSessionFactory>> cacheManager = ObjectCacheFactory.createUnlimitedMapCacheManager(this, true);

    public SqlEnvCache() {
    }

    public static SqlEnvCache getInstance() {
        return SqlEnvCache.InstHolder.inst;
    }

    public IObjectCacheManager<ISqlEnvs, Map<String, SqlSessionFactory>> getCacheManager() {
        return this.cacheManager;
    }

    public Map<String, SqlSessionFactory> createCacheObject(ISqlEnvs key) {
        return new HashMap<>();
    }

    public void addObject(ISqlEnvs sqlEnvs, String openEnv, SqlSessionFactory factory) {
        Map<String, SqlSessionFactory> map = (Map)this.getObject(sqlEnvs);
        map.put(openEnv, factory);
    }

    public SqlSessionFactory getDefault(ISqlEnvs sqlEnvs) {
        return this.getSpecial(sqlEnvs, "env_default");
    }

    public SqlSessionFactory getSpecial(ISqlEnvs sqlEnvs, String dbEnv) {
        return (SqlSessionFactory)((Map)this.getObject(sqlEnvs)).get(dbEnv);
    }

    private static class InstHolder {
        private static SqlEnvCache inst = new SqlEnvCache();

        private InstHolder() {
        }
    }
}