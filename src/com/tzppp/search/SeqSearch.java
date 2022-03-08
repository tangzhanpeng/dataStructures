package com.tzppp.search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {92,23,2,1,2,55,2,22};
        int index = seqSearch(arr, 22);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为="+index);
        }

    }

    /**
     * 返回下标
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一比对，发现有相同值，则停下
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}