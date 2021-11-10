package ru.nchernetsov;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class RgbImageMain {

    public static void main(String[] args) throws IOException {
        URL bmpPictureURL = RgbImageMain.class.getClassLoader().getResource("bmp/marbles.bmp");
        BufferedImage image = ImageIO.read(bmpPictureURL);

        RGBImage rgbImage = new RGBImage(image);

        // red channel
        BufferedImage redChannelImage = rgbImage.getRedChannelImage();
        ImageIO.write(redChannelImage, "bmp", new File("only-red.bmp"));

        // green channel
        BufferedImage greenChannelImage = rgbImage.getGreenChannelImage();
        ImageIO.write(greenChannelImage, "bmp", new File("only-green.bmp"));

        // blue channel
        BufferedImage blueChannelImage = rgbImage.getBlueChannelImage();
        ImageIO.write(blueChannelImage, "bmp", new File("only-blue.bmp"));

        // all rgb channels in one picture
        BufferedImage rgbChannelsImage = rgbImage.getRGBChannelsImage();
        ImageIO.write(rgbChannelsImage, "bmp", new File("rgb-channels.bmp"));

        // all rgb channels by least significant bit in one picture
        BufferedImage rgbLeastSignificantBitImage = rgbImage.getRGBLeastSignificantBitImage();
        ImageIO.write(rgbLeastSignificantBitImage, "bmp", new File("rgb-lsb-channels.bmp"));
    }
}
