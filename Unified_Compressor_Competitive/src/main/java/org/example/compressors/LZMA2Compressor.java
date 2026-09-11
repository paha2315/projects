package org.example.compressors;

import org.example.compressors.abs.AbstractLevelsCompressor;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.LZMAOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class LZMA2Compressor extends AbstractLevelsCompressor {

    public LZMA2Compressor(int level) {
        super("LZMA2", level, LZMA2Options.PRESET_MIN, LZMA2Options.PRESET_MAX);
    }

    public LZMA2Compressor() {
        this(LZMA2Options.PB_DEFAULT);
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (LZMAOutputStream lz = new LZMAOutputStream(bos, new LZMA2Options(getLevel()), bytes.length)) {
            lz.write(bytes);
        }
        return bos.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        try (LZMAInputStream lz = new LZMAInputStream(new ByteArrayInputStream(bytes)); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            lz.transferTo(out);
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
