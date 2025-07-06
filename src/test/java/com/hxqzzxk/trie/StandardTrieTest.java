package com.hxqzzxk.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Trie 前缀树单元测试类
 * 用于验证 Trie 结构的基本功能是否正确实现，包括添加、获取、删除、前缀匹配等操作。
 */
public class StandardTrieTest {

    /**
     * Trie 树实例，用于存储字符串键值对
     */
    private StandardTrie<String> trie;

    /**
     * 每个测试用例执行前初始化一个新的 Trie 实例
     */
    @Before
    public void setUp() {
        trie = new StandardTrie<>();
    }

    /**
     * 测试 Trie 的添加方法
     * 验证添加元素后，Trie 树的大小是否正确以及是否包含指定键
     */
    @Test
    public void testAdd() {
        trie.add("hello", "world");
        Assert.assertEquals(1, trie.size());
        Assert.assertTrue(trie.contains("hello"));
    }

    /**
     * 测试 Trie 的获取方法
     * 验证通过键可以正确获取对应的值，并验证不存在的键返回 null
     */
    @Test
    public void testGet() {
        trie.add("hello", "world");
        Assert.assertEquals("world", trie.get("hello"));
        Assert.assertNull(trie.get("hi"));
    }

    /**
     * 测试 Trie 的 contains 方法
     * 验证添加元素后是否能够正确识别存在或不存在的键
     */
    @Test
    public void testContains() {
        trie.add("hello", "world");
        Assert.assertTrue(trie.contains("hello"));
        Assert.assertFalse(trie.contains("hi"));
    }

    /**
     * 测试 Trie 的 remove 方法
     * 验证删除键后，该键不再存在于 Trie 中且大小更新正确
     */
    @Test
    public void testRemove() {
        trie.add("hello", "world");
        Assert.assertEquals("world", trie.remove("hello"));
        Assert.assertFalse(trie.contains("hello"));
        Assert.assertEquals(0, trie.size());
    }

    /**
     * 测试 Trie 的 startsWith 方法
     * 验证给定前缀是否能够正确判断是否存在匹配的路径
     */
    @Test
    public void testStartsWith() {
        trie.add("hello", "world");
        Assert.assertTrue(trie.startsWith("hel"));
        Assert.assertTrue(trie.startsWith("hello"));
        Assert.assertFalse(trie.startsWith("heo"));
    }

    /**
     * 测试 Trie 的 clear 方法
     * 验证清空 Trie 后所有数据是否被正确移除
     */
    @Test
    public void testClear() {
        Assert.assertEquals(0, trie.size());
        trie.add("hello", "world");
        Assert.assertEquals(1, trie.size());
        trie.clear();
        Assert.assertEquals(0, trie.size());
        Assert.assertFalse(trie.contains("hello"));
    }

    /**
     * 测试 Trie 的 size 方法
     * 验证 Trie 中存储的键值对数量是否准确
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, trie.size());
        trie.add("hello", "world");
        Assert.assertEquals(1, trie.size());
        trie.add("hi", "o");
        Assert.assertEquals(2, trie.size());
    }

    /**
     * 测试 Trie 的 isEmpty 方法
     * 验证 Trie 是否为空的判断逻辑是否正确
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(trie.isEmpty());
        trie.add("hello", "world");
        Assert.assertFalse(trie.isEmpty());
        trie.clear();
        Assert.assertTrue(trie.isEmpty());
    }

    /**
     * 测试添加时对非法键（null 或空字符串）的处理
     * 预期抛出 IllegalArgumentException 异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNullKey() {
        trie.add(null, "world");
        trie.add("", "world");
    }

    /**
     * 测试获取时对非法键（null 或空字符串）的处理
     * 预期抛出 IllegalArgumentException 异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetWithNullKey() {
        trie.get(null);
        trie.get("");
    }

    /**
     * 测试 contains 时对非法键（null 或空字符串）的处理
     * 预期抛出 IllegalArgumentException 异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContainsWithNullKey() {
        trie.contains(null);
        trie.contains("");
    }

    /**
     * 测试 remove 时对非法键（null 或空字符串）的处理
     * 预期抛出 IllegalArgumentException 异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullKey() {
        trie.remove(null);
        trie.remove("");
    }

    /**
     * 测试 startsWith 时对非法键（null）的处理
     * 预期抛出 IllegalArgumentException 异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testStartsWithWithNullKey() {
        trie.startsWith(null);
    }
}
