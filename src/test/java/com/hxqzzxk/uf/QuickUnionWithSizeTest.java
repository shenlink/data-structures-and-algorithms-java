package com.hxqzzxk.uf;

/**
 * QuickUnionWithSize 测试类
 */
public class QuickUnionWithSizeTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnionWithSize 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnionWithSize(capacity);
    }
}
