package io.github.liuruinian.im.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-02-03
 * <p>
 *     SDKAppId为空异常
 * </p>
 */
public class SDKAppIdIsNullException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "应用SDKAppID为空异常!";
        } else {
            DEFAULT_MSG = "SDKAppID is null exception!";
        }
    }

    public SDKAppIdIsNullException() {
        super(DEFAULT_MSG);
    }
}
