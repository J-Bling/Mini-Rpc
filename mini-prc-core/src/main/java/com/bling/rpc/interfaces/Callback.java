package com.bling.rpc.interfaces;

public interface Callback<T> {
    T get(T serviceName);
}
