package com.hxqzzxk.uf;

/**
 * QuickUnionWithPathHalf 测试类
 */
public class QuickUnionWithPathHalfTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnionWithPathHalf 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnionWithPathHalf(capacity);
    }
}
