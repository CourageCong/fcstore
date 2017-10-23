package com.fc.fcstroe.dagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义专属于fragemnt级别的scope(作用域)
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
