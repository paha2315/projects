package org.example.compressors;

import org.example.compressors.abs.AbstractLevelsCompressor;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflatterCompressor extends AbstractLevelsCompressor {

    public DeflatterCompressor(int level) {
        super("Deflatter", level, Deflater.NO_COMPRESSION, Deflater.BEST_COMPRESSION);
    }

    public DeflatterCompressor() {
        this(Deflater.DEFAULT_COMPRESSION);
    }

    @Override
    public byte[] compress(byte[] bytes) {
        getCompressor().setInput(bytes);
        getCompressor().finish();
        byte[] output = new byte[bytes.length * 2];
        int len = getCompressor().deflate(output);
        getCompressor().end();
        return java.util.Arrays.copyOf(output, len);
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException {
        getDecompressor().setInput(bytes);
        byte[] output = new byte[originalSize];
        getDecompressor().inflate(output);
        getDecompressor().end();
        return output;
    }

    @Override
    public Deflater getCompressor() {
        if (compressor == null)
            setCompressor(new Deflater(getLevel()));
        return (Deflater) compressor;
    }

    @Override
    public Inflater getDecompressor() {
        if (decompressor == null)
            setDecompressor(new Inflater());
        return (Inflater) decompressor;
    }
}
