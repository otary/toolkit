## cache

### EhCacheUtils

EhCache2.x工具类

``` java

List<Cache> caches = EhCacheUtils.getCaches();


// 添加Cache和添加元素
//EhCacheUtils.addCache("test");
EhCacheUtils.addElement("userCache", "key#0", "hello world");

for (Cache cache : caches) {
    List keys = EhCacheUtils.getKeys(cache.getName());
}

// 获取所有元素
List<Element> userCacheElements = EhCacheUtils.getElements("userCache");  // => 

Object value = EhCacheUtils.getValue("userCache", "key#0");  // => hello world

```

---
