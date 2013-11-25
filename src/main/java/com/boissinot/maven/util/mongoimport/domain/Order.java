package com.boissinot.maven.util.mongoimport.domain;

import java.lang.annotation.*;

/**
 * @author Gregory Boissinot
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Order {

    int value();
}
