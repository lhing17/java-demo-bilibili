package com.example.demo.datasource;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface UsingDataSource {

    String value() default "";
}
