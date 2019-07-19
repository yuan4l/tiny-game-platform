package cn.yuan.tiny.platform.core.cache;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:20
 */
public class UnLimitMapCacheManager<K, V> extends AbstractObjectCacheManager<K, V> {
    protected ConcurrentHashMap<K, V> objectMap;

    public UnLimitMapCacheManager() {
        this(null, false);
    }

    public UnLimitMapCacheManager(IObjectCacheInitializer<K, V> cache, boolean reloadIfAbsent) {
        super(cache, ObjectCachePolicy.UNLIMITED, reloadIfAbsent);
        this.objectMap = new ConcurrentHashMap<>();
    }

    public boolean containsKey(K key) {
        return this.objectMap.containsKey(key);
    }

    public boolean addObject(K key, V value) {
        this.objectMap.put(key, value);
        return true;
    }

    public boolean addObjectIfAbsent(K key, V value) {
        value = this.objectMap.putIfAbsent(key, value);
        return value != null;
    }

    public V getObject(K key) {
        return this.getObject(key, this.reloadIfAbsent);
    }

    public V getObject(K key, boolean isReloadIfAbsent) {
        V value = this.objectMap.get(key);
        if (value == null && isReloadIfAbsent) {
            value = this.reload(key);
        }

        return value;
    }

    public V removeObject(K key) {
        return this.removeObject(key, true);
    }

    public V removeObject(K key, boolean isRelease) {
        V value = this.objectMap.remove(key);
        if (value != null && this.cache != null && isRelease) {
            this.cache.releaseCacheObject(value);
        }

        return value;
    }

    public Collection<V> getObjects() {
        return this.objectMap.values();
    }

    public Set<K> keySet() {
        return this.objectMap.keySet();
    }

    public void clear() {
        Iterator<V> var1 = this.objectMap.values().iterator();

        while(var1.hasNext()) {
            V value = var1.next();
            if (this.cache != null) {
                this.cache.releaseCacheObject(value);
            }
        }

        this.objectMap.clear();
    }
}