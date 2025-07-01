package com.bling.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.bling.rpc.interfaces.Callback;
import com.bling.rpc.model.RpcRequest;
import com.bling.rpc.model.RpcResponse;
import com.bling.rpc.serializer.JdkSerializer;
import com.bling.rpc.serializer.Serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler {
    private String address;
    private Callback<String> callback;
    private String serviceName;

    private static final Serializer serializer = JdkSerializer.serializer;

    public ServiceProxy setAddress(String address){
        this.address=address;
        return this;
    }

    public ServiceProxy setCallback(Callback<String> callback){
        this.callback = callback;
        return this;
    }

    public ServiceProxy setServiceName(String serviceName){
        this.serviceName = serviceName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sName = serviceName==null || serviceName.isEmpty() ? method.getDeclaringClass().getName() : serviceName;
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(sName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try{
            byte[] bytes = serializer.serializer(rpcRequest);
            String add = address == null ? callback.get(serviceName) : address;
            if (add==null){
                throw new RuntimeException("调用地址不能为null");
            }
            try(HttpResponse httpResponse = HttpRequest.post(add)
                    .body(bytes)
                    .execute()
            ){
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserializer(result, RpcResponse.class);
                if (rpcResponse.getException()!=null){
                    throw new RuntimeException("remote invoke error : "+ rpcResponse.getMessage());
                }
                return rpcResponse.getData();
            }
        }catch (Exception e){
            throw new RuntimeException("remote invoke error : "+e.getMessage());
        }
    }
}
