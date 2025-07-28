package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试无向带权图的 Bellman-Ford 优化算法实现
 */
public class BellmanFordProTest {
    /**
     * 边数据集合
     * 包含边连接的顶点对及对应的权重值
     */
    private final int[][] pairs = {
            {0, 1, 4}, {0, 2, 2}, {1, 2, 1},
            {1, 3, 2}, {1, 4, 3}, {2, 3, 4},
            {2, 4, 5}, {3, 4, 1}
    };

    /**
     * 测试用带权无向图实例
     * 根据预设边数据构建的测试图
     */
    private IWeightedGraph graph = new UnDirectedWeightedGraph(pairs);

    /**
     * Bellman-Ford算法处理器实例
     * 起始顶点为0的最短路径计算器
     */
    private BellmanFordPro bf = new BellmanFordPro(graph, 0);

    /**
     * 测试检测负权环功能
     * 预期结果：图中不含负权环
     */
    @Test
    public void testHasNegativeCycle() {
        Assert.assertFalse(bf.hasNegativeCycle());
    }

    /**
     * 测试节点连通性检测功能
     * 预期结果：所有节点都与起始点连通
     */
    @Test
    public void testIsConnectedTo() {
        for (int i = 0; i < graph.getVertexes(); i++) {
            Assert.assertTrue(bf.isConnectedTo(i));
        }
    }

    /**
     * 测试最短路径距离计算功能
     * 预期结果：验证各节点到起点的最短距离
     */
    @Test
    public void testDistanceTo() {
        Assert.assertEquals(0, bf.distanceTo(0));
        Assert.assertEquals(3, bf.distanceTo(1));
        Assert.assertEquals(2, bf.distanceTo(2));
        Assert.assertEquals(5, bf.distanceTo(3));
        Assert.assertEquals(6, bf.distanceTo(4));
    }

    /**
     * 测试最短路径轨迹获取功能
     * 预期结果：验证各节点的最短路径构成
     */
    @Test
    public void testPath() {
        Assert.assertEquals("[0]", bf.path(0).toString());
        Assert.assertEquals("[0, 2, 1]", bf.path(1).toString());
        Assert.assertEquals("[0, 2]", bf.path(2).toString());
        Assert.assertEquals("[0, 2, 1, 3]", bf.path(3).toString());
        Assert.assertEquals("[0, 2, 1, 4]", bf.path(4).toString());
    }
}
