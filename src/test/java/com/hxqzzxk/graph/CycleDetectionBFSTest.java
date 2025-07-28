package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向图的 BFS 检测环测试类。
 */
public class CycleDetectionBFSTest {
    /**
     * 包含环的图的边集合。
     */
    private int[][] pairs1 = {
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    };

    /**
     * 不包含环的图的边集合。
     */
    private int[][] pairs2 = {
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 6}
    };

    /**
     * 用于进行环检测的对象实例。
     */
    private CycleDetectionBFS CycleDetectionBFS;

    /**
     * 测试图是否包含环。
     * - 使用两种图结构进行验证：一个有环，一个无环。
     */
    @Test
    public void testHasCycle() {
        CycleDetectionBFS =
                new CycleDetectionBFS(new UnDirectedUnWeightedGraph(7, 6,
                        pairs1));
        Assert.assertTrue(CycleDetectionBFS.hasCycle());
        CycleDetectionBFS =
                new CycleDetectionBFS(new UnDirectedUnWeightedGraph(7, 5,
                        pairs2));
        Assert.assertFalse(CycleDetectionBFS.hasCycle());
    }
}
