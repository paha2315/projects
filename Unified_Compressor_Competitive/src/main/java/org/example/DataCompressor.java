package org.example;

import java.util.Arrays;

public class DataCompressor{
    public String name = "";
    public byte[] data = null;

    public DataCompressor(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public DataCompressor() {
    }

    @Override
    public String toString() {
        return "DataCompressor{" +
                "name='" + name + '\'' +
                ", dataLength=" + data.length +
                '}';
    }
}
