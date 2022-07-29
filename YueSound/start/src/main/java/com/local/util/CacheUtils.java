package com.local.util;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hhs
 * @create 2022-07-01 17:12
 */
@Configuration
public class CacheUtils {
    @Bean
    public CacheManager cacheManager(){
        EhCacheCacheManager ehCacheCacheManager=new EhCacheCacheManager();
        return ehCacheCacheManager;
    }
}
