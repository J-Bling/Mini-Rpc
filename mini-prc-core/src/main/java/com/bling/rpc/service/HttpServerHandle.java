package com.bling.rpc.service;

import com.bling.rpc.model.RpcRequest;
import com.bling.rpc.model.RpcResponse;
import com.bling.rpc.register.ServiceRegister;
import com.bling.rpc.serializer.JdkSerializer;
import com.bling.rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandle implements Handler<HttpServerRequest> {

    private final Serializer serializer = JdkSerializer.serializer;

    @Override
    public void handle(HttpServerRequest request) {
        System.out.println("receive request "+request.method()+" uri:"+request.uri());

        request.bodyHandler(body->{
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try{
                rpcRequest = serializer.deserializer(bytes, RpcRequest.class);
            }catch (Exception e){
                e.printStackTrace();
            }

            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest==null){
                rpcResponse.setException(new RuntimeException("bad request"));
                rpcResponse.setMessage("400");
                doResponse(request, rpcResponse);
            }


            try{
                Class<?> interfaceClass = ServiceRegister.get(rpcRequest.getServiceName());
                Method method = interfaceClass.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(interfaceClass.newInstance(),rpcRequest.getArgs());
                rpcResponse.setException(null);
                rpcResponse.setMessage("invoke ok");
                rpcResponse.setData(result);
                rpcResponse.setDataType(request.getClass());

            }catch (Exception e){
                e.printStackTrace();
                rpcResponse.setData(null);
                rpcResponse.setException(new RuntimeException("server error: "+e.getMessage()));
                rpcResponse.setMessage(e.getMessage());
            }

            doResponse(request, rpcResponse);
        });
    }


    void doResponse(HttpServerRequest request, RpcResponse rpcResponse) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serializer(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
