package org.example.compressors;

import com.github.luben.zstd.Zstd;
import org.example.compressors.abs.AbstractLevelsCompressor;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class ZstdCompressor extends AbstractLevelsCompressor {

    public ZstdCompressor(int level) {
        super("Zstd", level, Zstd.minCompressionLevel(), Zstd.maxCompressionLevel());
    }

    public ZstdCompressor() {
        this(Zstd.defaultCompressionLevel());
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        return Zstd.compress(bytes, getLevel());
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        return Zstd.decompress(bytes, originalSize);
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
