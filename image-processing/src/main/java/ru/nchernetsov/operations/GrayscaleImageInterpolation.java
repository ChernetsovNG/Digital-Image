package ru.nchernetsov.operations;

import ru.nchernetsov.GrayscaleImage;

public class GrayscaleImageInterpolation {

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с интерполяцией яркости по ближайшему
     * соседу
     *
     * @param original исходное изображение
     * @param scale    коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage scaleNearestNeighborInterpolation(GrayscaleImage original, double scale) {
        int width = (int) (original.getWidth() * scale);
        int height = (int) (original.getHeight() * scale);
        int[][] brightness = original.getBrightness();

        int[][] array = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array[x][y] = brightness[(int) (x / scale)][(int) (y / scale)];
            }
        }
        return new GrayscaleImage(original.getBrightnessResolution(), width, height, array);
    }

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с билинейной интерполяцией яркости
     *
     * @param original исходное изображение
     * @param scale    коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage scaleBilinearInterpolation(GrayscaleImage original, double scale) {
        return null;
    }

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с бикубической интерполяцией яркости
     *
     * @param original исходное изображение
     * @param scale    коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage scaleBicubicInterpolation(GrayscaleImage original, double scale) {
        return null;
    }

}
