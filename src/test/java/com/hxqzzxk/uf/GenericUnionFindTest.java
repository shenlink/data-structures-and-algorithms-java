package com.hxqzzxk.uf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * GenericUnionFindTest类用于测试GenericUnionFind类的功能。
 * 该类实现了并查集的基本操作，包括创建集合、合并集合以及判断元素是否连通。
 */
public class GenericUnionFindTest {
    /**
     * GenericUnionFind实例，用于测试并查集的相关操作。
     */
    private GenericUnionFind<Integer> uf;

    /**
     * 在每个测试方法执行前初始化一个新的GenericUnionFind实例。
     */
    @Before
    public void setUp() {
        uf = new GenericUnionFind<>();
    }

    /**
     * 测试find方法的功能。
     * 验证不同元素在多次合并操作后是否具有正确的根节点。
     */
    @Test
    public void testFind() {
        for (int i = 0; i < 11; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);
        uf.union(6, 7);
        uf.union(8, 10);
        uf.union(9, 10);
        Assert.assertFalse(uf.isConnected(2, 7));
        uf.union(4, 6);
        Assert.assertTrue(uf.isConnected(2, 7));
    }

    /**
     * 测试union方法的正确性。
     * 验证多个元素经过合并操作后是否能够正确地连接在一起。
     */
    @Test
    public void testUnion() {
        for (int i = 0; i < 11; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);
        uf.union(6, 7);
        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);
        Assert.assertFalse(uf.isConnected(2, 7));
        uf.union(4, 6);
        Assert.assertTrue(uf.isConnected(2, 7));
    }

    /**
     * 测试isConnected方法是否能正确判断两个元素是否属于同一个集合。
     */
    @Test
    public void testIsConnected() {
        for (int i = 0; i < 11; i++) {
            uf.makeSet(i);
        }

        uf.union(0, 1);
        uf.union(0, 3);
        uf.union(0, 4);
        uf.union(2, 3);
        uf.union(2, 5);
        uf.union(6, 7);
        uf.union(8, 10);
        uf.union(9, 10);
        uf.union(9, 11);
        Assert.assertFalse(uf.isConnected(2, 7));
        uf.union(4, 6);
        Assert.assertTrue(uf.isConnected(2, 7));
    }
}