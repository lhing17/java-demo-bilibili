package com.example.demo.model;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
public @interface Excel {

    String name() default "";

    String dictValue() default "";
}
