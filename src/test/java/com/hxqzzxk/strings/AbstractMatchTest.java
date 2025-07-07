package com.hxqzzxk.strings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 字符串匹配算法测试基类 - 定义了通用的字符串匹配测试框架和验证方法
 */
public abstract class AbstractMatchTest {
    /**
     * 被测试的字符串匹配实现对象
     */
    protected Match match;

    /**
     * 设置具体的字符串匹配实现类
     */
    @Before
    public abstract void setUp();

    /**
     * 测试 indexOf 方法的正确性
     * 包含多种边界情况和常规情况的测试用例
     */
    @Test
    public void testIndexOf() {
        Assert.assertEquals(-1, match.indexOf("", ""));
        Assert.assertEquals(-1, match.indexOf("", "a"));
        Assert.assertEquals(-1, match.indexOf("a", ""));
        Assert.assertEquals(0, match.indexOf("a", "a"));
        Assert.assertEquals(0, match.indexOf("ab", "ab"));
        Assert.assertEquals(0, match.indexOf("abc", "abc"));
        Assert.assertEquals(1, match.indexOf("abc", "bc"));
        Assert.assertEquals(2, match.indexOf("abc", "c"));
    }
}
