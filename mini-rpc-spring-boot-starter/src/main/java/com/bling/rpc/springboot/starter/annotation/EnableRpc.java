package com.bling.rpc.springboot.starter.annotation;

import com.bling.rpc.springboot.starter.config.MiniApplicationConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({MiniApplicationConfig.class})
public @interface EnableRpc {

}