package com.tzppp.miniSpanTree.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * prim算法求最小生成树
 * 贪心算法：每日都是获取最小路径连通
 * 修路问题
 * https://blog.csdn.net/qq_44962429/article/details/103993772
 */
public class PrimDemo1 {
    public static void main(String[] args) {
        String[] data = {"A","B","C","D","E","F","G"};
        Integer[][] edges = {
                {0, 1, 5}, {0, 2, 7}, {0, 6, 2},
                {1, 0, 5}, {1, 3, 9}, {1, 6, 3},
                {2, 0, 7}, {2, 4, 8},
                {3, 1, 9}, {3, 5, 4},
                {4, 2, 8}, {4, 5, 5}, {4, 6, 4},
                {5, 3, 4}, {5, 4, 5}, {5, 6, 6},
                {6, 0, 2}, {6, 1, 3}, {6, 4, 4}, {6, 5, 6}
        };
        GraphRoad graphRoad = new GraphRoad(data, edges);
//        graphRoad.showMatrix();
        graphRoad.prim(0);
    }
}

class GraphRoad {
    private int size; // 顶点个数
    private List<String> vertices; // 顶点列表
    private Integer[][] matrix; // 邻接矩阵
    private List<List<Edge>> neighbors; //邻接表

    public GraphRoad(String[] verticesArray, Integer[][] edges) {
        size = verticesArray.length;
        vertices = Arrays.asList(verticesArray);
        createMatrix(edges, size);
    }

    private void createMatrix(Integer[][] edges, int numOfVertices) {
        neighbors = new ArrayList<>();
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
     * prim算法求得最小连通数
     * @param start 开始顶点
     * @return 返回最短路径之和，-1表示无法覆盖所有顶点
     */
    public void prim(int start) {
        List<Edge> route = new ArrayList<>();
        boolean[] isVisits = new boolean[size];
        isVisits[start] = true;
        int min = 0;
        List<Edge> cEdges = neighbors.get(start);
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(cEdges);
        Edge tEdge;
        while (!edgeQueue.isEmpty()) {
            if ((tEdge = edgeQueue.poll()) != null && !isVisits[tEdge.v]) {
                isVisits[tEdge.v] = true;
                route.add(tEdge);
                min += tEdge.w;
                cEdges = neighbors.get(tEdge.v);
                edgeQueue.addAll(cEdges);
            }
        }
        System.out.println("路程：" + min);
        for (Edge edge : route) {
            System.out.println(vertices.get(edge.u) + "->" + vertices.get(edge.v) + "：" + edge.w);
        }
    }

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
}