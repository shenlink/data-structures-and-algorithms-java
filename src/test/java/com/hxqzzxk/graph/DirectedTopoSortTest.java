package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基于入度统计的拓扑排序测试类。
 */
public class DirectedTopoSortTest {
    /**
     * 图的边集合。
     */
    private final int[][] pairs = {
            {0, 1}, {1, 2}, {1, 3}, {2, 4}, {3, 2}
    };

    /**
     * 有向图实例。
     */
    private final IDirectedGraph graph = new DirectedGraph(pairs);

    /**
     * 拓扑排序实例。
     */
    private final DirectedTopoSort topoSort = new DirectedTopoSort(graph);

    /**
     * 测试图是否包含环。
     */
    @Test
    public void testHasCycle() {
        Assert.assertFalse(topoSort.hasCycle());
    }

    /**
     * 测试拓扑排序结果是否正确。
     */
    @Test
    public void testResult() {
        Assert.assertEquals("[0, 1, 3, 2, 4]", topoSort.result().toString());
    }
}
