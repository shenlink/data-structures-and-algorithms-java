package com.hxqzzxk.uf;

/**
 * QuickUnionWithPathSplit 测试类
 */
public class QuickUnionWithPathSplitTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnionWithPathSplit 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnionWithPathSplit(capacity);
    }
}
