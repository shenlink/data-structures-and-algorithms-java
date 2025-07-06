package com.hxqzzxk.uf;

/**
 * 测试类 QuickFind 的单元测试类
 * 用于验证 QuickFind 实现的并查集（Union-Find）结构的功能正确性
 */
public class QuickFindTest extends UnionFindTest {
    /**
     * 初始化一个新的 QuickFind 实例
     */
    @Override
    public void setUp() {
        uf = new QuickFind(capacity);
    }
}
