package org.example.compressors;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.example.compressors.abs.AbstractCompressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class Bzip2Compressor extends AbstractCompressor {
    public Bzip2Compressor() {
        super("Bzip2");
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (BZip2CompressorOutputStream bz = new BZip2CompressorOutputStream(bos)) {
            bz.write(bytes);
        }
        return bos.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        try (BZip2CompressorInputStream bz = new BZip2CompressorInputStream(new ByteArrayInputStream(bytes));
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            bz.transferTo(out);
            return out.toByteArray();
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
