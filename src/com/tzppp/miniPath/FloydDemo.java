package com.tzppp.miniPath;

/**
 * 最短路径
 * 动态规划
 */
public class FloydDemo {
    /*
     * 参数adjMatrix：给定连通图的权重矩阵，其中权重为-1表示两个顶点不能直接相连
     * 函数功能：返回所有顶点之间的最短距离权重矩阵
     */
    public void getShortestPaths(int[][] adjMatrix) {
        for(int k = 0;k < adjMatrix.length;k++) {
            for(int i = 0;i < adjMatrix.length;i++) {
                for(int j = 0;j < adjMatrix.length;j++) {
                    if(adjMatrix[i][k] != -1 && adjMatrix[k][j] != -1) {
                        int temp = adjMatrix[i][k] + adjMatrix[k][j];  //含有中间节点k的顶点i到顶点j的距离
                        if(adjMatrix[i][j] == -1 || adjMatrix[i][j] > temp)
                            adjMatrix[i][j] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FloydDemo test = new FloydDemo();
        int[][] adjMatrix = {{0,-1,3,-1},
                {2,0,-1,-1},
                {-1,7,0,1},
                {6,-1,-1,0}};
        test.getShortestPaths(adjMatrix);
        System.out.println("使用Floyd算法得到的所有顶点之间的最短距离权重矩阵为：");
        for(int i = 0;i < adjMatrix.length;i++) {
            for(int j = 0;j < adjMatrix[0].length;j++)
                System.out.print(adjMatrix[i][j]+" ");
            System.out.println();
        }
    }
}