package com.interpackage.users.redisConfig.repository;

import com.interpackage.users.auth.model.TokenCache;

import java.util.Map;

public interface RedisRepository {
    Map<String, TokenCache> findAll();
    TokenCache findById(String id);
    boolean exist(String id);
    void save(String token, TokenCache tokenCache);
    void delete(String id);
    void updateExpiration(String id);
}
