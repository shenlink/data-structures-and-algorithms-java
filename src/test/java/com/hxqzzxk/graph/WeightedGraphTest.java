package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 带权图测试类
 */
public class WeightedGraphTest extends IWeightedGraphTest {
    /**
     * 设置带权图实例
     */
    @Override
    public void setUp() {
        graph = new WeightedGraph(pairs);
    }

    /**
     * 测试获取权重方法
     */
    @Test
    public void testGetWeight() {
        IWeightedGraph graph = new WeightedGraph(pairs);
        for (int[] pair : pairs) {
            Assert.assertEquals(pair[2], graph.getWeight(pair[0], pair[1]));
        }
    }
}
