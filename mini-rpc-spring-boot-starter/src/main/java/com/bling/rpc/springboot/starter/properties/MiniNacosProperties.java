package com.bling.rpc.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minio-rpc.nacos")
public class MiniNacosProperties {
    private Boolean enable =true;
    private String address = "localhost:8848";
}
