package cn.chenzw.toolkit.cache;

import cn.chenzw.toolkit.spring.config.AppConfig;
import cn.chenzw.toolkit.spring.config.CacheConfig;
import net.sf.ehcache.Cache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, CacheConfig.class})
public class EhcacheUtilsTests {

    @Test
    public void test() {
        List<Cache> caches = EhCacheUtils.getCaches();
        Assert.assertTrue(!caches.isEmpty());

        // 添加Cache和添加元素
        //EhCacheUtils.addCache("test");
        EhCacheUtils.addElement("userCache", "key#0", "hello world");

        for (Cache cache : caches) {
            List keys = EhCacheUtils.getKeys(cache.getName());

            for (Object key : keys) {
                System.out.println(key);
            }


            System.out.println(cache);

        }
    }

}
