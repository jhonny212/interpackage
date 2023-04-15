package com.interpackage.users.redisConfig;

import java.time.Duration;

import com.interpackage.users.auth.model.TokenCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    // @Value("${spring.redis.host}")
    // private String redisHost;

    // @Value("${spring.redis.port}")
    // private int redisPort;

    // @Value("${spring.redis.password}")
    // private String redisPassword;

    // @Bean
    // public JedisConnectionFactory jedisConnectionFactory() {
    //     RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
    //     //redisStandaloneConfiguration.setPassword(redisPassword);
    //     JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
    //     jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
    //     return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    // }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate() {
    //     RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //     redisTemplate.setConnectionFactory(jedisConnectionFactory());
    //     return redisTemplate;
    // }

    //Other way
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        //redisStandaloneConfiguration.setPassword(redisPassword);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        //jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, TokenCache> redisTemplate() {
        final RedisTemplate<String, TokenCache> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}