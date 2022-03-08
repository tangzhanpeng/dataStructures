package com.tzppp.sorts;

import java.util.Arrays;

/**
 * 排序练习
 * 默认都是传入int[]数组
 */
public class ArraySort {


    /**
     * 选择排序
     *
     * @param nums
     */
    public void selection(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
//        int min = nums[0];
//        int index = 0;
//        for (int j = 0; j < nums.length - 1; j++) {
//            for (int i = j; i < nums.length; ++i) {
//                if (nums[i] < min) {
//                    min = nums[i];
//                    index = i;
//                }
//                if (i == nums.length - 1) {
//                    nums[index] = nums[j];
//                    nums[j] = min;
//                    min = nums[j + 1];
//                    index = j + 1;
//                }
//            }
//        }
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

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public void bubbling(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tem = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tem;

                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param nums
     */
    public void insertSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 1; i < nums.length; ++i) {
            int tem = nums[i];
            int index = i - 1;
            while (index >= 0 && nums[index] > tem) {
                nums[index + 1] = nums[index];
                --index;
            }
            nums[index + 1] = tem;
        }

//        for (int i = 1; i < nums.length; ++i) {
//            int tem = nums[i];
//            int index = i - 1;
//            for (int j = i - 1; j >= 0; --j) {
//                if (nums[j] > tem) {
//                    nums[j + 1] = nums[j];
//                    index = j;
//                } else {
//                    break;
//                }
//            }
//            nums[index] = tem;
//        }

//        for (int i = 0; i < nums.length - 1; ++i) {
//            int tem = nums[i + 1];
//            int index = i;
//            while (index >= 0 && nums[index] > tem) {
//                nums[index + 1] = nums[index];
//                --index;
//            }
//            nums[index + 1] = tem;
//        }
    }

    /**
     * 希尔排序
     * 其实就是对插入排序的优化，设置希增量gap = length/2，不断收敛至1后进行最后一次排序
     *
     * @param nums
     */
    public void shellSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int gap = length >> 1, tem;
        while (gap > 0) {
            for (int i = gap; i < length; ++i) {
                tem = nums[i];
                int index = i - gap;
                while (index >= 0 && nums[index] > tem) {
                    nums[index + gap] = nums[index];
                    index -= gap;
                }
                nums[index + gap] = tem;
            }
            gap >>= 1;
        }
    }

    /**
     * 归并排序
     * 算法：分治法（Divide and Conquer）分成n/2个分组进行排序，然后合并，所以关键是如何快速合并两个有序的数组
     *
     * @param nums
     */
    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        // 优化一下length=2的时候只要简单判断交换一下就好了
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                swap(nums, 0, 1);
            }
            return nums;
        }
        int mid = nums.length >> 1;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        return mergeArrs(mergeSort(left), mergeSort(right));
    }

    private int[] mergeArrs(int[] left, int[] right) {
        int total = left.length + right.length;
        int[] arrNew = new int[total];
        for (int n1 = 0, n2 = 0; n1 + n2 < total; ) {
            if (n1 < left.length && (n2 >= right.length || left[n1] < right[n2])) {
                arrNew[n1 + n2] = left[n1++];
            } else {
                arrNew[n1 + n2] = right[n2++];
            }
        }
        return arrNew;
    }

    /**
     * 快速排序 start,end是为了递归才加的形参
     *
     * @param nums
     * @param start 包含
     * @param end   包含
     */
    public void quickSort(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0
                || start >= end || end >= nums.length || start < 0) {
            return;
        }
        int smallIndex = quickSortCore(nums, start, end);
        if (smallIndex - 1 > start) {
            quickSort(nums, start, smallIndex - 1);
        }
        if (smallIndex + 1 < end){
            quickSort(nums, smallIndex + 1, end);
        }
    }

    /**
     * 快速排序的核心算法
     *
     * @param nums
     * @param start
     * @param end
     */
    public int quickSortCore(int[] nums, int start, int end) {
        // 随机取[start, end]之间的一个数作为基准
        int q = MathUtils.getRandom(start, end);
        // 把这个基准和给定的数组范围的最后一个调换，最后会把这个基准数放到分割好的两堆数中间
        swap(nums, q, end);
        // 记录已经遍历过的小于等于num[q]的最后一个数的下标，方便元素交换
        int smallIndex = start - 1;
        // 循环数组，小于等于num[q]的放到前面，大于num[q]的放到后面
        for (int i = start; i <= end; ++i) {
            if (nums[i] <= nums[end] && i > ++smallIndex) {
                swap(nums, smallIndex, i);
            }
        }
        return smallIndex;
    }


    public static void swap(int[] nums, int target, int source) {
        if (target == source) {
            return;
        }
        nums[target] ^= nums[source];
        nums[source] ^= nums[target];
        nums[target] ^= nums[source];
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 222, 1, 2, 22, 11, 10};
        ArraySort sort = new ArraySort();
//        sort.selection(nums);
//        sort.bubbling(nums);
//        sort.insertSort(nums);
//        sort.shellSort(nums);
//        nums = sort.mergeSort(nums);
        sort.quickSort(nums, 0, 3);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
