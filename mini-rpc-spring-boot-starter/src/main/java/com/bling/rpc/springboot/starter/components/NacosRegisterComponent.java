package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.bling.rpc.springboot.starter.properties.MiniNacosProperties;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class NacosRegisterComponent {
    @Autowired(required = false)
    private NamingService namingService;
    private final MiniServerProperties serverProperties;


    public NacosRegisterComponent(MiniServerProperties serverProperties){
        this.serverProperties = serverProperties;
    }

    public void register(String interfacename) throws NacosException {
        if (namingService == null){
            return;
        }
        namingService.registerInstance(serverProperties.generateServerName(interfacename),serverProperties.getHost(),serverProperties.getPort());
        System.out.println("服务"+serverProperties.generateServerName(interfacename)+"注册成功");
    }
}
