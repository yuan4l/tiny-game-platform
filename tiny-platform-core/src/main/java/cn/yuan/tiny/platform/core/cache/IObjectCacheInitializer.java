package cn.yuan.tiny.platform.core.cache;

/**
 * Description：
 *
 * @author yuan 2019\7\19 001916:13
 */
public interface IObjectCacheInitializer<K, V> {
    IObjectCacheManager<K, V> getCacheManager();

    V createCacheObject(K var1);
}
