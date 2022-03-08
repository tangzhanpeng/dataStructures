package com.tzppp.sorts;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ArraySort3 {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    // Requires positive x
    static int stringSize(int x) {
        for (int i = 0; ; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 2, 23, 44, 222, 1, 32, 5, 6};
//        selectionSort(arr);
//        insertSort(arr);
//        shellSort(arr);
//        mergeSort2(arr);
//        radixSort(arr);
        radixSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void mathTest() {
        System.out.println(Math.pow(10, 0));
    }

    public static void main(String[] args) {
        Date date1, date2;
        int length = 8000000;
        int[] arr = new int[length];
        int[] arr2 = new int[length];
        int[] arr3 = new int[length];
        int[] arr4 = new int[length];
        int[] arr5 = new int[length];
        int[] arr6 = new int[length];
        int[] arr7 = new int[length];
        int[] arr8 = new int[length];
        int[] arr9 = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
            arr2[i] = arr[i];
            arr3[i] = arr[i];
            arr4[i] = arr[i];
            arr5[i] = arr[i];
            arr6[i] = arr[i];
            arr7[i] = arr[i];
            arr8[i] = arr[i];
            arr9[i] = arr[i];
        }
//        Date date1 = new Date();
//        String dateStr1 = simpleDateFormat.format(date1);
//        System.out.println("冒泡排序前的时间是=" + dateStr1);
//        bubbleSort(arr);
//        Date date2 = new Date();
//        String dateStr2 = simpleDateFormat.format(date2);
//        System.out.println("冒泡排序前的时间是=" + dateStr2 + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "毫秒");

//        date1 = new Date();
//        System.out.println("选择排序前的时间是=" + simpleDateFormat.format(date1));
//        selectionSort(arr2);
//        date2 = new Date();
//        System.out.println("选择排序前的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

//        date1 = new Date();
//        System.out.println("插入排序前的时间是=" + simpleDateFormat.format(date1));
//        insertSort(arr3);
//        date2 = new Date();
//        System.out.println("插入排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

//        date1 = new Date();
//        System.out.println("希尔排序前的时间是=" + simpleDateFormat.format(date1));
//        shellSort(arr4);
//        date2 = new Date();
//        System.out.println("希尔排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

        date1 = new Date();
        System.out.println("快速排序前的时间是=" + simpleDateFormat.format(date1));
        quickSort(arr5);
        date2 = new Date();
        System.out.println("快速排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

        date1 = new Date();
        System.out.println("归并排序前的时间是=" + simpleDateFormat.format(date1));
        mergeSort(arr6);
        date2 = new Date();
        System.out.println("归并排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

        date1 = new Date();
        System.out.println("归并排序2前的时间是=" + simpleDateFormat.format(date1));
        mergeSort2(arr7);
        date2 = new Date();
        System.out.println("归并排序2后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

        date1 = new Date();
        System.out.println("基数排序前的时间是=" + simpleDateFormat.format(date1));
        mergeSort2(arr8);
        date2 = new Date();
        System.out.println("基数排序后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");

        date1 = new Date();
        System.out.println("基数排序2前的时间是=" + simpleDateFormat.format(date1));
        mergeSort2(arr9);
        date2 = new Date();
        System.out.println("基数排序2后的时间是=" + simpleDateFormat.format(date2) + " 花费时间：" + ((date2.getTime() - date1.getTime())) + "豪秒");
    }

    /**
     * 冒泡排序
     * 时间复杂度 O(n^2)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int tem;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int tem, index;
        for (int i = 0; i < arr.length - 1; i++) {
            tem = arr[i];
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (tem > arr[j]) {
                    tem = arr[j];
                    index = j;
                }
            }
            if (index > i) {
                arr[index] = arr[i];
                arr[i] = tem;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int tem, index;
        for (int i = 1; i < arr.length; i++) {
            tem = arr[i];
            index = i - 1;
            while (index >= 0 && arr[index] > tem) {
                arr[index + 1] = arr[index];
                --index;
            }
            arr[index + 1] = tem;
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int gap = arr.length >> 1;
        while (gap > 0) {
            int tem, index;
            for (int i = gap; i < arr.length; i++) {
                tem = arr[i];
                index = i - gap;
                while (index >= 0 && arr[index] > tem) {
                    arr[index + gap] = arr[index];
                    --index;
                }
                arr[index + gap] = tem;
            }
            gap >>= 1;
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0
                || start >= end || end >= arr.length || start < 0) {
            return;
        }
        int smallIndex = quickSortCore(arr, start, end);
        if (smallIndex - 1 > start) {
            quickSort(arr, start, smallIndex - 1);
        }
        if (smallIndex + 1 < end) {
            quickSort(arr, smallIndex + 1, end);
        }
    }

    private static int quickSortCore(int[] arr, int start, int end) {
        // 随机取一个下标[start -> end] 作为基准数据
        int q = start + (int) (Math.random() * (end - start + 1));
        // 将基准数据放到末尾作为基准
        swap(arr, q, end);
        // 循环数组，小于等于num[q]的放到前面，大于num[q]的放到后面
        int smallIndex = start - 1;
        for (int i = start; i <= end; ++i) {
            if (arr[i] <= arr[end] && i > ++smallIndex) {
                swap(arr, smallIndex, i);
            }
        }
        return smallIndex;
    }

    private static void swap(int[] arr, int source, int target) {
        if (source == target) {
            return;
        }
        arr[source] ^= arr[target];
        arr[target] ^= arr[source];
        arr[source] ^= arr[target];
    }

    // 归并排序
    public static int[] mergeSort(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        int nextLength = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, nextLength);
        int[] right = Arrays.copyOfRange(nums, nextLength, nums.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    //关键就是合并两个有序数组
    private static int[] merge(int[] left, int[] right) {
        int l1 = left.length, l2 = right.length;
        int length = l1 + l2;
        int[] res = new int[length];
        for (int i = 0, j = 0; i + j < length; ) {
            if (i < l1 && (j >= l2 || left[i] < right[j])) {
                res[i + j] = left[i++];
            } else {
                res[i + j] = right[j++];
            }
        }
        return res;
    }

    public static void mergeSort2(int[] arr) {
        int[] tem = new int[arr.length];
        mergeSort2(arr, 0, arr.length - 1, tem);
    }

    private static void mergeSort2(int[] arr, int left, int right, int[] tem) {
        if (left < right) {
            int mid = (right + left) / 2;
            mergeSort2(arr, left, mid, tem);
            mergeSort2(arr, mid + 1, right, tem);
            merge2(arr, left, mid, right, tem);
        }
    }

    private static void merge2(int[] arr, int left, int mid, int right, int[] tem) {
//        System.out.printf("left=%d,mid=%d,right=%d\n", left, mid, right);
        int l = left;
        int r = mid + 1;
        int t = left;
        while (t <= right) {
            if (l <= mid && (r > right || arr[l] <= arr[r])) {
                tem[t++] = arr[l++];
            } else {
                tem[t++] = arr[r++];
            }
        }
        System.arraycopy(tem, left, arr, left, right + 1 - left);
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr) {
        List<List<Integer>> radixList;
        int maxLoop = 1;
        int loop = 1;
        int temLoop, index;
        while (loop <= maxLoop) {
            radixList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                radixList.add(new ArrayList<>());
            }
            for (int anArr : arr) {
                if (loop == 1 && (temLoop = stringSize(anArr)) > maxLoop) {
                    maxLoop = temLoop;
                }
                index = (anArr / (int) Math.pow(10, loop - 1)) % 10;
                radixList.get(index).add(anArr);
            }
            int tem = 0;
            for (List<Integer> radix : radixList) {
                for (Integer integer : radix) {
                    arr[tem++] = integer;
                }
            }
            ++loop;
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void radixSort2(int[] arr) {
        int[][] radixArr = new int[10][arr.length];
        int[] radixValue = new int[10];
        int maxLoop = 1;
        int loop = 1;
        int temLoop, index, tem;
        while (loop <= maxLoop) {
            for (int anArr : arr) {
                if (loop == 1 && (temLoop = stringSize(anArr)) > maxLoop) {
                    maxLoop = temLoop;
                }
                index = (anArr / (int) Math.pow(10, loop - 1)) % 10;
                radixArr[index][radixValue[index]++] = anArr;
            }
            tem = 0;
            for (int i = 0; i < radixArr.length; i++) {
                for (int j = 0; j < radixValue[i]; j++) {
                    arr[tem++] = radixArr[i][j];
                }
                radixValue[i] = 0;
            }
            ++loop;
        }
    }
}