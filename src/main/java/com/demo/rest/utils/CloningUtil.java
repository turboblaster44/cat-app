package com.demo.rest.utils;

import jakarta.enterprise.context.Dependent;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.*;

@Log
@Dependent
public class CloningUtil {
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T clone(T object) {
        try (ByteArrayInputStream is = new ByteArrayInputStream(writeObject(object).toByteArray());
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        }

    }
    private <T extends Serializable> ByteArrayOutputStream writeObject(T object) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(object);
            return os;
        }
    }

}
