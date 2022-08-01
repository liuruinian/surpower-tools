package io.github.liuruinian.im.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     SecretKey为空异常
 * </p>
 */
public class SecretKeyEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "应用密钥为空异常!";
        } else {
            DEFAULT_MSG = "SecretKey is empty exception!";
        }
    }

    public SecretKeyEmptyException() {
        super(DEFAULT_MSG);
    }
}
