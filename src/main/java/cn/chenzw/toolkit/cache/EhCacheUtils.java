package cn.chenzw.toolkit.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * EhCache2工具类
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
                    cacheManager = CacheManager.create();
                }
            }
        }
        return cacheManager;
    }

    /**
     * 获取Cache对象
     * @param cacheName 缓存名称
     * @return
     */
    public static Cache getCache(String cacheName) {
        CacheManager cacheManager = getCacheManager();
        return cacheManager.getCache(cacheName);
    }

    /**
     * 获取所有Cache对象
     * @return 缓存列表
     */
    public static List<Cache> getCaches() {
        List<Cache> caches = new ArrayList<>();
        CacheManager cacheManager = getCacheManager();
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            caches.add(cacheManager.getCache(cacheName));
        }
        return caches;
    }

    /**
     * 获取所有Key
     * @param cacheName 缓存名称
     * @return key列表
     */
    public static List getKeys(String cacheName) {
        return getCache(cacheName).getKeys();
    }

    /**
     * 获取key对应的缓存值
     * @param cacheName
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getValue(String cacheName, Object key) {
        Element element = getCache(cacheName).get(key);
        return element != null ? (T) element.getObjectValue() : null;
    }

    /**
     *
     * @param cacheName
     * @return
     */
    public static List<Element> getElements(String cacheName) {
        List<Element> elements = new ArrayList<>();
        Cache cache = getCache(cacheName);
        List keys = cache.getKeys();
        for (Object key : keys) {
            elements.add(cache.get(key));
        }
        return elements;
    }

    /**
     * 获取所有的Element元素
     *
     * @return
     */
    public static List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        String[] cacheNames = getCacheManager().getCacheNames();
        for (String cacheName : cacheNames) {
            elements.addAll(getElements(cacheName));
        }
        return elements;
    }

    /**
     * 添加Cache
     * @param cacheName
     */
    public static void addCache(String cacheName) {
        CacheManager cacheManager = getCacheManager();
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCacheIfAbsent(cacheName);
        }
    }

    /**
     * 添加元素
     * @param cacheName
     * @param key
     * @param value
     */
    public static void addElement(String cacheName, Object key, Object value) {
        getCache(cacheName).put(new Element(key, value));
    }

    /**
     * 删除key
     * @param cacheName
     * @param key
     */
    public static void removeKey(String cacheName, Object key) {
        getCache(cacheName).remove(key);
    }


}
