package com.tzppp.miniSpanTree.kruskal;

import java.util.*;

public class KruskalDemo {
    public static void main(String[] args) {
        String[] data = {"V0","V1","V2","V3","V4","V5"};
        Integer[][] edges = {
                {0, 1, 4}, {0, 4, 1}, {0, 5, 2},
                {1, 0, 4}, {1, 2, 1}, {1, 5, 3},
                {2, 1, 1}, {2, 3, 6}, {2, 5, 5},
                {3, 2, 6}, {3, 4, 5}, {3, 5, 4},
                {4, 0, 1}, {4, 3, 5}, {4, 5, 3},
                {5, 0, 2}, {5, 1, 3}, {5, 2, 5}, {5, 3, 4}, {5, 4, 3}
        };
        KruskalGraph kruskalGraph = new KruskalGraph(data, edges);
//        kruskalGraph.showMatrix();
        kruskalGraph.kruskal(0);
        Set set = new HashSet();

        List<Integer> list = new ArrayList<>();
        list.add(2);
        Stack<Integer> stack = new Stack<>();
        for (Integer integer : stack) {
            
        }
        stack.push(2);stack.size();
    }

}

class KruskalGraph {
    private int size; // 顶点个数
    private List<String> vertices; // 顶点列表
    private Integer[][] matrix; // 邻接矩阵
    private List<List<Edge>> neighbors; //邻接表
    private List<Edge> edgeList;// 边的列表

    public KruskalGraph(String[] verticesArray, Integer[][] edges) {
        size = verticesArray.length;
        vertices = Arrays.asList(verticesArray);
        createMatrix(edges, size);
    }

    private void createMatrix(Integer[][] edges, int numOfVertices) {
        neighbors = new ArrayList<>();
        edgeList = new ArrayList<>();
        for (int i = 0; i < numOfVertices; i++) {
            neighbors.add(new ArrayList<>());
        }
        matrix = new Integer[numOfVertices][numOfVertices];
        for (Integer[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            matrix[u][v] = w;
            Edge e = new Edge(u, v, w);
            neighbors.get(u).add(e);
            edgeList.add(e);
        }
    }

    /**
     * 打印邻接矩阵
     */
    public void showMatrix() {
        for (Integer[] matrix : matrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    /**
     * kruskal算法求最小连通树
     * @param start 开始顶点
     */
    public void kruskal(int start) {
        UnionFind unionFind = new UnionFind(size);
        List<Edge> selectEdges = new ArrayList<>();
        int all = 0;
        //对边进行排序,使用有序队列
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(edgeList);
        while (!edgeQueue.isEmpty() && selectEdges.size() < size) {
            Edge temEdge = edgeQueue.poll();
            if (temEdge != null && !unionFind.isConnectedThenUnion(temEdge.u, temEdge.v)) {
                selectEdges.add(temEdge);
                all += temEdge.w;
            }
        }
        System.out.println("路程：" + all);
        for (Edge edge : selectEdges) {
            System.out.println(vertices.get(edge.u) + "->" + vertices.get(edge.v) + "：" + edge.w);
        }
    }

    /**
     * 边类
     */
    class Edge implements Comparable<Edge>{
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    /**
     * 并查集
     */
    class UnionFind {
        private int[] parent;
        private int[] height;
        private int size;

        public UnionFind(int size) {
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

        public boolean isConnectedThenUnion(int firstElement, int secondElement) {
            int firstUnion = find(firstElement);
            int secondUnion = find(secondElement);
            if (firstUnion == secondUnion) {
                return true;
            }
            merge(firstUnion, secondUnion);
            return false;
        }

        public void unionElements(int firstElement, int secondElement) {
            int firstUnion = find(firstElement);
            int secondUnion = find(secondElement);
            if (firstUnion == secondUnion) {
                return;
            }
            merge(firstUnion, secondUnion);
        }

        private void merge(int firstUnion, int secondUnion) {
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
}