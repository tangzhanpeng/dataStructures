package com.tzppp.tree.unionCollection;

/**
 * 并查集
 * https://www.cnblogs.com/noKing/p/8018609.html
 * 数组里存的数字代表所属的集合。比如arr[4]==1;代表4是第一组。
 * 如果arr[7]==1,代表7也是第一组。既然 arr[4] == arr[7] == 1 ，那么说明4 和 7同属于一个集合，
 */
public class UnionFindDemo {

    public static void main(String[] args) {

    }
}

/**
 * 慢union,快find
 */
class UnionFind {
    private int[] arr;
    private int size;

    public UnionFind(int size) {
        this.size = size;
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    public int find(int index) {
        return arr[index];
    }

    public boolean isConnected(int index1, int index2) {
        return find(index1) == find(index2);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion == secondUnion) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == firstElement) {
                arr[i] = secondUnion;
            }
        }
    }
}

/**
 * 快速Union，慢Find
 */
class UnionFind2 {
    private int[] parent;
    private int size;

    public UnionFind2(int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int element) {
        while (parent[element] != element) {
            element = parent[element];
        }
        return parent[element];
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion != secondUnion) {
            parent[firstUnion] = secondUnion;
        }
    }
}

/**
 * 快速union，快速find，基于重量
 */
class UnionFind3 {
    private int[] parent;
    private int[] weight;
    private int size;

    public UnionFind3(int size) {
        this.size = size;
        parent = new int[size];
        weight = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int element) {
        while (parent[element] != element) {
            element = parent[element];
        }
        return parent[element];
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion == secondUnion) {
            return;
        }
        if (weight[firstUnion] > weight[secondUnion]) {
            parent[secondUnion] = firstUnion;
            weight[firstUnion] += weight[secondUnion];
        } else {
            parent[firstUnion] = secondUnion;
            weight[secondUnion] += weight[firstUnion];
        }
    }
}

/**
 * 快速union，快速find，基于高度(基于秩)
 */
class UnionFind4 {
    private int[] parent;
    private int[] height;
    private int size;

    public UnionFind4(int size) {
        this.size = size;
        parent = new int[size];
        height = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    public int find(int element) {
        while (parent[element] != element) {
            element = parent[element];
        }
        return parent[element];
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion == secondUnion) {
            return;
        }
        if (height[firstUnion] > height[secondUnion]) {
            parent[secondUnion] = firstUnion;
        } else if (height[secondUnion] > height[firstUnion]){
            parent[firstUnion] = secondUnion;
        } else {
            parent[firstUnion] = secondUnion;
            height[secondUnion]++;
        }
    }
}

/**
 * 快速union，快速find，基于重量压缩路径
 */
class UnionFind5 {
    private int[] parent;
    private int[] weight;
    private int size;

    public UnionFind5(int size) {
        this.size = size;
        parent = new int[size];
        weight = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int element) {
        while (parent[element] != element) {
            element = parent[element] = parent[parent[element]]; //优化点，深度>1,深度-1
        }
        return parent[element];
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion == secondUnion) {
            return;
        }
        if (weight[firstUnion] > weight[secondUnion]) {
            parent[secondUnion] = firstUnion;
            weight[firstUnion] += weight[secondUnion];
        } else {
            parent[firstUnion] = secondUnion;
            weight[secondUnion] += weight[firstUnion];
        }
    }
}