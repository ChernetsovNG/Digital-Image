package ru.nchernetsov;

import java.awt.image.BufferedImage;

import static ru.nchernetsov.utils.ColorUtils.BLACK;
import static ru.nchernetsov.utils.ColorUtils.WHITE;
import static ru.nchernetsov.utils.ColorUtils.getRGB;

public class RGBImage {
    private final int width;
    private final int height;
    private final int[][] red;
    private final int[][] green;
    private final int[][] blue;

    public RGBImage(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.red = new int[width][height];
        this.green = new int[width][height];
        this.blue = new int[width][height];
        fillChannels(image);
    }

    private void fillChannels(BufferedImage image) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                this.red[x][y] = (rgb >> 16) & 0xff;
                this.green[x][y] = (rgb >> 8) & 0xff;
                this.blue[x][y] = (rgb) & 0xff;
            }
        }
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

    public BufferedImage getRedChannelImage() {
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[y * width + x] = getRGB(red[x][y], 0, 0);
            }
        }
        return createBufferedImage(array);
    }

    public BufferedImage getGreenChannelImage() {
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[y * width + x] = getRGB(0, green[x][y], 0);
            }
        }
        return createBufferedImage(array);
    }

    public BufferedImage getBlueChannelImage() {
        int[] array = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[y * width + x] = getRGB(0, 0, blue[x][y]);
            }
        }
        return createBufferedImage(array);
    }

    public BufferedImage getRGBChannelsImage() {
        int[] array = new int[3 * width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[3 * width * y + x] = getRGB(red[x][y], 0, 0);
                array[3 * width * y + x + width] = getRGB(0, green[x][y], 0);
                array[3 * width * y + x + 2 * width] = getRGB(0, 0, blue[x][y]);
            }
        }
        BufferedImage bufferedImage = new BufferedImage(3 * width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, 3 * width, height, array, 0, 3 * width);
        return bufferedImage;
    }

    public BufferedImage getRGBLeastSignificantBitImage() {
        int[] array = new int[3 * width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // наименее значащий бит = 0 => пиксель белый, 1 => пиксель чёрный
                array[3 * width * y + x] = (red[x][y] & 1) == 0 ? WHITE : BLACK;
                array[3 * width * y + x + width] = (green[x][y] & 1) == 0 ? WHITE : BLACK;
                array[3 * width * y + x + 2 * width] = (blue[x][y] & 1) == 0 ? WHITE : BLACK;
            }
        }
        BufferedImage bufferedImage = new BufferedImage(3 * width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, 3 * width, height, array, 0, 3 * width);
        return bufferedImage;
    }

    private BufferedImage createBufferedImage(int[] data) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferedImage.setRGB(0, 0, width, height, data, 0, width);
        return bufferedImage;
    }
}
