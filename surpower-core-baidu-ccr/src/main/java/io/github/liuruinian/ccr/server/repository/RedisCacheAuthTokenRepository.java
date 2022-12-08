package io.github.liuruinian.ccr.server.repository;

import io.github.liuruinian.ccr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ccr.server.properties.BaiduCcrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class RedisCacheAuthTokenRepository implements AuthTokenRepository {

    private RedisTemplate<String, String> redisTemplate;

    private BaiduCcrProperties properties;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setProperties(BaiduCcrProperties properties) {
        this.properties = properties;
    }

    @Override
    public String findOne(String key) {
        String token = redisTemplate.opsForValue().get(key(key));

        if (!StringUtils.hasLength(token)) {
            log.debug("[RedisCacheAuthTokenRepository::findOne] -> cache access_token is empty for key : {}", key(key));
            return "";
        }

        return token;
    }

    @Override
    public void save(String accessToken, String key) {
        long expire = properties.getTokenExpire();

        String k = key(key);

        redisTemplate.opsForValue().set(k, accessToken, expire, TimeUnit.DAYS);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    private String key(String key) {
        String keyPrefix = StringUtils.trimAllWhitespace(properties.getTokenKeyPrefix());

        StringBuilder keyBuilder = new StringBuilder();

        if (keyPrefix != null) {
            keyBuilder.append(keyPrefix);
        }

        keyBuilder.append(key);

        return keyBuilder.toString();
    }
}
