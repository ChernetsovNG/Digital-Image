package ru.nchernetsov;

import java.awt.image.BufferedImage;

import static ru.nchernetsov.utils.ColorUtils.getRGB;
import static ru.nchernetsov.utils.ImageUtils.createBufferedImage;

public class GrayscaleImage {
    private final int width;
    private final int height;
    private final int brightnessResolution;  // in bits, values count = 2^brightnessResolution
    private final int[][] brightness;

    public GrayscaleImage(BufferedImage image) {
        this.brightnessResolution = 8;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.brightness = new int[width][height];
        fill(image);
    }

    public GrayscaleImage(int brightnessResolution, BufferedImage image) {
        this.brightnessResolution = brightnessResolution;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.brightness = new int[width][height];
        fill(image);
    }

    public GrayscaleImage(int brightnessResolution, int width, int height, int[][] brightness) {
        this.brightnessResolution = brightnessResolution;
        this.width = width;
        this.height = height;
        this.brightness = brightness;
    }

    private void fill(BufferedImage image) {
        int red, green, blue;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                red = (rgb >> 16) & 0xff;
                green = (rgb >> 8) & 0xff;
                blue = (rgb) & 0xff;
                if (red != blue || green != blue) {
                    throw new IllegalArgumentException("Not grayscale image!");
                }
                // If image is monochrome, then red green and blue should be equal
                if (this.brightnessResolution == 8) {
                    this.brightness[x][y] = red;
                } else {
                    this.brightness[x][y] = toBrightnessResolution(red);
                }
            }
        }
    }

    private int toBrightnessResolution(int x) {
        double original = x * 1.0 / 255;
        double result = original * (1 << this.brightnessResolution);
        return (int) result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBrightnessResolution() {
        return brightnessResolution;
    }

    public int[][] getBrightness() {
        return brightness;
    }

    public BufferedImage getImage() {
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[y * width + x] = getRGB(brightness[x][y]);
            }
        }
        return createBufferedImage(width, height, array);
    }
}
