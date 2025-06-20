package com.hxqzzxk.list;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * 跳跃表测试类
 * 测试跳跃表实现的键值对存储功能
 */
public class SkipListTest {
    /**
     * 测试用的跳跃表实例
     */
    private SkipList<Integer, String> skipList;

    /**
     * 初始化测试用的跳跃表
     */
    @Before
    public void setUp() {
        skipList = new SkipList<>();
    }

    /**
     * 测试清空跳跃表的方法
     */
    @Test
    public void testClear() {
        Assert.assertEquals(0, skipList.size());
        Assert.assertTrue(skipList.isEmpty());
        skipList.put(1, "One");
        Assert.assertEquals(1, skipList.size());
        Assert.assertFalse(skipList.isEmpty());
        skipList.clear();
        Assert.assertEquals(0, skipList.size());
        Assert.assertTrue(skipList.isEmpty());
    }

    /**
     * 测试获取跳跃表大小的方法
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, skipList.size());
        skipList.put(1, "One");
        Assert.assertEquals(1, skipList.size());
    }

    /**
     * 测试判断跳跃表是否为空的方法
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(skipList.isEmpty());
        skipList.put(1, "One");
        Assert.assertFalse(skipList.isEmpty());
    }

    /**
     * 测试获取跳跃表中指定键对应的值的方法
     */
    @Test
    public void testGet() {
        skipList.put(1, "One");
        Assert.assertEquals("One", skipList.get(1));
        Assert.assertNull(skipList.get(99));
    }

    /**
     * 测试向跳跃表中插入键值对的方法
     */
    @Test
    public void testPut() {
        skipList.put(1, "One");
        Assert.assertEquals("One", skipList.get(1));
        Assert.assertEquals("One", skipList.put(1, "Updated One"));
    }

    /**
     * 测试从跳跃表中删除指定键对应的键值对的方法
     */
    @Test
    public void testRemove() {
        skipList.put(1, "One");
        Assert.assertEquals("One", skipList.remove(1));
        Assert.assertNull(skipList.get(1));
        Assert.assertNull(skipList.remove(99));
    }

    /**
     * 测试向跳跃表中插入键为null的键值对
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullKeyPut() {
        skipList.put(null, "Value");
    }

    /**
     * 测试获取跳跃表中键为null的键值对
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullKeyGet() {
        skipList.get(null);
    }

    /**
     * 测试从跳跃表中删除键为null的键值对
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullKeyRemove() {
        skipList.remove(null);
    }

    /**
     * 测试跳跃表中存储多个键值对的情况
     */
    @Test
    public void testMultipleEntries() {
        for (int i = 1; i <= 100; i++) {
            skipList.put(i, "Value" + i);
        }
        for (int i = 1; i <= 100; i++) {
            Assert.assertEquals("Value" + i, skipList.get(i));
        }
        for (int i = 1; i <= 100; i++) {
            String removed = skipList.remove(i);
            Assert.assertEquals("Value" + i, removed);
            Assert.assertNull(skipList.get(i));
        }
        Assert.assertTrue(skipList.isEmpty());
    }
}
