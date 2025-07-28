package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有向图的基本操作测试类。
 */
public class DirectedGraphTest extends AbstractGraphTest {
    /**
     * 创建有向图实例
     */
    public void setUp() {
        graph = new DirectedGraph(pairs);
    }

    /**
     * 测试边存在性检测功能
     * 预期结果：验证所有预设边的存在
     */
    @Test
    public void testHasEdge() {
        for (int[] pair : pairs) {
            Assert.assertTrue(graph.hasEdge(pair[0], pair[1]));
        }
    }

    /**
     * 测试入度计算。
     */
    @Test
    public void testGetInDegree() {
        IDirectedGraph directedGraph = (IDirectedGraph) this.graph;
        Assert.assertEquals(0, directedGraph.getInDegree(0));
        Assert.assertEquals(1, directedGraph.getInDegree(1));
        Assert.assertEquals(1, directedGraph.getInDegree(2));
        Assert.assertEquals(2, directedGraph.getInDegree(3));
        Assert.assertEquals(1, directedGraph.getInDegree(4));
        Assert.assertEquals(2, directedGraph.getInDegree(5));
        Assert.assertEquals(2, directedGraph.getInDegree(6));
    }

    /**
     * 测试出度计算。
     */
    @Test
    public void testGetOutDegree() {
        IDirectedGraph directedGraph = (IDirectedGraph) this.graph;
        Assert.assertEquals(2, directedGraph.getOutDegree(0));
        Assert.assertEquals(2, directedGraph.getOutDegree(1));
        Assert.assertEquals(2, directedGraph.getOutDegree(2));
        Assert.assertEquals(1, directedGraph.getOutDegree(3));
        Assert.assertEquals(1, directedGraph.getOutDegree(4));
        Assert.assertEquals(1, directedGraph.getOutDegree(5));
        Assert.assertEquals(0, directedGraph.getOutDegree(6));
    }
}
