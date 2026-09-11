package org.example.compressors;

import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import org.example.compressors.abs.AbstractCompressor;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class LZFCompressor extends AbstractCompressor {

    public LZFCompressor() {
        super("LZF");
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        return LZFEncoder.encode(bytes);
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        return LZFDecoder.decode(bytes);
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
