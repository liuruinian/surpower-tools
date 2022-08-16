package io.github.liuruinian.ocr.server.repository;

import io.github.liuruinian.ocr.core.authtoken.AuthTokenRepository;
import io.github.liuruinian.ocr.core.util.StringUtils;
import io.github.liuruinian.ocr.server.properties.HuaweiOcrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisCacheAuthTokenRepository implements AuthTokenRepository {

    private RedisTemplate<String, String>   redisTemplate;

    private HuaweiOcrProperties             properties;

    @Autowired
    public void setProperties(HuaweiOcrProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String findOne(String key) {
        String data = redisTemplate.opsForValue().get(key(key));

        if (StringUtils.isBlank(data)) {
            log.debug("[RedisCacheAuthTokenRepository::findOne] -> cache x-auth-token is empty for key : {}", key(key));
            return "";
        }

        return data;
    }

    @Override
    public void save(String authToken, String key) {
        long expire = properties.getAuthTokenExpire();

        String k = key(key);

        redisTemplate.opsForValue().set(k, authToken, expire, TimeUnit.HOURS);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    private String key(String key) {
        String keyPrefix = StringUtils.trimToNull(properties.getAuthTokenKeyPrefix());

        StringBuilder keyBuilder = new StringBuilder();

        if (keyPrefix != null) {
            keyBuilder.append(keyPrefix);
        }

        keyBuilder.append(key);

        return keyBuilder.toString();
    }
}
