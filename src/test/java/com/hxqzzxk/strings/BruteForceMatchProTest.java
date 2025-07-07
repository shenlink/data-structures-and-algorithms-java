package com.hxqzzxk.strings;

/**
 * 优化的暴力匹配算法测试类
 * 用于验证 BruteForceMatchPro 算法的正确性
 */
public class BruteForceMatchProTest extends AbstractMatchTest {
    /**
     * 初始化 BruteForceMatchPro 实例
     */
    @Override
    public void setUp() {
        match = new BruteForceMatchPro();
    }
}
