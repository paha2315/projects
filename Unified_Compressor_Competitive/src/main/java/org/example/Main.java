package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.pow;

public class Main {

    static ArrayList<String> sizeLetter = new ArrayList<>(Arrays.asList("Б", "КБ", "МБ", "ГБ", "ПБ", "ЦБ"));

    public static String getHumanSize(double size, int a, int b) {
        int sizeIndex = 0;
        int block = (int) pow(2, 10);
        while (size > block) {
            sizeIndex++;
            size /= block;
        }
        return String.format("%%%d.%df %s", a, b, sizeLetter.get(sizeIndex)).formatted(size);
    }

    public static void main(String[] args) {
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream("D:\\Download\\6\\Тема 6.2. Аудит технического состояния БАС.mp4");
            data = fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            DataCompressor dataCompressor = CompetitiveCompressors.getBestCompressor(data);
            System.out.println(dataCompressor);
            System.out.println("Size:");
            System.out.println("\tOld: " + getHumanSize(data.length, 5, 2));
            System.out.println("\tNew: " + getHumanSize(dataCompressor.data.length, 5, 2));
            System.out.println("\tCompress ratio: " + String.format("%7.3f", ((double) dataCompressor.data.length) / data.length * 100) + "%");
            System.out.println("\tSubstr: " + getHumanSize((double) data.length - dataCompressor.data.length, 5, 2));
        } catch (Exception e) {
            System.out.println("e");
        }
    }

}