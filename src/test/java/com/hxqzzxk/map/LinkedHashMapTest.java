package com.hxqzzxk.map;

/**
 * LinkedHashMap 测试类，继承自 MapTest
 * 用于验证 LinkedHashMap 实现类的基本功能和正确性
 */
public class LinkedHashMapTest extends MapTest {
    /**
     * 初始化 LinkedHashMap 实例
     * 在每次测试方法执行前调用，用于准备测试环境
     */
    @Override
    public void setUp() {
        map = new LinkedHashMap<>();
    }
}
