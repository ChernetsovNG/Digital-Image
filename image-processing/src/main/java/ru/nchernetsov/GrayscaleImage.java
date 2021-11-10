package ru.nchernetsov;

import java.awt.image.BufferedImage;

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
}
