package com.bling.rpc.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio-rpc.consumer")
public class MiniConsumerProperties {
    //连接服务配置
    private String name = "rpc-server";
    private String host = "localhost";
    private Integer port = 9009;
}
