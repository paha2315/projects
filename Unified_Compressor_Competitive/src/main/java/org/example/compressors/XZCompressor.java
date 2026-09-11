package org.example.compressors;

import org.example.compressors.abs.AbstractLevelsCompressor;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZInputStream;
import org.tukaani.xz.XZOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class XZCompressor extends AbstractLevelsCompressor {

    public XZCompressor() {
        this(LZMA2Options.PRESET_DEFAULT);
    }

    public XZCompressor(int level) {
        super("XZ", level, LZMA2Options.PRESET_MIN, LZMA2Options.PRESET_MAX);
    }

    @Override
    public byte[] compress(byte[] bytes) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (XZOutputStream xz = new XZOutputStream(bos, new LZMA2Options(getLevel()))) {
            xz.write(bytes);
        }
        return bos.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] bytes, int originalSize) throws DataFormatException, IOException {
        try (XZInputStream xz = new XZInputStream(new ByteArrayInputStream(bytes));
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            xz.transferTo(out);
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
