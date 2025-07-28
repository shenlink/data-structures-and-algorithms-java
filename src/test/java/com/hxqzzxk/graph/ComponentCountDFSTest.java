package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试无向图的连通分量计数功能，使用 DFS 算法
 */
public class ComponentCountDFSTest {
    /**
     * 测试用无向图实例
     * 包含6条边和7个顶点的测试图
     */
    private IUnDirectedGraph graph = new UnDirectedGraph(7, 6, new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    });

    /**
     * 连通分量计数器实例
     * 基于深度优先搜索算法实现
     */
    private ComponentCountDFS cc = new ComponentCountDFS(graph);

    /**
     * 测试连通分量计数功能
     * 预期结果：图中应有2个连通分量
     */
    @Test
    public void testCount() {
        Assert.assertEquals(2, cc.count());
    }
}
