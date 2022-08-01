package io.github.liuruinian.im.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     用户ID为空异常
 * </p>
 */
public class UserIdEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "用户ID为空异常!";
        } else {
            DEFAULT_MSG = "UserID is empty exception!";
        }
    }

    public UserIdEmptyException() {
        super(DEFAULT_MSG);
    }
}
