package org.example.compressors;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.decoder.BrotliInputStream;
import com.aayushatharva.brotli4j.encoder.Encoder;
import org.example.compressors.abs.AbstractLevelsCompressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class BrotliCompressor extends AbstractLevelsCompressor {
    private Encoder.Parameters parameters = null;

    public BrotliCompressor(int level) {
        super("Brotli", level, 0, 11);
    }

    public BrotliCompressor() {
        this(6);
    }

    public static void init() {
        Brotli4jLoader.ensureAvailability();
    }

    public Encoder.Parameters getParameters() {
        if (parameters == null) setParameters(new Encoder.Parameters().setQuality(getLevel()));
        return parameters;
    }

    public void setParameters(Encoder.Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        return Encoder.compress(bytes, getParameters());
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (BrotliInputStream in = new BrotliInputStream(new ByteArrayInputStream(bytes))) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
            }
        }
        return out.toByteArray();
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
