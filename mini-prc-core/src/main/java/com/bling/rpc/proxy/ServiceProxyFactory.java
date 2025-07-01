package com.bling.rpc.proxy;

import com.bling.rpc.interfaces.Callback;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    public static <T> T getProxy(Class<?> interfaceClass, String address,String serviceName, Callback<String> callback){
        return (T) Proxy.newProxyInstance(
                    interfaceClass.getClassLoader(),
                    new Class[]{interfaceClass},
                    new ServiceProxy().setAddress(address).setCallback(callback).setServiceName(serviceName)
                );
    }
}
