package com.hxqzzxk.graph;

/**
 * 最小生成树接口，定义获取最小生成树的方法
 */
public interface IMinimumSpanningTree {
    /**
     * 获取最小生成树的边集合
     *
     * @return 返回包含最小生成树边的可迭代对象
     */
    Iterable<UnDirectedWeightedEdge> mst();
}
