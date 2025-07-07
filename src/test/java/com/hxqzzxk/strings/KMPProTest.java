package com.hxqzzxk.strings;

/**
 * KMP 优化算法测试类
 * 用于验证 KMPBasic 算法的正确性
 */
public class KMPProTest extends AbstractMatchTest {
    /**
     * 初始化 KMPBasic 实例
     */
    @Override
    public void setUp() {
        match = new KMPPro();
    }
}
