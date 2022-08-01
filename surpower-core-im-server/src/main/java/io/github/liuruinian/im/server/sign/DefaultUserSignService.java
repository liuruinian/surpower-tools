package io.github.liuruinian.im.server.sign;

import io.github.liuruinian.im.core.repository.UserSignRepository;
import io.github.liuruinian.im.core.sign.AbstractUserSignService;
import io.github.liuruinian.im.core.util.StringUtils;
import io.github.liuruinian.im.server.properties.ImServerProperties;
import io.github.liuruinian.im.server.properties.RedisUserSignProperties;
import io.github.liuruinian.im.server.properties.UserSignProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserSignService extends AbstractUserSignService {

    private UserSignRepository              userSignRepository;

    private ImServerProperties              imServerProperties;

    private RedisUserSignProperties         redisUserSignProperties;

    private UserSignProperties              userSignProperties;

    private RedisTemplate<String, String>   redisTemplate;

    @Autowired
    public void setUserSignRepository(UserSignRepository userSignRepository) {
        this.userSignRepository = userSignRepository;
    }

    @Autowired
    public void setImServerProperties(ImServerProperties imServerProperties) {
        this.imServerProperties = imServerProperties;
    }

    @Autowired
    public void setRedisUserSignProperties(RedisUserSignProperties redisUserSignProperties) {
        this.redisUserSignProperties = redisUserSignProperties;
    }

    @Autowired
    public void setUserSignProperties(UserSignProperties userSignProperties) {
        this.userSignProperties = userSignProperties;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void userSignRepository(String userSign, String userId) {
        userSignRepository.save(userSign, userId);
    }

    @Override
    public String obtainUserSign(String userId) {
        Long sdkAppId = imServerProperties.getSdkAppId();
        String secretKey = imServerProperties.getSecretKey();
        long expire = userSignProperties.getExpire();

        String key = key(userId);

        String cacheUserSign = redisTemplate.opsForValue().get(key);

        if (StringUtils.isNotBlank(cacheUserSign)) {
            return cacheUserSign;
        } else {
            return generateUserSign(sdkAppId, secretKey, userId, expire);
        }
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
