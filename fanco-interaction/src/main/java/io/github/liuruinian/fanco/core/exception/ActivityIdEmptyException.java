package io.github.liuruinian.fanco.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-12-26
 */
public class ActivityIdEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "活动ID为空!";
        } else {
            DEFAULT_MSG = "activity id is empty!";
        }
    }

    public ActivityIdEmptyException() {
        super(DEFAULT_MSG);
    }
}
