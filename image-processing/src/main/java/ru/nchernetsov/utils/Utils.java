package ru.nchernetsov.utils;

public class Utils {

    public static String intToBinaryString(int x) {
        return toBinaryStringWithLength(x, 32);
    }

    public static String toBinaryStringWithLength(int x, int len) {
        final char[] buff = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            int mask = 1 << i;
            buff[len - 1 - i] = (x & mask) != 0 ? '1' : '0';
        }
        return new String(buff);
    }
}
