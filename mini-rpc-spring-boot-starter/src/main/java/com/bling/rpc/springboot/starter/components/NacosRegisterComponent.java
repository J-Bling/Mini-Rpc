package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.bling.rpc.springboot.starter.properties.MiniNacosProperties;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;

public class NacosRegisterComponent {
    private final NamingService namingService;
    private final MiniServerProperties serverProperties;


    public NacosRegisterComponent(NamingService namingService,MiniServerProperties serverProperties){
        this.namingService = namingService;
        this.serverProperties = serverProperties;
    }

    public void register(String interfacename) throws NacosException {
        namingService.registerInstance(serverProperties.generateServerName(interfacename),serverProperties.getHost(),serverProperties.getPort());
        System.out.println("服务"+serverProperties.generateServerName(interfacename)+"注册成功");
    }
}
