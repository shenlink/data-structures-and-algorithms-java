package com.hxqzzxk.strings;

/**
 * KMP算法实现测试类
 * 用于验证 KMPBasic 算法的正确性
 */
public class KMPBasicTest extends AbstractMatchTest {
    /**
     * 初始化 KMPBasic 实例
     */
    @Override
    public void setUp() {
        match = new KMPBasic();
    }
}
