package com.hxqzzxk.graph;

/**
 * 有向图的基本接口
 * 定义了有向图的核心操作，包括获取顶点的入度和出度
 */
public interface IDirectedGraph extends IGraph {
    /**
     * 获取指定顶点的入度
     *
     * @param vertex 顶点编号
     * @return 该顶点的入度值
     */
    int getInDegree(int vertex);

    /**
     * 获取指定顶点的出度
     *
     * @param vertex 顶点编号
     * @return 该顶点的出度值
     */
    int getOutDegree(int vertex);
}
