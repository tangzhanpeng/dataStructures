package com.tzppp.tree.HuffManTree;


import java.util.*;

/**
 * 霍夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = getHuffManTree(arr);
        node.preOrder();
    }

    public static Node getHuffManTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Queue<Node> nodeQueue = new PriorityQueue<>();
        for (int anArr : arr) {
            nodeQueue.add(new Node(anArr));
        }

        while (nodeQueue.size() > 1) {
            Node leftNode = nodeQueue.poll();
            Node rightNode = nodeQueue.poll();
            Node parentNode = new Node(leftNode.getVal() + rightNode.getVal());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodeQueue.add(parentNode);
        }
        return nodeQueue.poll();
    }
}

class Node implements Comparable<Node> {
    private int val;
    private Node left;
    private Node right;

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    // 前序
    public void preOrder() {
        System.out.println(this);
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子数前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 后序
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}