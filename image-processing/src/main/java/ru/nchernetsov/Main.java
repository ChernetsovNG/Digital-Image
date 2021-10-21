package ru.nchernetsov;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {
        URL bmpPictureURL = Main.class.getClassLoader().getResource("bmp/marbles.bmp");
        BufferedImage image = ImageIO.read(bmpPictureURL);

        ImageChannels imageChannels = new ImageChannels(image);

        // red channel
        BufferedImage redChannelImage = imageChannels.getRedChannelImage();
        ImageIO.write(redChannelImage, "bmp", new File("only-red.bmp"));

        // green channel
        BufferedImage greenChannelImage = imageChannels.getGreenChannelImage();
        ImageIO.write(greenChannelImage, "bmp", new File("only-green.bmp"));

        // blue channel
        BufferedImage blueChannelImage = imageChannels.getBlueChannelImage();
        ImageIO.write(blueChannelImage, "bmp", new File("only-blue.bmp"));

        // all rgb channels in one picture
        BufferedImage rgbChannelsImage = imageChannels.getRGBChannelsImage();
        ImageIO.write(rgbChannelsImage, "bmp", new File("rgb-channels.bmp"));
    }
}
