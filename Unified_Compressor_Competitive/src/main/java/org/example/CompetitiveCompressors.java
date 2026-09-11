package org.example;

import com.github.luben.zstd.Zstd;
import org.example.compressors.*;
import org.example.compressors.abs.AbstractCompressor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.DataFormatException;

public class CompetitiveCompressors {
    private static final Map<String, AbstractCompressor> compressorHashMap = new TreeMap<>();

    static {
        ArrayList<AbstractCompressor> compressors = new ArrayList<>();

//        for (int i = 0; i <= 11; i++)
//            compressors.add(new BrotliCompressor(i));

        compressors.add(new Bzip2Compressor());

        for (int i = 0; i <= 9; i++)
            compressors.add(new DeflatterCompressor(i));

        compressors.add(new GzipCompressor());

        for (int i = 1; i <= 17; i++)
            compressors.add(new Lz4Compressor(i));

        compressors.add(new LZFCompressor());

        for (int i = 0; i <= 9; i++)
            compressors.add(new LZMA2Compressor(i));

        compressors.add(new SnappyCompressor());

        for (int i = 0; i <= 9; i++)
            compressors.add(new XZCompressor(i));

        for (int i = 0; i <= Zstd.maxCompressionLevel(); i++)
            compressors.add(new ZstdCompressor(i));

        for (var compressor : compressors)
            compressorHashMap.put(compressor.toString(), compressor);
    }

    public static DataCompressor getBestCompressor(byte[] data) throws IOException {
        AtomicReference<DataCompressor> best = new AtomicReference<>(new DataCompressor("None", data));
        int originalSize = data.length;
        for (String key : compressorHashMap.keySet()) {
            try {
                byte[] newData = compress(key, data);
                int newSize = newData.length;
                if (newSize < best.get().data.length) {
                    System.out.println("Complete " + key + " (new best) (%7.3f%%)".formatted(((double) newSize) / originalSize * 100));
                    best.set(new DataCompressor(key, newData));
                }
                else System.out.println("Complete " + key +" (%7.3f%%)".formatted(((double) newSize) / originalSize * 100));
            } catch (Exception e) {
                System.out.println("Skip " + key);
            }
        }
        return best.get();
    }

    public static byte[] compress(String bestName, byte[] data) throws IOException {
        if (!compressorHashMap.containsKey(bestName))
            return data;
        return compressorHashMap.get(bestName).compress(data);
    }

    public static byte[] decompress(String bestName, byte[] data, int originalSize) throws IOException, DataFormatException {
        if (!compressorHashMap.containsKey(bestName))
            return data;
        return compressorHashMap.get(bestName).decompress(data, originalSize);
    }
}
