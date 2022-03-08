package com.tzppp.divideAndConquer;

/**
 * 分治,汉诺塔
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

    //汉诺塔移动的方法
    //使用分治塔
    public static void hanoi(int num, char a, char b, char c) {
        //如果只有1个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果num >=2,可以看作是2个盘 1.最小面的盘  2.上面的盘
            // 1.把最上面的盘a->b, 移动过程会使用到c
            hanoi(num - 1, a, c, b);
            // 2.把下面的盘a->c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3.把B塔的所有盘b->c, 移动过程会使用到a
            hanoi(num - 1, b, a, c);
        }
    }
}