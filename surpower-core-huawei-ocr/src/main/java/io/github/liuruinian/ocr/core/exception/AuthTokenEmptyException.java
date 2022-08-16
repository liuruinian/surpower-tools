package io.github.liuruinian.ocr.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-08-16
 * <p>
 *     auth token为空
 * </p>
 */
public class AuthTokenEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "auth token为空!";
        } else {
            DEFAULT_MSG = "auth token is null!";
        }
    }

    public AuthTokenEmptyException() {
        super(DEFAULT_MSG);
    }
}
