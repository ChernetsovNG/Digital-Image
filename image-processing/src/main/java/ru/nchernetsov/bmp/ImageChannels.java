package ru.nchernetsov.bmp;

import java.awt.image.BufferedImage;

public class ImageChannels {
    private final int width;
    private final int height;
    private final int[][] alpha;
    private final int[][] red;
    private final int[][] green;
    private final int[][] blue;

    public ImageChannels(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.alpha = new int[width][height];
        this.red = new int[width][height];
        this.green = new int[width][height];
        this.blue = new int[width][height];
        fillChannels(image);
    }

    private void fillChannels(BufferedImage image) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                this.alpha[x][y] = (rgb >> 24) & 0xff;
                this.red[x][y] = (rgb >> 16) & 0xff;
                this.green[x][y] = (rgb >> 8) & 0xff;
                this.blue[x][y] = (rgb) & 0xff;
            }
        }
    }

    public int[][] getAlpha() {
        return alpha;
    }

    public int[][] getRed() {
        return red;
    }

    public int[][] getGreen() {
        return green;
    }

    public int[][] getBlue() {
        return blue;
    }
}
