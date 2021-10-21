package ru.nchernetsov;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {
        URL bmpPictureURL = Main.class.getClassLoader().getResource("bmp/tru256.bmp");
        BufferedImage image = ImageIO.read(bmpPictureURL);

        int width = image.getWidth();
        int height = image.getHeight();

        int[][] array2DAlpha = new int[width][height];
        int[][] array2DRed = new int[width][height];
        int[][] array2DGreen = new int[width][height];
        int[][] array2DBlue = new int[width][height];

        for (int xPixel = 0; xPixel < width; xPixel++) {
            for (int yPixel = 0; yPixel < height; yPixel++) {
                Color color = new Color(image.getRGB(xPixel, yPixel));
                array2DAlpha[xPixel][yPixel] = color.getAlpha();
                array2DRed[xPixel][yPixel] = color.getRed();
                array2DGreen[xPixel][yPixel] = color.getGreen();
                array2DBlue[xPixel][yPixel] = color.getBlue();
            }
        }

        // red channel
        int[] arrayResultOnlyRed = new int[width * height];
        int indexRed = 0;
        for (int yPixel = 0; yPixel < height; yPixel++) {
            for (int xPixel = 0; xPixel < width; xPixel++) {
                int rgb = getRGB(array2DAlpha[xPixel][yPixel], array2DRed[xPixel][yPixel], 0, 0);
                arrayResultOnlyRed[indexRed] = rgb;
                indexRed += 1;
            }
        }

        BufferedImage bufferedImageOnlyRed = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageOnlyRed.setRGB(0, 0, width, height, arrayResultOnlyRed, 0, image.getWidth());
        ImageIO.write(bufferedImageOnlyRed, "bmp", new File("only-red.bmp"));

        // green channel
        int[] arrayResultOnlyGreen = new int[width * height];
        int indexGreen = 0;
        for (int yPixel = 0; yPixel < height; yPixel++) {
            for (int xPixel = 0; xPixel < width; xPixel++) {
                arrayResultOnlyGreen[indexGreen] = getRGB(array2DAlpha[xPixel][yPixel], 0,
                        array2DGreen[xPixel][yPixel], 0);
                indexGreen += 1;
            }
        }

        BufferedImage bufferedImageOnlyGreen = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageOnlyGreen.setRGB(0, 0, width, height, arrayResultOnlyGreen, 0, image.getWidth());
        ImageIO.write(bufferedImageOnlyGreen, "bmp", new File("only-green.bmp"));

        // blue channel
        int[] arrayResultOnlyBlue = new int[width * height];
        int indexBlue = 0;
        for (int yPixel = 0; yPixel < height; yPixel++) {
            for (int xPixel = 0; xPixel < width; xPixel++) {
                arrayResultOnlyBlue[indexBlue] = getRGB(array2DAlpha[xPixel][yPixel], 0, 0,
                        array2DBlue[xPixel][yPixel]);
                indexBlue += 1;
            }
        }

        BufferedImage bufferedImageOnlyBlue = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageOnlyBlue.setRGB(0, 0, width, height, arrayResultOnlyBlue, 0, image.getWidth());
        ImageIO.write(bufferedImageOnlyBlue, "bmp", new File("only-blue.bmp"));

        // original image duplicate (for testing)
        int[] arrayResultOriginal = new int[width * height];
        int indexCustomAlfa = 0;
        for (int yPixel = 0; yPixel < height; yPixel++) {
            for (int xPixel = 0; xPixel < width; xPixel++) {
                arrayResultOriginal[indexCustomAlfa] = getRGB(
                        array2DAlpha[xPixel][yPixel], array2DRed[xPixel][yPixel],
                        array2DGreen[xPixel][yPixel], array2DBlue[xPixel][yPixel]);
                indexCustomAlfa += 1;
            }
        }

        BufferedImage bufferedImageOriginal = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageOriginal.setRGB(0, 0, width, height, arrayResultOriginal, 0, width);
        ImageIO.write(bufferedImageOriginal, "bmp", new File("original-image.bmp"));

        // all rgb channels in one picture
        int[] arrayResultRGB = new int[3 * width * height];
        int indexRGB1 = 0;
        int indexRGB2 = 0;
        int indexRGB3 = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = getRGB(array2DAlpha[x][y], array2DRed[x][y], 0, 0);
                int rgb2 = getRGB(array2DAlpha[x][y], 0, array2DGreen[x][y], 0);
                int rgb3 = getRGB(array2DAlpha[x][y], 0, 0, array2DBlue[x][y]);

                indexRGB1 = 3 * width * y + x;
                indexRGB2 = indexRGB1 + width;
                indexRGB3 = indexRGB2 + width;

                arrayResultRGB[indexRGB1] = rgb1;
                arrayResultRGB[indexRGB2] = rgb2;
                arrayResultRGB[indexRGB3] = rgb3;
            }
        }

        BufferedImage bufferedImageRgbChannels = new BufferedImage(3 * width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImageRgbChannels.setRGB(0, 0, 3 * width, height, arrayResultRGB, 0, 3 * width);
        ImageIO.write(bufferedImageRgbChannels, "bmp", new File("rgb-channels.bmp"));
    }

    private static int getRGB(int alpha, int red, int green, int blue) {
        return ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF));
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
