package ru.nchernetsov.utils;

import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage createBufferedImage(int width, int height, int[] data) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, width, height, data, 0, width);
        return bufferedImage;
    }
}
