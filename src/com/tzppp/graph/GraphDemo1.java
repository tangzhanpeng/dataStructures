package com.tzppp.graph;

import java.util.*;

/**
 * https://blog.csdn.net/wind_cp/article/details/82948874 图论
 * 先认为顶点就是String
 */
public class GraphDemo1 {

    private int size; // 顶点个数
    private List<String> vertices; // 顶点列表
    private int[][] adjacencyMatrix; // 邻接矩阵
    private List<List<Integer>> neighbors; // 邻接表

    public GraphDemo1(String[] verticesArray, int[][] edges) {
        size = verticesArray.length;
        vertices = Arrays.asList(verticesArray);
        createAdjacency(edges, size);
    }

    /**
     * 根据边的二维数组初始化图
     *
     * @param edges         边
     * @param numOfVertices 顶点数量
     */
    private void createAdjacency(int[][] edges, int numOfVertices) {
        neighbors = new ArrayList<>();
        for (int i = 0; i < numOfVertices; i++) {
            neighbors.add(new ArrayList<>());
        }
        adjacencyMatrix = new int[numOfVertices][numOfVertices];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            neighbors.get(u).add(v);
            adjacencyMatrix[u][v] = 1;
        }
    }

    /**
     * 打印邻接矩阵
     */
    public void showAdjacencyMatrix() {
        for (int[] matrix : adjacencyMatrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }

    /**
     * 获取顶点
     *
     * @param index 下标
     * @return 顶点
     */
    public String getVertices(int index) {
        return vertices.get(index);
    }

//    /**
//     * 通过邻接表查找下一个邻接顶点
//     * @param v 当前顶点
//     * @param pre 上一个邻接顶点 -1代表开始
//     * @return int -1代表没有找到
//     */
//    private int getNextAdjoin(int v, int pre) {
//        List<Integer> adjoinList = neighbors.get(v);
//        if (adjoinList == null || adjoinList.size() == 0) {
//            return -1;
//        }
//        for (int i = 0; i < adjoinList.size(); i++) {
//            if (adjoinList.get(i) > pre) {
//                return adjoinList.get(i);
//            }
//        }
//        return -1;
//    }


    /**
     * 使用栈进行dfs
     * todo 可以考虑非连通图
     * @param v 起始顶点
     * @return
     */
    public List<String> dfs(int v) {
        List<String> searchVertices = new ArrayList<>();
        boolean[] isVisits = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        isVisits[v] = true;
        searchVertices.add(getVertices(v));
        while (stack.size() > 0) {
            v = stack.peek();
            boolean pop = true;
            for (Integer adjoin : neighbors.get(v)) {
                if (!isVisits[adjoin]) {
                    isVisits[adjoin] = true;
                    stack.push(adjoin);
                    searchVertices.add(getVertices(adjoin));
                    pop = false;
                    break;
                }
            }
            if (pop) {
                stack.pop();
            }
        }
        return searchVertices;
    }

    /**
     * 广度优先遍历
     * todo 可以考虑非连通图
     * @param v 起始顶点
     * @return
     */
    public List<String> bfs(int v) {
        List<String> searchVertices = new ArrayList<>();
        boolean[] isVisits = new boolean[size];
        LinkedList<Integer> queen = new LinkedList<>();
        queen.add(v);
        isVisits[v] = true;
        while (queen.size() > 0) {
            v = queen.pop();
            searchVertices.add(getVertices(v));
            for (Integer adjoin : neighbors.get(v)) {
                if (!isVisits[adjoin]) {
                    queen.add(adjoin);
                    isVisits[adjoin] = true;
                }
            }
        }
        return searchVertices;
    }

    public static class Edge {
        private int u; // 第一个节点下标
        private int v; // 指向节点的下标

        public Edge(int i, int j) {
            this.u = i;
            this.v = j;
        }
    }

    public static void main(String[] args) {
        //测试图创建ok
        String[] verticesArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int[][] edges = {
                {0, 1}, {0, 7},
                {1, 0}, {1, 2}, {1, 4},
                {2, 1}, {2, 3},
                {3, 2}, {3, 4}, {3, 5},
                {4, 1}, {4, 3},
                {5, 3}, {5, 6}, {5, 7},
                {6, 5},
                {7, 0}, {7, 5}, {7, 8},
                {8, 7}
        };
        GraphDemo1 graphDemo1 = new GraphDemo1(verticesArray, edges);
//        graphDemo1.showAdjacencyMatrix();
        List<String> dfs = graphDemo1.dfs(0);
        System.out.println(dfs);
        List<String> bfs = graphDemo1.bfs(0);
        System.out.println(bfs);
    }
}