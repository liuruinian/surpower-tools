package io.github.liuruinian.fanco.server.repository;

import io.github.liuruinian.fanco.core.token.AuthTokenRepository;
import io.github.liuruinian.fanco.server.properties.FancoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class RedisCacheAuthTokenRepository implements AuthTokenRepository {

    private RedisTemplate<String, String>   redisTemplate;

    private FancoProperties                 properties;

    @Autowired
    public void setProperties(FancoProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String findOne(String key) {
        String data = redisTemplate.opsForValue().get(key(key));

        if (!StringUtils.hasLength(data)) {
            log.debug("[RedisCacheAuthTokenRepository::findOne] -> cache access token is empty for key : {}", key(key));
            return "";
        }

        return data;
    }

    @Override
    public void save(String token, String key) {
        long expire = properties.getAuthTokenExpire();

        String k = key(key);

        redisTemplate.opsForValue().set(k, token, expire, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key(key));
    }

    private String key(String key) {
        String keyPrefix = StringUtils.trimAllWhitespace(properties.getAccessTokenKeyPrefix());

        StringBuilder keyBuilder = new StringBuilder();

        if (keyPrefix != null) {
            keyBuilder.append(keyPrefix);
        }

        keyBuilder.append(key);

        return keyBuilder.toString();
    }
}
