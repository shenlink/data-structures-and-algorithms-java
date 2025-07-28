package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 图的 DFS 实现测试类
 */
public class GraphDFSRTest {
    /**
     * 图实例
     * 包含6条边和7个顶点的固定测试图
     */
    protected IGraph graph = new UnDirectedUnWeightedGraph(7, 6, new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    });

    /**
     * 图深度优先搜索测试接口
     * 用于执行具体的DFS操作
     */
    protected GraphDFSR graphDFSR = new GraphDFSR(graph);

    /**
     * 测试DFS的访问顺序是否符合预期。
     */
    @Test
    public void testOrder() {
        Assert.assertEquals("[0, 1, 3, 2, 6, 4, 5]",
                graphDFSR.order().toString());
    }

    /**
     * 测试前序遍历结果是否符合预期。
     */
    @Test
    public void testPrev() {
        Assert.assertEquals("[0, 1, 3, 2, 6, 4, 5]",
                graphDFSR.prev().toString());
    }

    /**
     * 测试后序遍历结果是否符合预期。
     */
    @Test
    public void testPost() {
        Assert.assertEquals("[6, 2, 3, 4, 1, 0, 5]",
                graphDFSR.post().toString());
    }
}
