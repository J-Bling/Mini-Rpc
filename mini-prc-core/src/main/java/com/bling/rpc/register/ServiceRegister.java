package com.bling.rpc.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegister {
    private static final Map<String,Object> services = new ConcurrentHashMap<>();

    public static void register(String serviceName,Object beanObject){
        services.put(serviceName,beanObject);
    }

    public static Object get(String serviceName){
        return services.get(serviceName);
    }

    public static void remove(String serviceName){
        services.remove(serviceName);
    }
}
