package com.hxqzzxk.uf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * UnionFindTest 是一个抽象基类，用于测试各种 UnionFind 实现。
 */
public abstract class UnionFindTest {
    /**
     * 测试使用的默认容量
     */
    protected int capacity = 11;

    /**
     * 被测试的 UnionFind 实例
     */
    protected UnionFind uf;

    /**
     * 在每个测试用例执行前初始化 UnionFind 实例。
     * 子类必须实现此方法以提供具体的 UnionFind 实现。
     */
    @Before
    public abstract void setUp();

    /**
     * 测试 find 方法
     */
    @Test
    public void testFind() {
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
     * 测试 union 方法
     */
    @Test
    public void testUnion() {
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
     * 测试 isConnected 方法
     */
    @Test
    public void testIsConnected() {
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
}
