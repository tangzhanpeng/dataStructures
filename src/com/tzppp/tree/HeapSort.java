package com.tzppp.tree;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
//        int[] arr = {2, 9, 5, 4, 10, 22, 8};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        Date date1, date2;
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }
        date1 = new Date();
        System.out.println("堆排序前的时间是=" + simpleDateFormat.format(date1));
        heapSort(arr);
        date2 = new Date();
        System.out.println("堆排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");
    }

    public static void heapSort(int[] arr) {
        int length = arr.length;
        //首先构建好一个大顶堆
        //1.找到最后的一个叶子节点，从右到左，从下到上进行堆的调整（通俗点讲就是逆序进行逐一调整），最终生成完整的大顶堆
        for (int i = length / 2 - 1; i >= 0; --i) {
            adjustHeap(arr, i, length);
        }
        int tem;
        for (int high = length - 1; high > 0; --high) {
            // 将大顶堆首尾进行交换
            tem = arr[0];
            arr[0] = arr[high];
            arr[high] = tem;
            // 从新调整大顶堆，这时只需要调整一次即可
            adjustHeap(arr, 0, high);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int tem = arr[i];
        // 从左子节点开始遍历
        for (int j = getLeft(i); j < length; j = getLeft(j)) {
            //先比较左子节点和右子节点
            //若右子节点大于左子节点则指针指向右子节点
            //当然要先判断右子节点是否存在
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                ++j;
            }
            //判断指针的值是否大于父节点的值
            //若大于则父节点赋值为指针对应的值，子节点的值则暂时不变，因为存放了一个临时变量tem记录当前指针的值，只要在最后进行赋值即可，减少赋值运算
            if (arr[j] > tem) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = tem;
    }

    private static int getLeft(int index) {
        return 2 * index + 1;
    }
}