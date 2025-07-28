package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试邻接集合表示的无向无权图。
 */
public class AdjacencySetTest extends IUnDirectedUnWeightedGraphTest {
    /**
     * 设置测试用例所需的图结构。
     */
    @Override
    public void setUp() {
        graph = new AdjacencySet(pairs);
    }

    /**
     * 测试 toString 方法，验证图的字符串表示是否正确。
     */
    @Test
    public void testToString() {
        Assert.assertEquals("顶点数：7，边数：9\n" +
                "0: 1 3 \n" +
                "1: 0 2 6 \n" +
                "2: 1 3 5 \n" +
                "3: 0 2 4 \n" +
                "4: 3 5 \n" +
                "5: 2 4 6 \n" +
                "6: 1 5 \n", graph.toString());
    }
}
