package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ServerDiscovery {
    @Autowired(required = false)
    private NamingService namingService;
    private final MiniServerProperties serverProperties;

    public ServerDiscovery(MiniServerProperties serverProperties){
        this.serverProperties = serverProperties;
    }

    public String getRandomAddress(String interfaceName) throws NacosException {
        if (namingService == null){
            return null;
        }
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
