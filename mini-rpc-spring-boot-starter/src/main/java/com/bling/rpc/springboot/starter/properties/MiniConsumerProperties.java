package com.bling.rpc.springboot.starter.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mini-rpc.consumer")
public class MiniConsumerProperties {
    //连接服务配置
    private String prefix = "rpc-server";
    private String host = "localhost";
    private Integer port = 9009;

    public String generateServName(String interfaceName){
        return prefix +"-"+interfaceName;
    }
}
