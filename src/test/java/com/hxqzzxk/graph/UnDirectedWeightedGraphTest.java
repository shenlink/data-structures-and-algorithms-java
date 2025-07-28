package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向带权图功能测试类
 * 测试无向带权图的基本功能和特性
 */
public class UnDirectedWeightedGraphTest {
    /**
     * 边的数组，每个元素包含三个值：顶点对和权重
     * 分别表示图中的边及其权重
     */
    private final int[][] pairs = {
            {0, 1, 2}, {0, 3, 7},
            {0, 5, 2}, {1, 2, 1},
            {1, 3, 4}, {1, 4, 3},
            {1, 5, 5}, {2, 4, 4},
            {2, 5, 4}, {3, 4, 1},
            {3, 6, 5}, {4, 6, 7}
    };

    /**
     * 创建一个无向带权图实例
     * 使用pairs数组初始化图结构
     */
    private UnDirectedWeightedGraph graph = new UnDirectedWeightedGraph(pairs);

    /**
     * 测试获取边权重的功能
     * 验证图中所有边的权重是否与输入一致
     */
    @Test
    public void testGetWeight() {
        for (int[] pair : pairs) {
            Assert.assertEquals(pair[2], graph.getWeight(pair[0], pair[1]));
        }
    }

    /**
     * 测试获取顶点数量的功能
     * 验证图中顶点数量是否正确（应为7个顶点）
     */
    @Test
    public void testGetVertex() {
        Assert.assertEquals(7, graph.getVertexes());
    }

    /**
     * 测试获取边数量的功能
     * 验证图中边的数量是否正确（应为12条边）
     */
    @Test
    public void testGetEdges() {
        Assert.assertEquals(12, graph.getEdges());
    }

    /**
     * 测试判断边是否存在功能
     * 验证图中所有已定义的边是否都存在
     */
    @Test
    public void testHasEdge() {
        for (int[] pair : pairs) {
            Assert.assertTrue(graph.hasEdge(pair[0], pair[1]));
        }
    }

    /**
     * 测试获取邻接边功能
     * 验证每个顶点的邻接边是否正确
     */
    @Test
    public void testEdges() {
        Assert.assertEquals("[1, 3, 5]",
                graph.edges(0).toString());
        Assert.assertEquals("[0, 2, 3, 4, 5]",
                graph.edges(1).toString());
        Assert.assertEquals("[1, 4, 5]",
                graph.edges(2).toString());
        Assert.assertEquals("[0, 1, 4, 6]",
                graph.edges(3).toString());
        Assert.assertEquals("[1, 2, 3, 6]",
                graph.edges(4).toString());
        Assert.assertEquals("[0, 1, 2]",
                graph.edges(5).toString());
        Assert.assertEquals("[3, 4]", graph.edges(6).toString());
    }

    /**
     * 测试计算顶点度数的功能
     * 验证每个顶点的度数是否正确
     */
    @Test
    public void testDegree() {
        Assert.assertEquals(3, graph.degree(0));
        Assert.assertEquals(5, graph.degree(1));
        Assert.assertEquals(3, graph.degree(2));
        Assert.assertEquals(4, graph.degree(3));
        Assert.assertEquals(4, graph.degree(4));
        Assert.assertEquals(3, graph.degree(5));
        Assert.assertEquals(2, graph.degree(6));
    }

    /**
     * 测试验证顶点有效性时异常处理
     * 当尝试访问不存在的顶点时应该抛出异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidateVertex() {
        graph.validateVertex(8);
    }

    /**
     * 测试自环边处理时异常处理
     * 当尝试创建自环边时应该抛出异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelfLoopThrowsException() {
        int[][] selfLoopEdges = {{0, 0, 10}};
        new UnDirectedWeightedGraph(1, 1, selfLoopEdges);
    }

    /**
     * 测试平行边处理时异常处理
     * 当尝试创建平行边时应该抛出异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParallelEdgeThrowsException() {
        int[][] parallelEdges = {
                {0, 1, 5},
                {0, 1, 6}
        };
        new UnDirectedWeightedGraph(parallelEdges);
    }

    /**
     * 测试字符串表示方法
     * 验证图的字符串表示是否符合预期格式
     */
    @Test
    public void testToString() {
        String expected = "顶点数: 7, 边数: 12\n" +
                "0: (1: 2) (3: 7) (5: 2) \n" +
                "1: (0: 2) (2: 1) (3: 4) (4: 3) (5: 5) \n" +
                "2: (1: 1) (4: 4) (5: 4) \n" +
                "3: (0: 7) (1: 4) (4: 1) (6: 5) \n" +
                "4: (1: 3) (2: 4) (3: 1) (6: 7) \n" +
                "5: (0: 2) (1: 5) (2: 4) \n" +
                "6: (3: 5) (4: 7) \n";
        Assert.assertEquals(expected, graph.toString());
    }
}
