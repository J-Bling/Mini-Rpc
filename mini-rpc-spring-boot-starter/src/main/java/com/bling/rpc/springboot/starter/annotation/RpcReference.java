package com.bling.rpc.springboot.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcReference {
    /**
     * 指定接口
     */
    Class<?> interfaceClass() default void.class;
    /**
     * 指定address
     */
    String address() default "";
    /**
     * 指定服务名称
     */
    String serviceName() default "";
}
