package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向图的 BFS 二分图检测测试类。
 */
public class BipartitionDetectionBFSTest {
    /**
     * 包含环但仍是二分图的图的边集合。
     * 测试用例：包含环但仍满足二分图性质的图
     */
    private int[][] pairs1 = {
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    };

    /**
     * 不包含环且不满足二分图性质的图的边集合。
     * 测试用例：包含奇数长度环的图，不能构成二分图
     */
    private int[][] pairs2 = {
            {0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}
    };

    /**
     * 被测试的无向图实例
     */
    private IUnDirectedUnWeightedGraph graph;

    /**
     * 二分图检测器实例，使用BFS实现
     */
    private BipartitionDetectionBFS bipartitionDetection;

    /**
     * 测试图是否是二分图。
     * 预期结果：
     * - 对pairs1中的图应返回true（是二分图）
     * - 对pairs2中的图应返回false（不是二分图）
     */
    @Test
    public void testIsBipartite() {
        graph = new UnDirectedUnWeightedGraph(7, 6, pairs1);
        bipartitionDetection = new BipartitionDetectionBFS(graph);
        Assert.assertTrue(bipartitionDetection.isBipartite());

        graph = new UnDirectedUnWeightedGraph(4, 6, pairs2);
        bipartitionDetection = new BipartitionDetectionBFS(graph);
        Assert.assertFalse(bipartitionDetection.isBipartite());
    }
}
