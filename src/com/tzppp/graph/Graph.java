package com.tzppp.graph;

import java.util.List;

 interface Graph<V> {
     int getSize();   //返回这个图的结点个数
     List<V> getVertices();   //返回图的结点
     V getVertex(int index);  //返回下标index 对应的结点
     int getIndex(V v);   //返回结点v对应的下标
     List<Integer> getNeighbors(int index);//返回下标index对应的结点的相邻结点
     int getDegree(int v);  //返回结点v的度
     int[][] getAdjacencyMatrix();    //返回邻接距阵
     void printAdjacencyMatrix();     //打印邻接距阵
     void printEdges();   //打印所有的邻接表
     AbstractGraph<V>.Tree dfs(int v);    //以结点v为根，深度优先搜索生成树
     AbstractGraph<V>.Tree bfs(int v);    //以结点v为根，宽度优先搜索生成树
}
