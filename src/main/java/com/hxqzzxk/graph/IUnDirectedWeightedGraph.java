package com.hxqzzxk.graph;

/**
 * 无向带权图的基本接口
 * 表示一个无向且边有权重的图结构。该接口定义了基本操作，
 * 如添加带权重的边、获取邻接顶点以及边的权重等。
 */
public interface IUnDirectedWeightedGraph extends IUnDirectedGraph,
        IWeightedGraph {
    /**
     * 获取指定顶点的度数（即与该顶点相连的边的数量）
     *
     * @param vertex 图中的顶点编号
     * @return 该顶点的度数
     */
    int degree(int vertex);
}
