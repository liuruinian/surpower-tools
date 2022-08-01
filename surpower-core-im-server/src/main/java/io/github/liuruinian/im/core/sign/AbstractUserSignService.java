package io.github.liuruinian.im.core.sign;


import com.tencentyun.TLSSigAPIv2;
import io.github.liuruinian.im.core.util.StringUtils;
import io.github.liuruinian.im.core.exception.InvalidUserSignExpireException;
import io.github.liuruinian.im.core.exception.SDKAppIdIsNullException;
import io.github.liuruinian.im.core.exception.SecretKeyEmptyException;
import io.github.liuruinian.im.core.exception.UserIdEmptyException;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     DefaultUserSignService
 * </p>
 */
public abstract class AbstractUserSignService implements UserSignService {

    @Override
    public String generateUserSign(Long sdkAppId, String secretKey, String userId, long expire) {
        checkPreConditions(sdkAppId, secretKey, userId, expire);

        TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppId, secretKey);
        String userSign = api.genUserSig(userId, expire);

        userSignRepository(userSign, userId);

        return userSign;
    }

    protected abstract void userSignRepository(String userSign, String userId);

    private void checkPreConditions(Long sdkAppId, String secretKey, String userId, long expire) {
        if (sdkAppId == null) {
            throw new SDKAppIdIsNullException();
        }
        if (StringUtils.isBlank(secretKey)) {
            throw new SecretKeyEmptyException();
        }
        if (StringUtils.isBlank(userId)) {
            throw new UserIdEmptyException();
        }
        if (expire <= 0) {
            throw new InvalidUserSignExpireException();
        }
    }
}
