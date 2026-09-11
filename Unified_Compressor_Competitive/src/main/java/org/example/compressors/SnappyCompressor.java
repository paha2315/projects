package org.example.compressors;

import org.example.compressors.abs.AbstractCompressor;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class SnappyCompressor extends AbstractCompressor {

    public SnappyCompressor() {
        super("Snappy");
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        return Snappy.compress(bytes);
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        return Snappy.uncompress(bytes);
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
