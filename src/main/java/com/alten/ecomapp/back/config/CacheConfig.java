package com.alten.ecomapp.back.config;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder().build(true);
    }
}
