package com.hxqzzxk.graph;

/**
 * 带权图测试基类
 */
public abstract class IWeightedGraphTest extends AbstractGraphTest {
    /**
     * 边数据集合
     */
    protected int[][] pairs = {
            {0, 1, 5}, {0, 3, 3}, {1, 2, 4},
            {1, 6, 2}, {2, 3, 6}, {2, 5, 4},
            {3, 4, 3}, {4, 5, 2}, {5, 6, 4},
    };
}
