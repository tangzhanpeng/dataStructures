package com.tzppp.recursion;

public class RecursionDemo1 {

    public static void main(String[] args) {
        System.out.println("=" + factorial(20));
    }

    /**
     * 逆序打印
     * @param n
     */
    public static void reversePrint(int n) {
        if (n > 1) {
            reversePrint(n - 1);
        }
        System.out.println(n);
    }

    /**
     * 阶乘问题
     * n! = 1 * 2 ... * n
     * @return
     */
    public static long factorial(long n) {
        if (n > 1) {
            System.out.print(n + "*");
            return n * factorial(n - 1);
        } else  {
            System.out.print(n);
            return 1;
        }
    }
}