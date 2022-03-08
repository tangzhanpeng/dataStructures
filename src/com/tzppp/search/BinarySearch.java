package com.tzppp.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15,16,17};
        int index = binarySearch(arr, 4, 0, arr.length - 1);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为=" + index);
        }
        List<Integer> res = binarySearch(arr, 2);
        if (res == null) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为=" + res);
        }
    }

    /**
     * 非递归查找
     * @param arr
     * @param findValue
     * @return
     */
    public static List<Integer> binarySearch(int[] arr, int findValue) {
        int start = 0, end = arr.length - 1;
        int gap;
        List<Integer> res = null;
        while (start <= end && arr[end] >= findValue && arr[start] <= findValue) {
            gap = (start + end) >> 1;// 二分查找

            gap = start + (end - start) * (findValue - arr[start]) / (arr[end] - arr[start]);//插入查找
            int v = arr[gap];
            if (gap == start && gap == end && v != findValue) {
                return null;
            }
            if (v > findValue) {
                end = gap - 1;
            } else if (v < findValue) {
                start = gap + 1;
            } else {
                res = new ArrayList<>();
                res.add(gap);
                int tem = gap - 1;
                while (tem >= start && arr[tem] == v) {
                    res.add(tem--);
                }
                tem = gap + 1;
                while (tem <= end && arr[tem] == v) {
                    res.add(tem++);
                }
                break;
            }
        }
        return res;
    }

    public static int binarySearch(int[] arr, int value, int start, int end) {
//        if (start > end || value < arr[0] || value > arr[arr.length - 1]) {
//            return -1;
//        }
//        int mid = (start + end) >> 1;
//        mid = start + (end - start) * (value - arr[start]) / (arr[end] - arr[start]);//插入查找
//        if (arr[mid] > value) {
//            return binarySearch(arr, value, start, mid - 1);
//        } else if (arr[mid] < value) {
//            return binarySearch(arr, value, mid + 1, end);
//        } else {
//            return mid;
//        }

        if (start <= end && arr[end] >= value && arr[start] <= value) {
            int mid = (start + end) >> 1;
            mid = start + (end - start) * (value - arr[start]) / (arr[end] - arr[start]);//插入查找
            if (arr[mid] > value) {
                return binarySearch(arr, value, start, mid - 1);
            } else if (arr[mid] < value) {
                return binarySearch(arr, value, mid + 1, end);
            } else {
                return mid;
            }
        } else {
            return -1;
        }
    }

    public static List<Integer> binarySearch2(int[] arr, int value, int start, int end) {
        if (start <= end && arr[end] >= value && arr[start] <= value) {
            int mid = (start + end) >> 1;
            mid = start + (end - start) * (value - arr[start]) / (arr[end] - arr[start]);//插入查找
            int v = arr[mid];
            if (v > value) {
                return binarySearch2(arr, value, start, mid - 1);
            } else if (v < value) {
                return binarySearch2(arr, value, mid + 1, end);
            } else {
                List<Integer> result = new ArrayList<>();
                result.add(mid);
                int tem = mid - 1;
                while (tem >= start && arr[tem] == v) {
                    result.add(tem--);
                }
                tem = mid + 1;
                while (tem <= end && arr[tem] == v) {
                    result.add(tem++);
                }
                return result;
            }
        } else {
            return null;
        }
    }
}