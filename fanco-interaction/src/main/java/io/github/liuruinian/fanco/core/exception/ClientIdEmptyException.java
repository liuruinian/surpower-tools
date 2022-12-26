package io.github.liuruinian.fanco.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-12-26
 */
public class ClientIdEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "客户端ID为空!";
        } else {
            DEFAULT_MSG = "client id is empty!";
        }
    }

    public ClientIdEmptyException() {
        super(DEFAULT_MSG);
    }
}
