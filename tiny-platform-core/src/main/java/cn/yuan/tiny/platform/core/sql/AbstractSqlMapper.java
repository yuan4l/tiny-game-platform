package cn.yuan.tiny.platform.core.sql;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001914:53
 */
public abstract class AbstractSqlMapper {
    private Map<String, String> keyCache = new HashMap<>();
    private String namespace;

    public AbstractSqlMapper() {
        this.namespace = this.getClass().getName();
    }

    public AbstractSqlMapper(String namespace) {
        this.namespace = namespace;
    }

    protected abstract SqlSession sqlSession();

    protected int insert(String key, Object param) {
        SqlSession session = this.sqlSession();

        int var5;
        try {
            int value = session.insert(this.getKey(key), param);
            session.commit();
            var5 = value;
        } finally {
            session.close();
        }

        return var5;
    }

    protected <T> T selectOne(String key, Class<T> valueClazz) {
        SqlSession session = this.sqlSession();

        T var4;
        try {
            var4 = session.selectOne(this.getKey(key));
        } finally {
            session.close();
        }

        return var4;
    }

    protected <T> T selectOne(String key, Class<T> valueClazz, Object param) {
        SqlSession session = this.sqlSession();

        T var5;
        try {
            var5 = session.selectOne(this.getKey(key), param);
        } finally {
            session.close();
        }

        return var5;
    }

    protected <T> List<T> selectList(String key, Class<T> valueClazz) {
        SqlSession session = this.sqlSession();

        List<T> var4;
        try {
            var4 = session.selectList(this.getKey(key));
        } finally {
            session.close();
        }

        return var4;
    }

    protected <T> List<T> selectList(String key, Class<T> valueClazz, Object param) {
        SqlSession session = this.sqlSession();

        List<T> var5;
        try {
            var5 = session.selectList(this.getKey(key), param);
        } finally {
            session.close();
        }

        return var5;
    }

    protected int update(String key) {
        SqlSession session = this.sqlSession();

        int var4;
        try {
            int value = session.update(this.getKey(key));
            session.commit();
            var4 = value;
        } finally {
            session.close();
        }

        return var4;
    }

    protected int update(String key, Object param) {
        SqlSession session = this.sqlSession();

        int var5;
        try {
            int value = session.update(this.getKey(key), param);
            session.commit();
            var5 = value;
        } finally {
            session.close();
        }

        return var5;
    }

    protected int delete(String key) {
        SqlSession session = this.sqlSession();

        int var4;
        try {
            int value = session.delete(this.getKey(key));
            session.commit();
            var4 = value;
        } finally {
            session.close();
        }

        return var4;
    }

    protected int delete(String key, Object param) {
        SqlSession session = this.sqlSession();

        int var5;
        try {
            int value = session.delete(this.getKey(key), param);
            session.commit();
            var5 = value;
        } finally {
            session.close();
        }

        return var5;
    }

    private String getKey(String key) {
        String value = null;
        if (!this.keyCache.containsKey(key)) {
            value = this.namespace + "." + key;
            this.keyCache.put(key, value);
        } else {
            value = (String)this.keyCache.get(key);
        }

        return value;
    }
}
