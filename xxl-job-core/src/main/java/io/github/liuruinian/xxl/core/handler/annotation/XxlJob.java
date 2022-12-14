package io.github.liuruinian.xxl.core.handler.annotation;

import java.lang.annotation.*;

/**
 * annotation for method jobhandler
 *
 * @author xuxueli 2019-12-11 20:50:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlJob {

    /**
     * jobhandler name
     * @return jobhandler name
     */
    String value();

    /**
     * init handler, invoked when JobThread init
     * @return init handler
     */
    String init() default "";

    /**
     * destroy handler, invoked when JobThread destroy
     * @return destroy handler
     */
    String destroy() default "";

}
