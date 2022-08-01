package io.github.liuruinian.im.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     用户签名有效期非法
 * </p>
 */
public class InvalidUserSignExpireException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "用户签名有效期非法!";
        } else {
            DEFAULT_MSG = "User sign expire time invalid!";
        }
    }

    public InvalidUserSignExpireException() {
        super(DEFAULT_MSG);
    }
}
