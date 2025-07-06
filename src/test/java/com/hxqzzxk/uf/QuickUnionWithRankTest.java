package com.hxqzzxk.uf;

/**
 * QuickUnionWithRank 测试类
 */
public class QuickUnionWithRankTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnionWithRank 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnionWithRank(capacity);
    }
}
