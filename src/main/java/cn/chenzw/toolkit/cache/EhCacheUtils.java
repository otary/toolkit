package cn.chenzw.toolkit.cache;

import cn.chenzw.toolkit.spring.util.SpringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.core.EhcacheManager;

/**
 * EhCache工具类
 *
 * @author chenzw
 */
public class EhCacheUtils {

    private static CacheManager cacheManager;

    private EhCacheUtils() {

    }

    public static CacheManager getCacheManager() {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    cacheManager = SpringUtils.getBean(EhcacheManager.class);
                }
            }
        }
        return cacheManager;
    }

    public Cache getCache() {
        return null;
    }
}
