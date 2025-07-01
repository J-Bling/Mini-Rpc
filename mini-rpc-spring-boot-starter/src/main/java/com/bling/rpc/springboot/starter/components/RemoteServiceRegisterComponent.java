package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.bling.rpc.register.ServiceRegister;
import com.bling.rpc.service.HttpServer;
import com.bling.rpc.service.VertxHttpServer;
import com.bling.rpc.springboot.starter.annotation.RpcService;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class RemoteServiceRegisterComponent implements BeanPostProcessor{
    @Autowired(required = false)
    private NacosRegisterComponent nacosRegisterComponent;
    private final MiniServerProperties serverProperties;

    public RemoteServiceRegisterComponent(MiniServerProperties serverProperties){
        this.serverProperties = serverProperties;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
        if (!serverProperties.getEnable()){
            //不使用 服务
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean,beanName);
        }
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        if (rpcService==null){
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean,beanName);
        }
        System.out.println("注册服务 "+beanName);
        Class<?> interfaceClass = rpcService.interfaceName();
        if (interfaceClass==void.class){
            Class<?>[] interfacesClass = beanClass.getInterfaces();
            interfaceClass = interfacesClass.length == 0 ? beanClass : interfacesClass[0];
//            interfaceClass = beanClass;
        }
        String serviceName = rpcService.serverName();
        if (serviceName.isEmpty()){
            serviceName = interfaceClass.getName();
        }
        if (nacosRegisterComponent!=null){
            //在nacos注册服务
            try {
                nacosRegisterComponent.register(serviceName);
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        }
        //本地注册
        ServiceRegister.register(serviceName,bean);

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean,beanName);
    }

    public void init(){
        if (!serverProperties.getEnable()){
            return;
        }
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(serverProperties.getPort());
    }
}
