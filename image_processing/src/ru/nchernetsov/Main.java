package ru.nchernetsov;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(Main.class.getResource("blu.bmp"));

        int[][] array2DAlpha = new int[image.getWidth()][image.getHeight()];
        int[][] array2DRed = new int[image.getWidth()][image.getHeight()];
        int[][] array2DGreen = new int[image.getWidth()][image.getHeight()];
        int[][] array2DBlue = new int[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int rgb = image.getRGB(xPixel, yPixel);
                array2DAlpha[xPixel][yPixel] = (rgb >> 24) & 0x000000FF;
                array2DRed[xPixel][yPixel] = (rgb >> 16) & 0x000000FF;
                array2DGreen[xPixel][yPixel] = (rgb >> 8) & 0x000000FF;
                array2DBlue[xPixel][yPixel] = (rgb) & 0x000000FF;
            }
        }

        // red channel
        int[] arrayResultOnlyRed = new int[image.getWidth() * image.getHeight()];
        int indexRed = 0;
        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                arrayResultOnlyRed[indexRed] = getRGB(0, array2DRed[xPixel][yPixel], 0, 0);
                indexRed += 1;
            }
        }

        BMP bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyRed);
        bmp.saveBMP("only-red.bmp");

        // green channel
        int[] arrayResultOnlyGreen = new int[image.getWidth() * image.getHeight()];
        int indexGreen = 0;
        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                arrayResultOnlyGreen[indexGreen] = getRGB(0, 0, array2DGreen[xPixel][yPixel], 0);
                indexGreen += 1;
            }
        }

        bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyGreen);
        bmp.saveBMP("only-green.bmp");

        // blue channel
        int[] arrayResultOnlyBlue = new int[image.getWidth() * image.getHeight()];
        int indexBlue = 0;
        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                arrayResultOnlyBlue[indexBlue] = getRGB(0, 0, 0, array2DBlue[xPixel][yPixel]);
                indexBlue += 1;
            }
        }

        bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyBlue);
        bmp.saveBMP("only-blue.bmp");
    }

    private static int getRGB(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | (blue);
    }
}
