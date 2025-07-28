package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试无向无权图的基本功能。
 */
public class UnDirectedUnWeightedGraphTest extends IUnDirectedUnWeightedGraphTest {
    /**
     * 设置测试环境，初始化无向图实例。
     */
    @Override
    public void setUp() {
        graph = new UnDirectedUnWeightedGraph(pairs);
    }

    /**
     * 测试图的toString方法是否返回预期的字符串表示。
     */
    @Test
    public void testToString() {
        Assert.assertEquals("顶点数: 7, 边数: 9\n" +
                "0: 1 3 \n" +
                "1: 0 2 6 \n" +
                "2: 1 3 5 \n" +
                "3: 0 2 4 \n" +
                "4: 3 5 \n" +
                "5: 2 4 6 \n" +
                "6: 1 5 \n", graph.toString());
    }
}
