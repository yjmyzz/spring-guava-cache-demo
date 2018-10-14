package com.cnblogs.yjmyzz.service.impl;

import com.cnblogs.yjmyzz.cache.config.CacheConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cnblogs.yjmyzz.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {


    @Override
    @Cacheable(cacheManager = "myCacheManager", key = "#name", value = CacheConfiguration.CACHE_10S)
    public String guavaCache10seconds(String name) {
        return "guavaCache10seconds:" + name + ",time:" + System.currentTimeMillis();
    }


    @Override
    @Cacheable(cacheManager = "myCacheManager", key = "#name", value = CacheConfiguration.CACHE_30S)
    public String guavaCache30seconds(String name) {
        return "guavaCache30seconds:" + name + ",time:" + System.currentTimeMillis();
    }


}
