package org.example.compressors;

import org.example.compressors.abs.AbstractCompressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompressor extends AbstractCompressor {
    public GzipCompressor() {
        super("Gzip");
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream g = new GZIPOutputStream(bos)) {
            g.write(bytes);
        }
        return bos.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        try (GZIPInputStream g = new GZIPInputStream(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            g.transferTo(bos); // Java 9+
            return bos.toByteArray();
        }
    }

    @Override
    public Object getCompressor() {
        return null;
    }

    @Override
    public Object getDecompressor() {
        return null;
    }
}
