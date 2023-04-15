package com.interpackage.users.redisConfig;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class JwtRedisCache {


    private RedisTemplate<String, String> redisTemplate;

    public void addJwtToCache(String jwt, long expirationTime) {
        System.out.println("AGREGANDO"+"->"+jwt);
        redisTemplate.opsForValue().set(jwt, "lastAccessed", expirationTime,
        TimeUnit.SECONDS);
    }

    public boolean isJwtInCache(String jwt) {
        System.out.println("ACAAAAAAAAAAAAAAAAAAAAAAAAAA"+jwt);
        return redisTemplate.hasKey(jwt);
    }

    public void removeJwtFromCache(String jwt) {
        redisTemplate.delete(jwt);
    }

    public void getJwt(String jwt){
        String expiration = redisTemplate.opsForValue().get(jwt);
        System.out.println(expiration+"->"+jwt);
        redisTemplate.opsForValue();
    }

    /*private final RedisTemplate<String, Object> redisTemplate;

    public JwtRedisCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addJwtToCache(String token, long expirationTimeInSeconds) {
        redisTemplate.opsForValue().set(token, "valid", expirationTimeInSeconds, TimeUnit.SECONDS);
    }

    public boolean isJwtInCache(String token) {
        return redisTemplate.hasKey(token);
    }

    public void deleteJwtFromCache(String token) {
        redisTemplate.delete(token);
    }*/
}
