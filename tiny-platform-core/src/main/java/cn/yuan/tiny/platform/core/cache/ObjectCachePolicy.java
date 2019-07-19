package cn.yuan.tiny.platform.core.cache;

/**
 * Description：缓存类型枚举
 *
 * @author yuan 2019\7\19 001916:21
 */
public enum ObjectCachePolicy {
    UNLIMITED,
    FIFO,
    LRU,
    WEAKREFERENCE;

    private ObjectCachePolicy() {
    }
}
