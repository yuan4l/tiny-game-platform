package cn.yuan.tiny.platform.core.cache;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:21
 */
public abstract class AbstractObjectCacheManager<K, V> implements IObjectCacheManager<K, V> {
    protected AbstractObjectCache<K, V> cache;
    protected final ObjectCachePolicy cachePolicy;
    protected final boolean reloadIfAbsent;

    protected AbstractObjectCacheManager() {
        this(null, ObjectCachePolicy.UNLIMITED, false);
    }

    protected AbstractObjectCacheManager(IObjectCacheInitializer<K, V> cache, ObjectCachePolicy policy, boolean reloadIfAbsent) {
        this.cache = (AbstractObjectCache)cache;
        this.reloadIfAbsent = reloadIfAbsent;
        this.cachePolicy = policy;
    }

    public boolean isReloadIfAbsent() {
        return this.reloadIfAbsent;
    }

    public V reload(K key) {
        synchronized(key) {
            V value = this.getObject(key, false);
            if (value == null) {
                if (this.cache != null) {
                    value = this.cache.createCacheObject(key);
                }

                if (value != null && this.reloadIfAbsent) {
                    this.addObject(key, value);
                }
            }

            return value;
        }
    }

    public void release() {
        this.clear();
    }
}
