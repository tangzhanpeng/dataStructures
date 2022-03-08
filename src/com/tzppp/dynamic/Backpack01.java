package com.tzppp.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划
 * https://blog.csdn.net/qq_37438740/article/details/105072492
 * 2、01背包问题
 * 问题描述：有N件物品，每件物品的重量为weight[ i ]，价值为value[ i ]。现有一个容量为W的背包，问如何选取物品放入背包，使得背包内物品的总价值最大。物品不重复。
 * 定义背包总价值sum[i][j] :
 * 背包容量为j时，在前i件物品中取小于等于i件物品，此时取得的物品的价值最大，也就是此时背包里面物品的总价值最大。
 * <p>
 * 转移方程：
 * 1、物品重量无法放入：sum[i][j] = sum[i-1][j]
 * 2、物品重量可以放入：sum[i][j] = max(
 * sum[i-1][j-weight[i]] + value[i],   // 下标物品+减去物品重量可以放入的最大价值
 * sum[i-1][j]
 * )
 */
public class Backpack01 {

    static int N = 5;//物品有五件
    static int W = 20;//背包容量为20
    static int weight[] = {0, 2, 3, 4, 5, 9};//重量 2 3 4 5 9
    static int value[] = {0, 3, 4, 5, 8, 10};//价值 3 4 5 8 10

    public static void getMaxValue() {
        int[][] sum = new int[N + 1][W + 1]; // 让下标从1开始
        int[][] ns = new int[N + 1][W + 1]; // 背包物品
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= W; ++j) {
                if (weight[i] > j) {
                    sum[i][j] = sum[i - 1][j];
                } else {
                    if (sum[i - 1][j - weight[i]] + value[i] > sum[i - 1][j]) {
                        sum[i][j] = sum[i - 1][j - weight[i]] + value[i];
                        //放入新的物品
                        ns[i][j] = i;
                    } else {
                        sum[i][j] = sum[i - 1][j];
                    }
                }
            }
        }
        //输出背包物品
        int i = N, j = W;
        while (i > 0 && j > 0) {
            if (ns[i][j] > 0) {
                System.out.println("第"+ns[i][j]+"个商品放入了背包");
                j = j - weight[ns[i][j]];
            }
            i--;
        }
        //输出最大价值
        System.out.println(sum[N][W]);
    }

    /**
     * 优化
     * 观察上面的状态转移方程可以发现 i 和 i-1 有着固定的对应关系，所以 i 其实可以省略掉，简化二维数组，采用一维数组sum[ j ]来记录总价值
     * <p>
     * 问题1：这个和上面的方法有什么区别呢？
     * 上面的方法直接用二维数组来记录总价值，记录后的数值会一直保存，这里用一维数组，会经过N（物品件数）次的刷新。举例来说，经过第一轮遍历（ i=1 那一轮），sum[ j ]数组中保存的数据意义为：背包容量为 j 时，只有第一个物品可以选择时的总价值。当 i =2 时就可以利用 sum 数组中保存的值（ i=1 ）来更新得到新的价值，直到 i 遍历结束。这样空间复杂度就会降低很多
     * <p>
     * 问题2：为什么j从W-1开始遍历而不是1
     * 这里的状态转移方程是：
     * <p>
     * sum[j]=Math.max(sum[j], sum[j-weight[i]]+value[i]);
     * 1
     * 可以看到sum[ j ]会用sum数组中小于 j 的数组元素来更新。同样假设现在sum数组已经经过第一轮遍历（ i=1，第一轮遍历假设不存在矛盾），如果我们从 j=1 开始遍历来求第二轮的数组（ i=2 ），假设sum[ 1 ]值不变（不拿），现在 j=2了，刚好拿了一个重量为 1 的物品，那么根据状态转移方程，现在要用sum[ 1 ]的值来更新sum[ 2 ]，可是此时的sum[ 1 ]是 i=2 时的值，更新需要 i=1时的值，这时就会出现矛盾
     *
     * @return
     */
    public static int getMaxValue2() {
        int[] sum = new int[W + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = W; j > 0; --j) {
                if (weight[i] <= j) {
                    sum[j] = Math.max(sum[j - weight[i]] + value[i], sum[j]);
                }
            }
        }
        return sum[W];
    }


    public static void main(String[] args) {
        getMaxValue();
        System.out.println(getMaxValue2());
    }
}