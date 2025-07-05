package com.hxqzzxk.map;

/**
 * TreeMap 测试类，继承自 MapTest
 * 用于验证 TreeMap 实现类的基本功能和正确性
 */
public class TreeMapTest extends MapTest {
    /**
     * 创建 TreeMap 实例
     */
    @Override
    public void setUp() {
        map = new TreeMap<>();
    }
}
