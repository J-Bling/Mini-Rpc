package com.bling.rpc.service;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer{

    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(new HttpServerHandle());
        httpServer.listen(port,result->{
           if (result.succeeded()){
               System.out.println("vertx http start success port "+port);
           }else {
               System.out.println("http start error");
           }
        });
    }
}
