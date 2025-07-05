package com.hxqzzxk.set;

/**
 * TreeSet 测试类，继承自 SetTest
 */
public class TreeSetTest extends SetTest {
    /**
     * 初始化测试所需的集合实例
     */
    @Override
    public void setUp() {
        set = new TreeSet<>();
    }
}
