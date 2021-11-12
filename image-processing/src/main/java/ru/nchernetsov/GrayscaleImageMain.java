package ru.nchernetsov;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import static ru.nchernetsov.operations.GrayscaleImageInterpolation.scaleNearestNeighborInterpolation;

public class GrayscaleImageMain {

    public static void main(String[] args) throws IOException {
        URL bmpPictureURL = RgbImageMain.class.getClassLoader().getResource("bmp/barbara_gray.bmp");
        BufferedImage image = ImageIO.read(bmpPictureURL);

        GrayscaleImage grayscaleImage = new GrayscaleImage(image);

        GrayscaleImage scaledImageTwo = scaleNearestNeighborInterpolation(grayscaleImage, 2.0);
        ImageIO.write(scaledImageTwo.getImage(), "bmp", new File(
                "barbara_gray_scaled_x2_nearest_neighbor_interpolation.bmp"));

        GrayscaleImage scaledImageHalf = scaleNearestNeighborInterpolation(grayscaleImage, 0.5);
        ImageIO.write(scaledImageHalf.getImage(), "bmp", new File("barbara_gray_scaled_x0" +
                ".5_nearest_neighbor_interpolation.bmp"));
    }
}
