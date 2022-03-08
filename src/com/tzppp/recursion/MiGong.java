package com.tzppp.recursion;

/**
 * 小球迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下至为1
        for (int i = 0; i < 7; ++i) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右为1
        for (int i = 0; i < 8; ++i) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        System.out.println("地图的情况～～～");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        // 使用递归回溯
//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        System.out.println("小球找到的路～～～");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     *
     * @param map 地图
     * @param i   从什么位置开始找
     * @param j
     * @return 如果找到通路就返回true, 否则返回false
     * 1.map表示地图
     * 2.i,j表示小球的位置
     * 3.如果小球找到map[6][5]则表示找到
     * 4.约定，当map[i][j]为0表示该店没有走过，当为1表示墙，2表示可以走，3表示该位置已经走过但是走不通
     * 5.在走迷宫时需要确定一个策略，下->右->上->左，如果该点走不通再回溯
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {// 如果当前的点没有走过
                //按照策略走 下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0, 有可能是1，2，3
                return false;
            }
        }
    }

    //修改策略 上->右->下>左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {// 如果当前的点没有走过
                //按照策略走 下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay2(map, i - 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0, 有可能是1，2，3
                return false;
            }
        }
    }
}