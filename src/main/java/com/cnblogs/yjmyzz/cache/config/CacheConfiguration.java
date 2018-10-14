package com.cnblogs.yjmyzz.cache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * @author jimmy
 */
@Configuration
@EnableCaching
@ComponentScan("com.cnblogs.yjmyzz")
public class CacheConfiguration {

    public static final String CACHE_10S = "CACHE_10S";
    public static final String CACHE_30S = "CACHE_30S";

    /**
     * 默认最多缓存条数
     */
    private static final int DEFAULT_MAXSIZE = 50000;

    /**
     * 默认过期（10秒）
     */
    private static final int DEFAULT_TTL = 15;

    /**
     * 缓存的容器名称
     */
    public enum CacheNames {
        /**
         * 10秒缓存
         */
        CACHE_10S(10),
        /**
         * 30秒缓存
         */
        CACHE_30S(30);

        CacheNames() {
        }

        CacheNames(int ttl) {
            this.ttl = ttl;
        }

        CacheNames(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        private int maxSize = DEFAULT_MAXSIZE;
        private int ttl = DEFAULT_TTL;

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }
    }

    @Bean("myCacheManager")
    public CacheManager guavaCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<GuavaCache> caches = new ArrayList<GuavaCache>();
        for (CacheNames c : CacheNames.values()) {
            caches.add(new GuavaCache(c.name(), CacheBuilder.newBuilder().recordStats()
                    .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS).maximumSize(c.getMaxSize()).build()));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
