package io.github.liuruinian.pp.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * <p>
 *     绑定关系的过期时间为空异常
 * </p>
 */
public class BindExpirationIsNullException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            DEFAULT_MSG = "绑定关系的过期时间不能为空!";
        } else {
            DEFAULT_MSG = "Bind expiration time not allow empty!";
        }
    }

    public BindExpirationIsNullException() {
        super(DEFAULT_MSG);
    }
}
