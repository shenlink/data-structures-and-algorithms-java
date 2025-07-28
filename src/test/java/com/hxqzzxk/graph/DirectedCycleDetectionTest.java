package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有向图环检测测试类。
 */
public class DirectedCycleDetectionTest {
    /**
     * 不包含环的有向图边集合。
     */
    private final int[][] pairs1 = {
            {0, 1}, {1, 2}, {1, 3}, {2, 4}, {3, 2}
    };

    /**
     * 包含环的有向图边集合。
     */
    private final int[][] pairs2 = {
            {0, 1}, {1, 2}, {1, 3}, {2, 4}, {3, 2}, {3, 0}
    };

    /**
     * 第一个有向图实例。
     */
    private final IDirectedGraph graph1 = new DirectedGraph(pairs1);

    /**
     * 第二个有向图实例。
     */
    private final IDirectedGraph graph2 = new DirectedGraph(pairs2);

    /**
     * 第一个环检测器。
     */
    private final DirectedCycleDetection cycleDetection1 =
            new DirectedCycleDetection(graph1);

    /**
     * 第二个环检测器。
     */
    private final DirectedCycleDetection cycleDetection2 =
            new DirectedCycleDetection(graph2);

    /**
     * 测试环检测逻辑。
     * - graph1 应该无环，graph2 应该有环。
     */
    @Test
    public void testHasCycle() {
        Assert.assertFalse(cycleDetection1.hasCycle());
        Assert.assertTrue(cycleDetection2.hasCycle());
    }
}
