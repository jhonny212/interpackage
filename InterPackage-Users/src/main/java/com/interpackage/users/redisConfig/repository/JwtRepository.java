package com.interpackage.users.redisConfig.repository;

import java.util.Map;

import com.interpackage.users.auth.model.TokenCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class JwtRepository implements RedisRepository{

    private static final String KEY = "TokenCache";
    private final long expiration = 10000;
    private RedisTemplate<String, TokenCache> redisTemplate;
    private HashOperations hashOperations;

    public JwtRepository(RedisTemplate<String, TokenCache> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, TokenCache> findAll() {
        // TODO Auto-generated method stub
        return hashOperations.entries(KEY);
    }

    @Override
    public TokenCache findById(String id) {
        // TODO Auto-generated method stub
        return (TokenCache) hashOperations.get(KEY, id);
    }

    @Override
    public boolean exist(String id) {
        return  hashOperations.hasKey(KEY,id);
    }

    @Override
    public void save(String token, TokenCache tokenCache) {
        hashOperations.put(KEY, token,tokenCache);
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void updateExpiration(String id) {
        TokenCache res = this.findById(id);
        res.setExpirationTime(res.getExpirationTime() + expiration);
        this.save(id,res);
    }

}
