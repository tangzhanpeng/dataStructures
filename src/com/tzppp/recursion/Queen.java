package com.tzppp.recursion;

/**
 * 8皇后问题
 */
public class Queen {
    private int max = 8;
    private int[] array = new int[max];
    private int count = 0;
    private int steps = 0;

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; ++i) {
            ++steps;
            array[n] = i;
            if (judge(n)) {
                // 不冲突则放置第n+1个皇后
                check(n + 1);
            }
        }
    }

    //当我们放置第n个皇后的时候，该皇后是否和前面已经摆放的皇后冲突
    // n表示第n个皇后
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(array[i] - array[n]) == n - i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印放置方法
     */
    private void print() {
        for (int anArray : array) {
            System.out.print(anArray + " ");
        }
        ++count;
        System.out.println();
    }

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
        System.out.println("总计摆法="+queen.count);
        System.out.println("总计步数="+queen.steps);
    }


}