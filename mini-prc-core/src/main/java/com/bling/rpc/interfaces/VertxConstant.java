package com.bling.rpc.interfaces;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public interface VertxConstant {
     Vertx vertx = Vertx.vertx();
     HttpClient httpClient = vertx.createHttpClient();
}
