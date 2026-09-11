package org.example.compressors.abs;

import java.io.IOException;
import java.util.Objects;
import java.util.zip.DataFormatException;

public abstract class AbstractCompressor {
    private String name;
    protected Object compressor = null;
    protected Object decompressor= null;

    public AbstractCompressor() {
        setName("AbstractCompressor");
    }

    public AbstractCompressor(String name) {
        setName(name);
    }

    public abstract byte[] compress(byte[] bytes) throws IOException;
    public abstract byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException;

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public abstract Object getCompressor();

    public void setCompressor(Object compressor) {
        this.compressor = compressor;
    }

    public abstract Object getDecompressor();

    public void setDecompressor(Object decompressor) {
        this.decompressor = decompressor;
    }
}
