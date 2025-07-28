package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试无向图的连通分量计数功能，使用 DFS 算法
 */
public class ComponentCountProBFSTest {
    /**
     * 测试用无向图实例
     * 包含6条边和7个顶点的测试图
     */
    private IUnDirectedUnWeightedGraph graph =
            new UnDirectedUnWeightedGraph(7, 6, new int[][]{
                    {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
            });

    /**
     * 连通分量计数器实例
     * 基于广度优先搜索算法实现
     */
    private ComponentCountProBFS cc =
            new ComponentCountProBFS(graph);

    /**
     * 测试连通分量计数功能
     * 预期结果：图中应有2个连通分量
     */
    @Test
    public void testCount() {
        Assert.assertEquals(2, cc.count());
    }

    /**
     * 测试节点间连通性检测功能
     * 预期结果：验证指定节点对之间是连通的
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
     * 测试获取所有连通分量的功能
     * 预期结果：返回正确的连通分量集合
     */
    @Test
    public void testComponents() {
        Iterable<Integer>[] components = cc.components();
        Assert.assertEquals(2, components.length);
        Assert.assertEquals("[0, 1, 2, 3, 4, 6]", components[0].toString());
        Assert.assertEquals("[5]", components[1].toString());
    }
}
