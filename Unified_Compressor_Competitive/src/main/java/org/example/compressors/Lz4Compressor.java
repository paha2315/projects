package org.example.compressors;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import org.example.compressors.abs.AbstractLevelsCompressor;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class Lz4Compressor extends AbstractLevelsCompressor {
    LZ4Factory factory = null;

    public Lz4Compressor(int level) {
        super("Lz4", level, 1, 17);
    }

    public Lz4Compressor() {
        this(9);
    }

    public LZ4Factory getFactory() {
        if (factory == null)
            factory = LZ4Factory.fastestInstance();
        return factory;
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        int maxLen = getCompressor().maxCompressedLength(bytes.length);
        byte[] compressed = new byte[maxLen];
        int compressedLen = getCompressor().compress(bytes, 0, bytes.length, compressed, 0, maxLen);
        return java.util.Arrays.copyOf(compressed, compressedLen);
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        byte[] restored = new byte[originalSize];
        getDecompressor().decompress(bytes, 0, restored, 0, originalSize);
        return restored;
    }

    @Override
    public LZ4Compressor getCompressor() {
        if (compressor == null)
            setCompressor(getFactory().highCompressor(getLevel()));
        return (LZ4Compressor) compressor;
    }

    @Override
    public LZ4FastDecompressor getDecompressor() {
        if (decompressor == null)
            setDecompressor(getFactory().fastDecompressor());
        return (LZ4FastDecompressor) decompressor;
    }
}
