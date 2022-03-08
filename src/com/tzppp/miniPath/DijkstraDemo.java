package com.tzppp.miniPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 最短路径
 * https://blog.csdn.net/a1439775520/article/details/96903328
 * Dijkstra算法
 * Dijkstra算法功能：给出加权连通图中一个顶点，称之为起点，找出起点到其它所有顶点之间的最短距离。
 * Dijkstra算法思想：采用贪心法思想，进行n-1次查找（PS:n为加权连通图的顶点总个数，除去起点，则剩下n-1个顶点），
 * 第一次进行查找，找出距离起点最近的一个顶点，标记为已遍历；下一次进行查找时，从未被遍历中的顶点寻找距离起点最近的一个顶点，
 * 标记为已遍历；直到n-1次查找完毕，结束查找，返回最终结果。
 */
public class DijkstraDemo {
    public static void main(String[] args) {
        String[] data = {"A","B","C","D","E","F"};
        Integer[][] edges = {
                {0, 1, 6}, {0, 2, 3},
                {1, 0, 6}, {1, 2, 2}, {1, 3, 5},
                {2, 0, 3}, {2, 1, 2}, {2, 3, 3}, {2, 4, 4},
                {3, 1, 5}, {3, 2, 3}, {3, 4, 2}, {3, 5, 3},
                {4, 2, 4}, {4, 3, 2}, {4, 5, 5},
                {5, 3, 3}, {5, 4, 5}
        };
        GraphDijkstra graph = new GraphDijkstra(data, edges);
//        graph.showMatrix();
        graph.dijkstra(0);
    }
}

class GraphDijkstra {
    private int size; // 顶点个数
    private List<String> vertices; // 顶点列表
    private Integer[][] matrix; // 邻接矩阵
    private List<List<Edge>> neighbors; //邻接表

    public GraphDijkstra(String[] verticesArray, Integer[][] edges) {
        size = verticesArray.length;
        vertices = Arrays.asList(verticesArray);
        createMatrix(edges, size);
    }

    private String getverticeName(int index) {
        return vertices.get(index);
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
     * dijkstra算法
     * @param start 起始点
     */
    public void dijkstra(int start) {
        Integer[] resWeight = new Integer[size]; // 记录start点到其它顶点的最小记录
        List<List<Edge>> resEdge = new ArrayList<>(size); // 记录start点到其它顶点的路径
        boolean[] isVisited = new boolean[size]; // 记录顶点是否访问
        int[] unionFind = new int[size];
        isVisited[start] = true;
        resWeight[start] = 0;
        for (int i = 0; i < size; i++) {
            resEdge.add(new ArrayList<>());
            resWeight[i] = matrix[start][i];
            unionFind[i] = start;
        }
        for (int i = 1; i < size; ++i) {
            int min = Integer.MAX_VALUE;
            int next = -1;
            for (int j = 0; j < size; ++j) {
                if (!isVisited[j] && resWeight[j] != null && resWeight[j] < min) {
                    min = resWeight[j];
                    next = j;
                }
            }
            isVisited[next] = true;
            for (int j = 0; j < size; j++) {
                if (!isVisited[j]) {
                    if (matrix[next][j] != null
                            && (resWeight[j] == null
                            || resWeight[j] > min + matrix[next][j])) {
                        resWeight[j] = min + matrix[next][j];
                        unionFind[j] = next;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(resWeight));
        System.out.println(Arrays.toString(unionFind));
        for (int i = 0; i < unionFind.length; i++) {
            Stack<Integer> stack = new Stack<>();
            stack.add(i);
            int tem = i;
            do {
                stack.add(unionFind[tem]);
                tem = unionFind[tem];
            }while (tem != start);

            int pre = stack.pop();
            System.out.print(getverticeName(pre));
            while (!stack.isEmpty()) {
                int next = stack.pop();
                System.out.printf("->%s(%d)",getverticeName(next),matrix[pre][next]);
                pre = next;
            }
            System.out.println();
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