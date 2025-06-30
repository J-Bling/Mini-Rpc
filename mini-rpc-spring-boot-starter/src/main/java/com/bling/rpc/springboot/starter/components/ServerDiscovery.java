package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ServerDiscovery {
    private final NamingService namingService;
    private final MiniServerProperties serverProperties;

    public ServerDiscovery(NamingService namingService,MiniServerProperties serverProperties){
        this.namingService = namingService;
        this.serverProperties = serverProperties;
    }

    public String getRandomAddress(String interfaceName) throws NacosException {
        List<Instance> instanceList = namingService.getAllInstances(serverProperties.generateServerName(interfaceName));
        if (instanceList==null || instanceList.isEmpty()){
            return null;
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(instanceList.size());
        Instance instance = instanceList.get(randomIndex);

        return generateAddress(instance.getIp(),instance.getPort());
    }

    private String generateAddress(String ip,int port){
        return "http://"+ip+":"+port;
    }
}
