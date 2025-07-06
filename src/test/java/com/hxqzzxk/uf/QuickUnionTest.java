package com.hxqzzxk.uf;

/**
 * QuickUnion 测试类
 */
public class QuickUnionTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnion 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnion(capacity);
    }
}
