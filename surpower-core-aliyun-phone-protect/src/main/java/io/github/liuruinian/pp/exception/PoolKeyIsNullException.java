package io.github.liuruinian.pp.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * 号码池KEY为空异常
 */
public class PoolKeyIsNullException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            DEFAULT_MSG = "号码池不能为空!";
        } else {
            DEFAULT_MSG = "pool key not allow empty!";
        }
    }

    public PoolKeyIsNullException() {
        super(DEFAULT_MSG);
    }
}
