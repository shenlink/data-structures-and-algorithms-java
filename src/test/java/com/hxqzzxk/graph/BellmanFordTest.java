package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试无向带权图的 Bellman-Ford 算法实现
 */
public class BellmanFordTest {
    /**
     * 无向带权图1的测试数据，不含负权边
     */
    private final int[][] pairs1 = {
            {0, 1, 4}, {0, 2, 2}, {1, 2, 1},
            {1, 3, 2}, {1, 4, 3}, {2, 3, 4},
            {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 无向带权图2的测试数据，包含一条负权边
     */
    private final int[][] pairs2 = {
            {0, 1, -1}, {0, 2, 2}, {1, 2, 1},
            {1, 3, 2}, {1, 4, 3}, {2, 3, 4},
            {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 使用pairs1构建的无向带权图实例
     */
    private IWeightedGraph graph1 = new UnDirectedWeightedGraph(pairs1);

    /**
     * 使用pairs2构建的无向带权图实例
     */
    private IWeightedGraph graph2 = new UnDirectedWeightedGraph(pairs2);

    /**
     * 针对graph1的Bellman-Ford算法实例
     */
    private BellmanFord bf1 = new BellmanFord(graph1, 0);

    /**
     * 针对graph2的Bellman-Ford算法实例
     */
    private BellmanFord bf2 = new BellmanFord(graph2, 0);

    /**
     * 测试Bellman-Ford算法是否正确检测图中的负环
     */
    @Test
    public void testHasNegativeCycle() {
        Assert.assertFalse(bf1.hasNegativeCycle());
        Assert.assertTrue(bf2.hasNegativeCycle());
    }

    /**
     * 测试Bellman-Ford算法是否能正确识别所有顶点的连通性
     */
    @Test
    public void testIsConnectedTo() {
        for (int i = 0; i < graph1.getVertexes(); i++) {
            Assert.assertTrue(bf1.isConnectedTo(i));
        }
    }

    /**
     * 测试Bellman-Ford算法计算最短路径的距离是否正确，
     * 并验证当存在负环时是否会抛出异常
     */
    @Test(expected = RuntimeException.class)
    public void testDistanceTo() {
        Assert.assertEquals(0, bf1.distanceTo(0));
        Assert.assertEquals(3, bf1.distanceTo(1));
        Assert.assertEquals(2, bf1.distanceTo(2));
        Assert.assertEquals(5, bf1.distanceTo(3));
        Assert.assertEquals(6, bf1.distanceTo(4));
        bf2.distanceTo(0);
    }
}
