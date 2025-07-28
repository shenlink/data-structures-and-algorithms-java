package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 无向带权图的 Kruskal 算法测试类
 */
public class KruskalTest extends IMinimumSpanningTreeTest {
    /**
     * 设置最小生成树实例
     */
    @Override
    public void setUp() {
        mst = new Kruskal(graph);
    }

    /**
     * 测试最小生成树算法的正确性
     * 验证生成树的边数和具体边的组合是否符合预期
     */
    @Test
    public void testMst() {
        // 验证生成树的具体边和权重是否符合预期
        Assert.assertEquals("[(1-2: 1), (3-4: 1), (0-1: 2), (0-5: 2), (1-4: " +
                "3), (3-6: 5)]", mst.mst().toString());
    }
}
