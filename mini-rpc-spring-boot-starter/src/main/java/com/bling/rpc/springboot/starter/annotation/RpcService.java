package com.bling.rpc.springboot.starter.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    /**
     * 用户定义类
     */
    Class<?> interfaceName() default void.class;
    /**
     * 版本
     */
    String serviceVersion() default "1.0";
    /**
     * 定义服务名
     */
    String serverName() default "";
}
