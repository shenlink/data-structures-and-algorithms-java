package com.hxqzzxk.uf;

/**
 * QuickUnionWithPathCompression 测试类
 */
public class QuickUnionWithPathCompressionTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickUnionWithPathCompression 实例
     */
    @Override
    public void setUp() {
        uf = new QuickUnionWithPathCompression(capacity);
    }
}
