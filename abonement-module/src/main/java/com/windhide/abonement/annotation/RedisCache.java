package com.windhide.abonement.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    String operation() default "select";
    Class targetClass() default Boolean.class;

}
