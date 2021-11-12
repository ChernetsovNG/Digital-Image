package ru.nchernetsov.utils;

import java.awt.image.BufferedImage;

import static ru.nchernetsov.utils.ColorUtils.getRGB;

public class ImageUtils {

    public static BufferedImage convertGrayscalePixelsToBufferedImage(int width, int height, int[][] data) {
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int brightness = data[x][y];
                array[y * width + x] = getRGB(brightness, brightness, brightness);
            }
        }
        return createBufferedImage(width, height, array);
    }

    public static BufferedImage createBufferedImage(int width, int height, int[] data) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, width, height, data, 0, width);
        return bufferedImage;
    }
}
