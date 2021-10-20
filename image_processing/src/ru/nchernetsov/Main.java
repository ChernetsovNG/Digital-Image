package ru.nchernetsov;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(Main.class.getResource("marbles.bmp"));

        int[][] array2DAlpha = new int[image.getWidth()][image.getHeight()];
        int[][] array2DRed = new int[image.getWidth()][image.getHeight()];
        int[][] array2DGreen = new int[image.getWidth()][image.getHeight()];
        int[][] array2DBlue = new int[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                Color color = new Color(image.getRGB(xPixel, yPixel));
                array2DAlpha[xPixel][yPixel] = color.getAlpha();
                array2DRed[xPixel][yPixel] = color.getRed();
                array2DGreen[xPixel][yPixel] = color.getGreen();
                array2DBlue[xPixel][yPixel] = color.getBlue();
            }
        }

        // red channel
        int[] arrayResultOnlyRed = new int[image.getWidth() * image.getHeight()];
        int indexRed = 0;
        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                int rgb = getRGB(array2DAlpha[xPixel][yPixel], array2DRed[xPixel][yPixel], 0, 0);
                arrayResultOnlyRed[indexRed] = rgb;
                indexRed += 1;
            }
        }

        BMP bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyRed);
        bmp.saveBMP("only-red.bmp");

        // green channel
        int[] arrayResultOnlyGreen = new int[image.getWidth() * image.getHeight()];
        int indexGreen = 0;
        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                arrayResultOnlyGreen[indexGreen] = getRGB(array2DAlpha[xPixel][yPixel], 0, array2DGreen[xPixel][yPixel], 0);
                indexGreen += 1;
            }
        }

        bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyGreen);
        bmp.saveBMP("only-green.bmp");

        // blue channel
        int[] arrayResultOnlyBlue = new int[image.getWidth() * image.getHeight()];
        int indexBlue = 0;
        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                arrayResultOnlyBlue[indexBlue] = getRGB(array2DAlpha[xPixel][yPixel], 0, 0, array2DBlue[xPixel][yPixel]);
                indexBlue += 1;
            }
        }

        bmp = new BMP(image.getWidth(), image.getHeight(), (short) 32, arrayResultOnlyBlue);
        bmp.saveBMP("only-blue.bmp");

        // original image duplicate (for testing)
        int[] arrayResultOriginal = new int[image.getWidth() * image.getHeight()];
        int indexCustomAlfa = 0;
        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                arrayResultOriginal[indexCustomAlfa] = getRGB(
                        array2DAlpha[xPixel][yPixel], array2DRed[xPixel][yPixel],
                        array2DGreen[xPixel][yPixel], array2DBlue[xPixel][yPixel]);
                indexCustomAlfa += 1;
            }
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, image.getWidth(), image.getHeight(), arrayResultOriginal, 0, image.getWidth());
        ImageIO.write(bufferedImage, "bmp", new File("original-image.bmp"));
    }

    private static int getRGB(int alpha, int red, int green, int blue) {
        return ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
    }

    private static String intToBinaryString(int x) {
        return toBinaryStringWithLength(x, 32);
    }

    private static String toBinaryStringWithLength(int x, int len) {
        final char[] buff = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            int mask = 1 << i;
            buff[len - 1 - i] = (x & mask) != 0 ? '1' : '0';
        }
        return new String(buff);
    }
}
