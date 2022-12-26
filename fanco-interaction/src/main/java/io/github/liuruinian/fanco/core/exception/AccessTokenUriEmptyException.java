package io.github.liuruinian.fanco.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-12-26
 */
public class AccessTokenUriEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "access token 请求地址为空!";
        } else {
            DEFAULT_MSG = "access token uri is empty!";
        }
    }

    public AccessTokenUriEmptyException() {
        super(DEFAULT_MSG);
    }
}
