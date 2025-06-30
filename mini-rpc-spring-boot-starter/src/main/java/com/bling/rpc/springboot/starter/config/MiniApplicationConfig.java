package com.bling.rpc.springboot.starter.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.bling.rpc.springboot.starter.components.NacosRegisterComponent;
import com.bling.rpc.springboot.starter.components.RemoteServiceRegisterComponent;
import com.bling.rpc.springboot.starter.components.ServerDiscovery;
import com.bling.rpc.springboot.starter.components.SetReferenceProxy;
import com.bling.rpc.springboot.starter.properties.MiniConsumerProperties;
import com.bling.rpc.springboot.starter.properties.MiniNacosProperties;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({MiniServerProperties.class, MiniNacosProperties.class, MiniConsumerProperties.class})
public class MiniApplicationConfig {

    @Autowired
    private MiniNacosProperties nacosProperties;

    @Autowired
    private MiniServerProperties serverProperties;

    @Autowired
    private MiniConsumerProperties consumerProperties;

    @Bean
    public NamingService namingService() throws NacosException {
        if (nacosProperties.getEnable() && nacosProperties.getAddress()!=null){
            return NamingFactory.createNamingService(nacosProperties.getAddress());
        }
        return null;
    }


    @Bean
    public NacosRegisterComponent nacosRegisterComponent() throws NacosException {
        return new NacosRegisterComponent(serverProperties);
    }

    @Bean
    public ServerDiscovery serverDiscovery() throws NacosException {
        return new ServerDiscovery(serverProperties);
    }

    @Bean
    public RemoteServiceRegisterComponent remoteServiceRegisterComponent() throws NacosException {
        RemoteServiceRegisterComponent component= new RemoteServiceRegisterComponent(serverProperties);
        component.init();
        return component;
    }

    @Bean
    public SetReferenceProxy setReferenceProxy() throws NacosException {
        return new SetReferenceProxy();
    }

}