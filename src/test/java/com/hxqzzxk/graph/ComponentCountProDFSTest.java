package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向图连通分量测试类，使用 DFS 算法进行图的遍历和连通性分析
 */
public class ComponentCountProDFSTest {
    /**
     * 测试用例使用的无向图实例
     */
    private IUnDirectedGraph graph = new UnDirectedGraph(7, 6, new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    });

    /**
     * 图连通分量计算类实例
     */
    private ComponentCountProDFS cc =
            new ComponentCountProDFS(graph);

    /**
     * 测试获取连通分量数量的方法
     */
    @Test
    public void testCount() {
        Assert.assertEquals(2, cc.count());
    }

    /**
     * 测试判断两个顶点是否连通的方法
     */
    @Test
    public void testIsConnected() {
        Assert.assertTrue(cc.isConnected(0, 1));
        Assert.assertTrue(cc.isConnected(0, 2));
        Assert.assertTrue(cc.isConnected(1, 3));
        Assert.assertTrue(cc.isConnected(1, 4));
        Assert.assertTrue(cc.isConnected(2, 3));
        Assert.assertTrue(cc.isConnected(2, 6));
    }

    /**
     * 测试获取所有连通分量集合的方法
     */
    @Test
    public void testComponents() {
        Iterable<Integer>[] components = cc.components();
        Assert.assertEquals(2, components.length);
        Assert.assertEquals("[0, 1, 2, 3, 4, 6]", components[0].toString());
        Assert.assertEquals("[5]", components[1].toString());
    }
}
