package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.bling.rpc.interfaces.Callback;
import com.bling.rpc.proxy.ServiceProxy;
import com.bling.rpc.proxy.ServiceProxyFactory;
import com.bling.rpc.springboot.starter.annotation.RpcReference;
import com.bling.rpc.springboot.starter.properties.MiniConsumerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class SetReferenceProxy implements BeanPostProcessor {
    @Autowired(required = false)
    private ServerDiscovery serverDiscovery;

    public SetReferenceProxy(){

    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields){
            RpcReference reference = field.getAnnotation(RpcReference.class);
            if (reference==null){
                continue;
            }

            String address = reference.address();
            String serviceName = reference.serviceName();
            Class<?> interfaceClass = reference.interfaceClass();
            if (interfaceClass == void.class){
                interfaceClass = field.getType();
            }
            if (serviceName.isEmpty()){
                serviceName = interfaceClass.getName();
            }
            System.out.println("接入字段:"+field.getName());
            try {
                field.setAccessible(true);
                Object proxy = ServiceProxyFactory.getProxy(interfaceClass,address,serviceName,getCallback());
                field.set(bean,proxy);
            } catch (Exception e) {
                e.printStackTrace();
//                throw new RuntimeException(e);
            }finally {
                field.setAccessible(false);
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean,beanName);
    }


    private Callback<String> getCallback(){
        if (serverDiscovery==null){
            return null;
        }
        return s-> {
            try {
                return serverDiscovery.getRandomAddress(s);
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
