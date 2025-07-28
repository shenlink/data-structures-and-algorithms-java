package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试 UnDirectedWeightedEdge 类
 */
public class UnDirectedWeightedEdgeTest {
    /**
     * 测试 getFrom 方法
     */
    @Test
    public void testGetFrom() {
        UnDirectedWeightedEdge edge = new UnDirectedWeightedEdge(1, 2, 5);
        Assert.assertEquals(1, edge.getFrom());
    }

    /**
     * 测试 getTo 方法
     */
    @Test
    public void testGetTo() {
        UnDirectedWeightedEdge edge = new UnDirectedWeightedEdge(1, 2, 5);
        Assert.assertEquals(2, edge.getTo());
    }

    /**
     * 测试获取权重方法
     */
    @Test
    public void testGetWeight() {
        UnDirectedWeightedEdge edge = new UnDirectedWeightedEdge(1, 2, 5);
        Assert.assertEquals(5, edge.getWeight());
    }

    /**
     * 测试 compareTo 方法
     */
    @Test
    public void testCompareTo() {
        // 测试边的比较功能
        UnDirectedWeightedEdge edge1 = new UnDirectedWeightedEdge(1, 2, 5);
        UnDirectedWeightedEdge edge2 = new UnDirectedWeightedEdge(3, 4, 10);
        UnDirectedWeightedEdge edge3 = new UnDirectedWeightedEdge(5, 6, 5);
        Assert.assertTrue(edge1.compareTo(edge2) < 0);
        Assert.assertTrue(edge2.compareTo(edge1) > 0);
        Assert.assertEquals(0, edge1.compareTo(edge3));
    }

    /**
     * 测试获取边的字符串表示的方法
     */
    @Test
    public void testToString() {
        UnDirectedWeightedEdge edge = new UnDirectedWeightedEdge(1, 2, 10);
        String expected = "(1-2: 10)";
        Assert.assertEquals(expected, edge.toString());
    }
}
