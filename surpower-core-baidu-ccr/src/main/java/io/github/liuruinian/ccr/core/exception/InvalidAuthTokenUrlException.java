package io.github.liuruinian.ccr.core.exception;

import java.util.Locale;

/**
 * 非法AuthToken请求地址
 *
 * @author liuruinian
 * @version 2022-12-08
 */
public class InvalidAuthTokenUrlException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "非法AccessToken请求地址!";
        } else {
            DEFAULT_MSG = "invalid access token url!";
        }
    }

    public InvalidAuthTokenUrlException() {
        super(DEFAULT_MSG);
    }
}
