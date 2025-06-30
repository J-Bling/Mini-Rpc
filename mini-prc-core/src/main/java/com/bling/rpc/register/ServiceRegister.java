package com.bling.rpc.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegister {
    private static final Map<String,Class<?>> services = new ConcurrentHashMap<>();

    public static void register(String serviceName,Class<?> serviceClass){
        services.put(serviceName,serviceClass);
    }

    public static Class<?> get(String serviceName){
        return services.get(serviceName);
    }

    public static String get(Class<?> serviceClass){
        for (Map.Entry<String,Class<?>> entry : services.entrySet()){
            if (entry.getValue().getName().equals(serviceClass.getName())){
                return entry.getKey();
            }
        }
        return null;
    }

    public static void remove(String serviceName){
        services.remove(serviceName);
    }
}
