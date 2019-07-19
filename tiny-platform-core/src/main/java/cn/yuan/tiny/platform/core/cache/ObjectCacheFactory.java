package cn.yuan.tiny.platform.core.cache;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:20
 */
public class ObjectCacheFactory {
    public ObjectCacheFactory() {
    }

    public static <P, V> IObjectCacheManager<P, V> createUnlimitedMapCacheManager(IObjectCacheInitializer<P, V> cache, boolean reloadIfAbsent) {
        return new UnLimitMapCacheManager<>(cache, reloadIfAbsent);
    }
}
