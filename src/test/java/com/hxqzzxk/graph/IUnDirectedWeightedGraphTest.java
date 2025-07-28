package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向带权图测试基类
 */
public class IUnDirectedWeightedGraphTest extends AbstractGraphTest {
    /**
     * 边数据集合
     */
    protected int[][] pairs = {
            {0, 1, 2}, {0, 3, 7}, {0, 5, 2},
            {1, 2, 1}, {1, 3, 4}, {1, 4, 3},
            {1, 5, 5}, {2, 4, 4}, {2, 5, 4},
            {3, 4, 1}, {3, 6, 5}, {4, 6, 7},
    };

    /**
     * 设置无向带权图实例
     */
    @Override
    public void setUp() {
        graph = new UnDirectedWeightedGraph(pairs);
    }

    /**
     * 测试获取边数量功能
     * 预期结果：返回预设的9条边
     */
    @Test
    public void testGetEdges() {
        Assert.assertEquals(12, graph.getEdges());
    }

    /**
     * 测试边存在性检测功能
     * 预期结果：验证所有预设边的存在
     */
    @Test
    public void testHasEdge() {
        for (int[] pair : pairs) {
            Assert.assertTrue(graph.hasEdge(pair[0], pair[1]));
            Assert.assertTrue(graph.hasEdge(pair[1], pair[0]));
        }
    }

    /**
     * 测试获取两个顶点之间的边的权重功能。
     */
    @Test
    public void testGetWeight() {
        IUnDirectedWeightedGraph graph = new UnDirectedWeightedGraph(pairs);
        for (int[] pair : pairs) {
            Assert.assertEquals(pair[2], graph.getWeight(pair[0], pair[1]));
        }
    }

    /**
     * 测试节点度数计算功能
     * 预期结果：验证各节点的度数是否正确
     */
    @Test
    public void testDegree() {
        IUnDirectedUnWeightedGraph graph = new UnDirectedUnWeightedGraph(pairs);
        Assert.assertEquals(3, graph.degree(0));
        Assert.assertEquals(5, graph.degree(1));
        Assert.assertEquals(3, graph.degree(2));
        Assert.assertEquals(4, graph.degree(3));
        Assert.assertEquals(4, graph.degree(4));
        Assert.assertEquals(3, graph.degree(5));
        Assert.assertEquals(2, graph.degree(6));
    }
}
