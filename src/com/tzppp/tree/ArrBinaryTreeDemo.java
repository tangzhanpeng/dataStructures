package com.tzppp.tree;

import java.util.Arrays;

/**
 * 存储二叉树特点（完全二叉树）
 * 1. 假设节点x为第i个节点（i从0开始)
 * 2. x的左子节点 = 2i + 1
 * 3. x的右子节点 = 2i + 2
 * 4. x的父节点为 = （i - 1）/ 2
 * 将二叉树以数组的形式存储，对数组进行前序，中序，后序遍历
 *     1
 *   2    3
 * 4  5  6  7
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("前序遍历结果：");
        preOrder(arr);
        System.out.println("中序遍历结果：");
        infixOrder(arr, arr.length - 1, 0);
        System.out.println("后序遍历结果：");
        postOrder(arr, arr.length - 1, 0);
    }

    public static void preOrder(int[] arr) {
        preOrder(arr, arr.length - 1, 0);
    }

    private static void preOrder(int[] arr, int max, int index) {
        System.out.println(arr[index]);
        int left = getLeft(index);
        if (left <= max) {
            preOrder(arr, max, left);
        }
        int right = getRight(index);
        if (right <= max) {
            preOrder(arr, max, right);
        }
    }

    private static void infixOrder(int[] arr, int max, int index) {
        int left = getLeft(index);
        if (left <= max) {
            infixOrder(arr, max, left);
        }
        System.out.println(arr[index]);
        int right = getRight(index);
        if (right <= max) {
            infixOrder(arr, max, right);
        }
    }

    private static void postOrder(int[] arr, int max, int index) {
        int left = getLeft(index);
        if (left <= max) {
            postOrder(arr, max, left);
        }
        int right = getRight(index);
        if (right <= max) {
            postOrder(arr, max, right);
        }
        System.out.println(arr[index]);
    }

    private static int getLeft(int index) {
        return 2 * index + 1;
    }

    private static int getRight(int index) {
        return 2 * index + 2;
    }

    private static int getFather(int index) {
        return (index - 1) / index;
    }
}