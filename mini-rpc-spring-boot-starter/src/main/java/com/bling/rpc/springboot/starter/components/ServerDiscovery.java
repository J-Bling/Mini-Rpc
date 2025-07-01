package com.bling.rpc.springboot.starter.components;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.bling.rpc.springboot.starter.properties.MiniConsumerProperties;
import com.bling.rpc.springboot.starter.properties.MiniServerProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ServerDiscovery {
    @Autowired(required = false)
    private NamingService namingService;

    private final MiniConsumerProperties consumerProperties;

    public ServerDiscovery(MiniConsumerProperties miniConsumerProperties){
        this.consumerProperties = miniConsumerProperties;
    }

    public String getRandomAddress(String interfaceName) throws NacosException {
        if (namingService == null){
            return null;
        }
        String serverName = consumerProperties.generateServName(interfaceName);
        List<Instance> instanceList = namingService.getAllInstances(serverName);
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
