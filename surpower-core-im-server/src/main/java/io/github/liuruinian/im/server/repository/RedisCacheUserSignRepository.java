package io.github.liuruinian.im.server.repository;

import io.github.liuruinian.im.core.repository.UserSignRepository;
import io.github.liuruinian.im.core.util.StringUtils;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import io.github.liuruinian.im.server.properties.RedisUserSignProperties;
import io.github.liuruinian.im.server.properties.UserSignProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     Redis缓存UserSign
 * </p>
 */
@Slf4j
@Repository
public class RedisCacheUserSignRepository implements UserSignRepository {

    private RedisUserSignProperties         redisUserSignProperties;

    private UserSignProperties              userSignProperties;

    private ImServerProperties              imServerProperties;

    private RedisTemplate<String, String>   redisTemplate;

    @Autowired
    public void setRedisUserSignProperties(RedisUserSignProperties redisUserSignProperties) {
        this.redisUserSignProperties = redisUserSignProperties;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserSignProperties(UserSignProperties userSignProperties) {
        this.userSignProperties = userSignProperties;
    }

    @Autowired
    public void setImServerProperties(ImServerProperties imServerProperties) {
        this.imServerProperties = imServerProperties;
    }

    @Override
    public String findOne(String uniqueKey) {

        String data = redisTemplate.opsForValue().get(uniqueKey);

        if (StringUtils.isBlank(data)) {
            log.debug("[RedisCacheUserSignRepository] -> cache user-sign is empty for key : {}", uniqueKey);
            return null;
        }
        return data;
    }

    @Override
    public void save(String userSign, String userId) {
        long expire = userSignProperties.getExpire();

        String key = key(userId);

        redisTemplate.opsForValue().set(key, userSign, expire, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String uniqueKey) {
        redisTemplate.delete(uniqueKey);
    }

    private String key(String userId) {
        String keyPrefix = StringUtils.trimToNull(redisUserSignProperties.getKeyPrefix());

        StringBuilder keyBuilder = new StringBuilder();

        if (keyPrefix != null) {
            keyBuilder.append(keyPrefix);
        }

        keyBuilder.append(userId);

        return keyBuilder.toString();
    }
}
