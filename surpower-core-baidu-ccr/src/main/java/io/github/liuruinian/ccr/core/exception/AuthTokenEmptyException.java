package io.github.liuruinian.ccr.core.exception;

import java.util.Locale;

/**
 * access token empty exception
 *
 * @author liuruinian
 * @version 2022-12-08
 */
public class AuthTokenEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "access token为空!";
        } else {
            DEFAULT_MSG = "access token is null!";
        }
    }

    public AuthTokenEmptyException() {
        super(DEFAULT_MSG);
    }
}
