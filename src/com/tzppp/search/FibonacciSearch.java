package com.tzppp.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000};
        System.out.println(fibSearch2(arr, 1001));
    }

    //得到一个fibonacci数列
    public static int[] fib(int size) {
        int[] f = new int[size];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < size; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static List<Integer> fib2(int high) {
        List<Integer> f = new ArrayList<>();
        f.add(1);
        f.add(1);
        while (high > f.get(f.size() - 1) - 1) {
            f.add(f.get(f.size() - 1) + f.get(f.size() - 2));
        }
        return f;
    }

    /**
     * 垃圾版斐波那契查找
     *
     * @param a   数组
     * @param key 需要查找的值
     * @return 返回对应的下标
     * 1）mid = low + F(k - 1) -1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标；
        int mid = 0; //存放mid值
        int[] f = fib(20); // 获取斐波那契数列
        // 获取斐波那契数列值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k] 可能大于a 的长度，因此我们需要使用Arrays类,构造一个新的数组，并指向temp[]
        // 不足的用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 实际上需要使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; ++i) {
            temp[i] = a[high];
        }
        //使用while循环处理，找到我们的key
        while (low <= high) {
            mid = low + (k == 0 ? 0 : f[k - 1] - 1);
            if (key < temp[mid]) { //向左边查找
                high = mid;
                // 1）全部元素= 前面的元素+ 后面的元素
                // 2）f[k] = f[k-1] + f[k-2]
                // 3)英文前面有f[k-1]个原色，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                // 即下次循环mid = f[k-1-1] -1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //为什么是k-2
                //1.全部元素=前面的元素+ 后面的元素
                //2。f[k] = f[k-1] + f[k-2]
                //3.因为后面有f[k-2]所以可以继续拆分f[k-2] = f[k-3] + f[k -4]
                // 4。即在f[k-2]的前面进行查找k-=2
                //5.即下次循环mid = f[k-1-2]-1
                k -= 2;
            } else {
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    /**
     * 改进版斐波那契查找
     * @param arr
     * @param findVal
     * @return
     */
    public static int fibSearch2(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        List<Integer> f = fib2(high);
        int k = f.size() - 1;
        while (low <= high) {
            do {
                mid = low + (k == 0 ? 0 : f.get(k - 1) - 1);
            }while (mid > high && --k >= 0);
            if (mid > high) {
                break;
            }
            if (findVal < arr[mid]) {
                high = mid - 1;
                --k;
            } else if (findVal > arr[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return mid;
            }
        }
        return -1;
    }
}