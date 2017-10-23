package com.fc.fcstroe.dagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/10/5 0005.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface PoetryQulified {
    /** The name. */
    String value() default "";
}
