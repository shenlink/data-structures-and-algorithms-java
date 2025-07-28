package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有向带权图测试类
 */
public class DirectedWeightedGraphTest {
    /**
     * 图的边集合，包含权重信息。
     */
    private final int[][] pairs = {
            {0, 1, 4}, {0, 2, 2}, {1, 2, 1}, {1, 3, 2},
            {1, 4, 3}, {2, 3, 4}, {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 构建的有向带权图实例。
     */
    private final DirectedWeightedGraph graph =
            new DirectedWeightedGraph(pairs);

    /**
     * 测试获取顶点数功能。
     */
    @Test
    public void testGetVertexes() {
        Assert.assertEquals(5, graph.getVertexes());
    }

    /**
     * 测试获取边数功能。
     */
    @Test
    public void testGetEdges() {
        Assert.assertEquals(8, graph.getEdges());
    }

    /**
     * 测试判断两个顶点之间是否存在边的功能。
     */
    @Test
    public void testHasEdge() {
        for (int[] pair : pairs) {
            Assert.assertTrue(graph.hasEdge(pair[0], pair[1]));
        }
    }

    /**
     * 测试获取两个顶点之间的边的权重功能。
     */
    @Test
    public void testGetWeight() {
        for (int[] pair : pairs) {
            Assert.assertEquals(pair[2], graph.getWeight(pair[0], pair[1]));
        }
    }

    /**
     * 测试获取指定顶点的所有出边功能。
     */
    @Test
    public void testEdges() {
        Assert.assertEquals("[1, 2]", graph.edges(0).toString());
        Assert.assertEquals("[2, 3, 4]", graph.edges(1).toString());
        Assert.assertEquals("[3, 4]", graph.edges(2).toString());
        Assert.assertEquals("[4]", graph.edges(3).toString());
        Assert.assertEquals("[]", graph.edges(4).toString());
    }

    /**
     * 测试获取指定顶点的入度功能。
     */
    @Test
    public void testGetInDegree() {
        Assert.assertEquals(0, graph.getInDegree(0));
        Assert.assertEquals(1, graph.getInDegree(1));
        Assert.assertEquals(2, graph.getInDegree(2));
        Assert.assertEquals(2, graph.getInDegree(3));
        Assert.assertEquals(3, graph.getInDegree(4));
    }

    /**
     * 测试获取指定顶点的出度功能。
     */
    @Test
    public void testGetOutDegree() {
        Assert.assertEquals(2, graph.getOutDegree(0));
        Assert.assertEquals(3, graph.getOutDegree(1));
        Assert.assertEquals(2, graph.getOutDegree(2));
        Assert.assertEquals(1, graph.getOutDegree(3));
        Assert.assertEquals(0, graph.getOutDegree(4));
    }

    /**
     * 测试验证顶点是否合法功能。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidateVertex() {
        graph.validateVertex(-1);
    }
}
