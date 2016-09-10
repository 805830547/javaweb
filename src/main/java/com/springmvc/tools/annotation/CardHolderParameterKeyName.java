package com.springmvc.tools.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface CardHolderParameterKeyName {
    public final static String USE_DEFAULT_NAME = "";

    String value() default USE_DEFAULT_NAME;
}
