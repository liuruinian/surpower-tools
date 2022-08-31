package io.github.liuruinian.pp.exception;

import java.util.Locale;

/**
 * @author liuruinian
 * @version 2021-11-05
 * AXN中的A号码
 */
public class PhoneNoAIsNullException extends RuntimeException {

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            DEFAULT_MSG = "AXN中的A号码不能为空!";
        } else {
            DEFAULT_MSG = "Phone number A not allow empty in AXN mode!";
        }
    }

    public PhoneNoAIsNullException() {
        super(DEFAULT_MSG);
    }
}
