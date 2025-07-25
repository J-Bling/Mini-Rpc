package com.bling.rpc.springboot.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mini-rpc.server")
public class MiniServerProperties {
    private Boolean enable = true;
    private String prefix = "rpc-server";
    private String host = "localhost";
    private Integer port = 9009;

    public String generateServerName(String interfaceName){
        return this.prefix + "-" + interfaceName;
    }
}
