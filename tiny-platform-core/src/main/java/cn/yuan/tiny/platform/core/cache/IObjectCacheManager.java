package cn.yuan.tiny.platform.core.cache;

import java.util.Collection;
import java.util.Set;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:14
 */
public interface IObjectCacheManager<K, V> {
    boolean containsKey(K var1);

    boolean addObject(K var1, V var2);

    boolean addObjectIfAbsent(K var1, V var2);

    V removeObject(K var1);

    V removeObject(K var1, boolean var2);

    V reload(K var1);

    boolean isReloadIfAbsent();

    V getObject(K var1);

    V getObject(K var1, boolean var2);

    Collection<V> getObjects();

    Set<K> keySet();

    void clear();

    void release();
}
