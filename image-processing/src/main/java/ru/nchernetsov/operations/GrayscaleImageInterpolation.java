package ru.nchernetsov.operations;

import ru.nchernetsov.GrayscaleImage;

public class GrayscaleImageInterpolation {

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с интерполяцией яркости по ближайшему
     * соседу
     *
     * @param original    исходное изображение
     * @param coefficient коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage nearestNeighborInterpolation(GrayscaleImage original, double coefficient) {
        return null;
    }

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с билинейной интерполяцией яркости
     *
     * @param original    исходное изображение
     * @param coefficient коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage bilinearInterpolation(GrayscaleImage original, double coefficient) {
        return null;
    }

    /**
     * Изменение размеров монохромного изображения в заданное количество раз с бикубической интерполяцией яркости
     *
     * @param original    исходное изображение
     * @param coefficient коэффициент изменения размеров изображения
     * @return масштабированное изображение
     */
    public static GrayscaleImage bicubicInterpolation(GrayscaleImage original, double coefficient) {
        return null;
    }

}
