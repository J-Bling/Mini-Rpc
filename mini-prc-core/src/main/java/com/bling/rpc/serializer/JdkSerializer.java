package com.bling.rpc.serializer;

import java.io.*;

public class JdkSerializer implements Serializer{

    public static final Serializer serializer = new JdkSerializer();

    @Override
    public <T> byte[] serializer(T object) throws IOException {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ) {
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> type) throws IOException {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ) {
            return type.cast(objectInputStream.readObject());
        } catch (ClassNotFoundException e) {
            throw new IOException("Deserialization failed: " + e.getMessage(), e);
        }
    }

}
