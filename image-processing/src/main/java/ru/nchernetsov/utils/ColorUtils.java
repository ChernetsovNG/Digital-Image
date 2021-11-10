package ru.nchernetsov.utils;

public class ColorUtils {

    public static int BLACK = getRGB(0, 0, 0);
    public static int WHITE = getRGB(255, 255, 255);

    public static int getRGB(int red, int green, int blue) {
        return getRGB(255, red, green, blue);
    }

    public static int getRGB(int alpha, int red, int green, int blue) {
        return ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF));
    }
}
