package com.hxqzzxk.graph;

/**
 * 带权图的基本接口
 * 定义带权图中必须实现的基本方法，例如获取两个顶点之间边的权重。
 */
public interface IWeightedGraph extends IGraph {
    /**
     * 获取从一个顶点到另一个顶点之间的边的权重
     *
     * @param vertex1 第一个顶点编号
     * @param vertex2 第二个顶点编号
     * @return 边的权重值
     */
    int getWeight(int vertex1, int vertex2);
}
