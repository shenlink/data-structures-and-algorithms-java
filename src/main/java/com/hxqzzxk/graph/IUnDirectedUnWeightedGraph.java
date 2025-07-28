package com.hxqzzxk.graph;

/**
 * 无向无权图的基本接口
 * 表示一个无向且边无权重的图结构。该接口定义了基本操作，
 * 如添加边、获取邻接顶点等，并继承自无向图和无权图的基础接口。
 */
public interface IUnDirectedUnWeightedGraph extends IUnDirectedGraph,
        IUnWeightedGraph {
    /**
     * 获取指定顶点的度数（即与该顶点相连的边的数量）
     *
     * @param vertex 图中的顶点编号
     * @return 该顶点的度数
     */
    int degree(int vertex);
}
