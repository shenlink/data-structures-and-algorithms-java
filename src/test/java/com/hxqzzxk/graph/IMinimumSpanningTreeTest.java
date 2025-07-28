package com.hxqzzxk.graph;

import org.junit.Before;

/**
 * 最小生成树测试基类
 */
public abstract class IMinimumSpanningTreeTest {
    /**
     * 边的数组，每个元素包含三个值：顶点对和权重
     * 分别表示图中的边及其权重
     */
    protected int[][] pairs = {
            {0, 1, 2}, {0, 3, 7}, {0, 5, 2}, {1, 2, 1},
            {1, 3, 4}, {1, 4, 3}, {1, 5, 5}, {2, 4, 4},
            {2, 5, 4}, {3, 4, 1}, {3, 6, 5}, {4, 6, 7}
    };

    /**
     * 创建一个无向带权图实例
     * 使用pairs数组初始化图结构
     */
    protected IUnDirectedWeightedGraph graph =
            new UnDirectedWeightedGraph(pairs);

    /**
     * 最小生成树实例
     */
    protected IMinimumSpanningTree mst;

    /**
     * 测试前的初始化方法
     * 子类必须实现此方法以设置具体的最小生成树实现
     */
    @Before
    public abstract void setUp();
}
