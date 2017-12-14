/*
 * 文 件 名:  ShiroRedisCacheManager.java
 * 版   权:  xwtec
 * 描     述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2017年10月26日
 * 修改内容:  <修改内容>
 */
package com.yll.springboot.demo.common;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年10月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, redisTemplate);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}