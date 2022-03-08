package com.tzppp.sorts;

public final class MathUtils {

    /**
     * 取min - max 之间的随机整数， 包含min和max
     * 若min >= max 则返回min
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return min;
        }
        return (int) (min + Math.random() * (max - min + 1));
    }

}
