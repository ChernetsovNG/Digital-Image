package ru.nchernetsov;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GrayscaleImageMain {

    public static void main(String[] args) throws IOException {
        URL bmpPictureURL = RgbImageMain.class.getClassLoader().getResource("bmp/barbara_gray.bmp");
        BufferedImage image = ImageIO.read(bmpPictureURL);

        GrayscaleImage grayscaleImage = new GrayscaleImage(16, image);
    }
}
