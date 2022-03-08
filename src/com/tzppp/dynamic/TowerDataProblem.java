package com.tzppp.dynamic;

/**
 * 动态规划
 * <p>
 * https://www.cnblogs.com/huststl/p/8664608.html
 * <p>
 * 1.数塔取数问题
 * <p>
 * 一个高度为N的由正整数组成的三角形，从上走到下，求经过的数字和的最大值。
 * 每次只能走到下一层相邻的数上，例如从第3层的6向下走，只能走到第4层的2或9上。
 * 该三角形第n层有n个数字，例如：
 * 第一层有一个数字：5
 * 第二层有两个数字：8 4
 * 第三层有三个数字：3 6 9
 * 第四层有四个数字：7 2 9 5
 * 最优方案是：5 + 8 + 6 + 9 = 28
 * 注意:上面应该是排列成一个三角形的样子不是竖向对应的，排版问题没有显示成三角形。
 * 状态定义: Fi，j是第i行j列项最大取数和，求第n行Fn，m（0 < m < n）中最大值。
 * 状态转移方程：Fi，j = max{Fi-1,j-1,Fi-1,j}+Ai,jt
 */
public class TowerDataProblem {

    private static int layer = 4;

    private static int[][] tower = {
            {5},
            {8, 4},
            {3, 6, 9},
            {222, 2, 9, 5}
    };

    public static void getMaxValue() {
        int[][] dp = new int[layer][layer];
        dp[0][0] = tower[0][0];
        long max = 0;
        for (int i = 1; i < layer; ++i) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + tower[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + tower[i][j];
                }
                if (i == layer - 1) {
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        System.out.println("最大值：" + max);
    }

    /**
     * 代码优化：
     * dp可以转为1维数组，减少空间复杂度
     * 因为在求最优解的时候只用到了上一层的数据
     */
    public static void getMaxValue2() {
        int[] dp = new int[layer];
        dp[0] = tower[0][0];
        long max = 0;
        for (int i = 1; i < layer; ++i) {
            for (int j = i; j >= 0; --j) {
                if (j == 0) {
                    dp[j] = dp[j] + tower[i][j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]) + tower[i][j];
                }
                if (i == layer - 1) {
                    max = Math.max(dp[j], max);
                }
            }
        }
        System.out.println("最大值：" + max);
    }

    public static void main(String[] args) {
        getMaxValue();
        getMaxValue2();
    }
}