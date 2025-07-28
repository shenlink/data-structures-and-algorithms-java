package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 图通用测试基类
 * 包含针对图的基本操作测试
 */
public abstract class AbstractGraphTest implements IGraphTest {
    /**
     * 图实例
     * 子类需在setUp方法中初始化
     */
    protected IGraph graph;

    /**
     * 边数据集合
     * 包含9条边，定义了7个顶点的连接关系
     */
    protected int[][] pairs = {
            {0, 1}, {0, 3}, {1, 2},
            {1, 6}, {2, 3}, {2, 5},
            {3, 4}, {4, 5}, {5, 6},
    };

    /**
     * 测试前的初始化方法
     * 子类必须实现此方法以设置具体的图实现
     */
    @Before
    public abstract void setUp();

    /**
     * 测试获取顶点数量功能
     * 预期结果：返回预设的7个顶点
     */
    @Test
    public void testGetVertex() {
        Assert.assertEquals(7, graph.getVertexes());
    }

    /**
     * 测试获取边数量功能
     * 预期结果：返回预设的9条边
     */
    @Test
    public void testGetEdges() {
        Assert.assertEquals(9, graph.getEdges());
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
     * 测试获取邻接边功能
     * 预期结果：验证所有邻接边的正确性
     */
    @Test
    public void testEdges() {
        for (int i = 0; i < graph.getVertexes(); i++) {
            for (Integer j : graph.edges(i)) {
                Assert.assertTrue(graph.hasEdge(i, j));
            }
        }
    }

    /**
     * 测试顶点度数功能
     * 预期结果：验证所有顶点的度数正确
     */
    @Test(expected = IllegalArgumentException.class)
    public void tesValidateVertex() {
        graph.validateVertex(0);
        graph.validateVertex(7);
    }
}
