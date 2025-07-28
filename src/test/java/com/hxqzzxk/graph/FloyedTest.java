package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试带权图的 Floyd 算法实现。
 */
public class FloyedTest {
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
    private final IWeightedGraph graph1 = new UnDirectedWeightedGraph(pairs1);

    /**
     * 使用pairs2构建的无向带权图实例
     */
    private final IWeightedGraph graph2 = new UnDirectedWeightedGraph(pairs2);

    /**
     * 针对graph1的Floyd算法实例
     */
    private final Floyed floyed1 = new Floyed(graph1);

    /**
     * 针对graph2的Floyd算法实例
     */
    private final Floyed floyed2 = new Floyed(graph2);

    /**
     * 测试Floyd算法是否正确检测负环
     */
    @Test
    public void testHasNegativeCycle() {
        Assert.assertFalse(floyed1.hasNegativeCycle());
        Assert.assertTrue(floyed2.hasNegativeCycle());
    }

    /**
     * 测试图中任意两点之间的连通性
     */
    @Test
    public void testIsConnectedTo() {
        for (int v = 0; v < graph1.getVertexes(); v++) {
            for (int w = 0; w < graph1.getVertexes(); w++) {
                Assert.assertTrue(floyed1.isConnected(v, w));
            }
        }
    }

    /**
     * 测试Floyd算法计算最短路径的距离是否正确
     */
    @Test
    public void testDistanceTo() {
        int[][] result = {
                {0, 3, 2, 5, 6},
                {3, 0, 1, 2, 3},
                {2, 1, 0, 3, 4},
                {5, 2, 3, 0, 1},
                {6, 3, 4, 1, 0}
        };
        for (int v = 0; v < graph1.getVertexes(); v++) {
            for (int w = 0; w < graph1.getVertexes(); w++) {
                Assert.assertEquals(result[v][w],
                        floyed1.distanceTo(v, w));
            }
        }
    }
}
