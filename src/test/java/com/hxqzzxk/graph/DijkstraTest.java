package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 带权图的 Dijkstra 最短路径算法的测试类。
 */
public class DijkstraTest {
    /**
     * 图的边数据，表示一个无向带权图
     */
    private final int[][] pairs = {
            {0, 1, 4}, {0, 2, 2}, {1, 2, 1}, {1, 3, 2},
            {1, 4, 3}, {2, 3, 4}, {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 构建的无向带权图实例
     */
    private final IWeightedGraph graph = new UnDirectedWeightedGraph(pairs);

    /**
     * Dijkstra算法实例，用于测试
     */
    private Dijkstra dijkstra;

    /**
     * 测试单源最短路径算法的连通性
     */
    @Test
    public void testIsConnectedTo() {
        dijkstra = new Dijkstra(graph, 0);
        for (int i = 0; i < graph.getVertexes(); i++) {
            Assert.assertTrue(dijkstra.isConnectedTo(i));
        }
    }

    /**
     * 测试Dijkstra算法计算的最短路径距离是否正确
     */
    @Test
    public void testDistanceTo() {
        dijkstra = new Dijkstra(graph, 0);
        Assert.assertEquals(0, dijkstra.distanceTo(0));
        Assert.assertEquals(3, dijkstra.distanceTo(1));
        Assert.assertEquals(2, dijkstra.distanceTo(2));
        Assert.assertEquals(5, dijkstra.distanceTo(3));
        Assert.assertEquals(6, dijkstra.distanceTo(4));
    }
}
