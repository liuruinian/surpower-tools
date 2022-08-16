package io.github.liuruinian.ocr.core.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2022-08-16
 * <p>
 *     x-subject-token为空
 * </p>
 */
public class XSubjectTokenEmptyException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "x-subject-token为空!";
        } else {
            DEFAULT_MSG = "x-subject-token is empty!";
        }
    }

    public XSubjectTokenEmptyException() {
        super(DEFAULT_MSG);
    }
}
