package io.github.liuruinian.ocr.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-08-16
 * <p>
 *     非法AuthToken请求地址
 * </p>
 */
public class InvalidAuthTokenUrlException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "非法AuthToken请求地址!";
        } else {
            DEFAULT_MSG = "invalid auth token url!";
        }
    }

    public InvalidAuthTokenUrlException() {
        super(DEFAULT_MSG);
    }
}
