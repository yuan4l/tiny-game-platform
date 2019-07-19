package cn.yuan.tiny.platform.core.cache;

import java.util.Collection;
import java.util.Set;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:12
 */
public abstract class AbstractObjectCache<K, V> implements IObjectCacheInitializer<K, V>, IObjectCacheManager<K, V> {
    public AbstractObjectCache() {
    }

    public boolean containsKey(K key) {
        return this.getCacheManager().containsKey(key);
    }

    public boolean addObject(K key, V value) {
        return this.getCacheManager().addObject(key, value);
    }

    public boolean addObjectIfAbsent(K key, V value) {
        return this.getCacheManager().addObjectIfAbsent(key, value);
    }

    public V removeObject(K key) {
        return this.getCacheManager().removeObject(key);
    }

    public V removeObject(K key, boolean isRelease) {
        return this.getCacheManager().removeObject(key, isRelease);
    }

    public V reload(K key) {
        return this.getCacheManager().reload(key);
    }

    public boolean isReloadIfAbsent() {
        return this.getCacheManager().isReloadIfAbsent();
    }

    public V getObject(K key) {
        return this.getCacheManager().getObject(key);
    }

    public V getObject(K key, boolean isReloadIfAbsent) {
        return this.getCacheManager().getObject(key, isReloadIfAbsent);
    }

    public Collection<V> getObjects() {
        return this.getCacheManager().getObjects();
    }

    public Set<K> keySet() {
        return this.getCacheManager().keySet();
    }

    public void clear() {
        this.getCacheManager().clear();
    }

    public V createCacheObject(K key) {
        return null;
    }

    protected void releaseCacheObject(V value) {
    }

    public void release() {
        this.getCacheManager().release();
    }
}
