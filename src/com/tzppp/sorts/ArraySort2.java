package com.tzppp.sorts;

import java.util.Arrays;

public class ArraySort2 {
    public static void main(String[] args) {
        int[] nums = {9, 2, 3, 2, 1, 22, 3};
//        selectionSort(nums);
//        buboSort(nums);
//        insertSort(nums);
//        shellSort(nums);
        nums = mergeSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }


    // 选择排序
    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tem = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tem;
                }
            }
        }
    }

    // 冒泡排序
    public static void buboSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tem = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tem;
                }
            }

        }
    }

    // 插入排序
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tem = nums[i];
            int index = i - 1;
            while (index >= 0 && nums[index] > tem) {
                nums[index + 1] = nums[index];
                --index;
            }
            nums[index + 1] = tem;
        }
    }

    // 希尔排序
    public static void shellSort(int[] nums) {
        int gap = nums.length / 2;
        while (gap > 0) {
            for (int i = gap; i < nums.length; i++) {
                int tem = nums[i];
                int index = i - gap;
                while (index >= 0 && nums[index] > tem) {
                    nums[index + gap] = nums[index];
                    index -= gap;
                }
                nums[index + gap] = tem;
            }
            gap = gap / 2;
        }
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
    public static int[] merge(int[] left, int[] right) {
        int l1 = left.length, l2 = right.length;
        int length = l1 + l2;
        int[] res = new int[length];
        for (int i = 0, j = 0; i + j < length;) {
            if (i < l1 && (j >= l2 || left[i] < right[j])) {
                res[i+j] = left[i++];
            } else {
                res[i+j] = right[j++];
            }
        }
        return res;
    }

    //快速排序
    public static void quickSort(int[] nums, int start, int end) {

    }
}