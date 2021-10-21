package ru.nchernetsov;

public class ColorUtils {

    public static int getRGB(int red, int green, int blue) {
        return getRGB(255, red, green, blue);
    }

    public static int getRGB(int alpha, int red, int green, int blue) {
        return ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF));
    }
}
