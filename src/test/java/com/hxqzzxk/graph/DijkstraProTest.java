package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 带权图的 Dijkstra 最短路径算法优化实现的测试类。
 */
public class DijkstraProTest {
    /**
     * 图的边集合，包含权重信息。
     */
    private final int[][] pairs = {
            {0, 1, 4}, {0, 2, 2}, {1, 2, 1}, {1, 3, 2},
            {1, 4, 3}, {2, 3, 4}, {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 构建的无向带权图实例。
     */
    private final IWeightedGraph graph = new UnDirectedWeightedGraph(pairs);

    /**
     * Dijkstra 算法处理对象。
     */
    private DijkstraPro dijkstra;

    /**
     * 测试所有顶点是否可达。
     */
    @Test
    public void testIsConnectedTo() {
        dijkstra = new DijkstraPro(graph, 0);
        for (int i = 0; i < graph.getVertexes(); i++) {
            Assert.assertTrue(dijkstra.isConnectedTo(i));
        }
    }

    /**
     * 测试每个顶点到源点的最短距离。
     */
    @Test
    public void testDistanceTo() {
        dijkstra = new DijkstraPro(graph, 0);
        Assert.assertEquals(0, dijkstra.distanceTo(0));
        Assert.assertEquals(3, dijkstra.distanceTo(1));
        Assert.assertEquals(2, dijkstra.distanceTo(2));
        Assert.assertEquals(5, dijkstra.distanceTo(3));
        Assert.assertEquals(6, dijkstra.distanceTo(4));
    }

    /**
     * 测试最短路径的输出是否正确。
     */
    @Test
    public void testPath() {
        dijkstra = new DijkstraPro(graph, 0);
        Assert.assertEquals("[0]", dijkstra.path(0).toString());
        Assert.assertEquals("[0, 2, 1]", dijkstra.path(1).toString());
        Assert.assertEquals("[0, 2]", dijkstra.path(2).toString());
        Assert.assertEquals("[0, 2, 1, 3]", dijkstra.path(3).toString());
        Assert.assertEquals("[0, 2, 1, 4]", dijkstra.path(4).toString());
    }
}
