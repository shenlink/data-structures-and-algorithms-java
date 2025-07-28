package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 无向无权图通用测试基类
 * 包含针对无向无权图的基本操作测试
 */
public abstract class IUnDirectedUnWeightedGraphTest extends AbstractGraphTest {
    /**
     * 边数据集合
     */
    protected int[][] pairs = {
            { 0, 1 }, { 0, 3 }, { 1, 2 },
            { 1, 6 }, { 2, 3 }, { 2, 5 },
            { 3, 4 }, { 4, 5 }, { 5, 6 },
    };

    /**
     * 测试前的初始化方法
     * 子类必须实现此方法以设置具体的图实现
     */
    @Before
    public abstract void setUp();

    /**
     * 测试节点度数计算功能
     * 预期结果：验证各节点的度数是否正确
     */
    @Test
    public void testDegree() {
        IUnDirectedUnWeightedGraph unDirectedUnWeightedGraph = (IUnDirectedUnWeightedGraph) graph;
        Assert.assertEquals(2, unDirectedUnWeightedGraph.degree(0));
        Assert.assertEquals(3, unDirectedUnWeightedGraph.degree(1));
        Assert.assertEquals(3, unDirectedUnWeightedGraph.degree(2));
        Assert.assertEquals(3, unDirectedUnWeightedGraph.degree(3));
        Assert.assertEquals(2, unDirectedUnWeightedGraph.degree(4));
        Assert.assertEquals(3, unDirectedUnWeightedGraph.degree(5));
        Assert.assertEquals(2, unDirectedUnWeightedGraph.degree(6));
    }
}
