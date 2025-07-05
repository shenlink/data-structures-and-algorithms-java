package com.hxqzzxk.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import com.hxqzzxk.map.Map.Visitor;

/**
 * Map 映射测试基类 - 定义了映射实现的基本测试方法
 * 提供了通用的映射功能测试用例，用于验证不同 Map 实现类的正确性
 */
public abstract class MapTest {
    /**
     * 被测试的映射实例
     */
    protected Map<Integer, String> map;

    /**
     * 初始化映射实例
     */
    @Before
    public abstract void setUp();

    /**
     * 测试清空映射内容的方法
     */
    @Test
    public void testClear() {
        Assert.assertTrue(map.isEmpty());
        map.put(1, "one");
        map.put(2, "two");
        map.clear();
        Assert.assertTrue(map.isEmpty());
        Assert.assertEquals(0, map.size());
    }

    /**
     * 测试获取映射元素数量的方法
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, map.size());
        map.put(1, "one");
        Assert.assertEquals(1, map.size());
        map.put(2, "two");
        Assert.assertEquals(2, map.size());
    }

    /**
     * 测试判断映射是否为空的方法
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(map.isEmpty());
        map.put(1, "one");
        Assert.assertFalse(map.isEmpty());
    }

    /**
     * 测试向映射中添加键值对的方法
     */
    @Test
    public void testPut() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Assert.assertEquals("one", map.get(1));
        Assert.assertEquals("two", map.get(2));
        Assert.assertEquals("three", map.get(3));
        map.put(3, "three");
        Assert.assertEquals("three", map.get(3));
        Assert.assertNull(map.get(4));
    }

    /**
     * 测试从映射中根据键获取值的方法
     */
    @Test
    public void testGet() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Assert.assertEquals("one", map.get(1));
        Assert.assertEquals("two", map.get(2));
        Assert.assertEquals("three", map.get(3));
        map.put(3, "three");
        Assert.assertEquals("three", map.get(3));
        Assert.assertNull(map.get(4));
    }

    /**
     * 测试从映射中根据键删除键值对的方法
     */
    @Test
    public void testRemove() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Assert.assertEquals("one", map.remove(1));
        Assert.assertNull(map.get(1));
        Assert.assertEquals(2, map.size());

        Assert.assertEquals("two", map.remove(2));
        Assert.assertNull(map.get(2));
        Assert.assertEquals(1, map.size());

        Assert.assertEquals("three", map.remove(3));
        Assert.assertNull(map.get(3));
        Assert.assertEquals(0, map.size());
    }

    /**
     * 测试判断映射是否包含指定键的方法
     */
    @Test
    public void testContainsKey() {
        map.put(1, "one");
        map.put(2, "two");

        Assert.assertTrue(map.containsKey(1));
        Assert.assertTrue(map.containsKey(2));
        Assert.assertFalse(map.containsKey(3));
    }

    /**
     * 测试判断映射是否包含指定值的方法
     */
    @Test
    public void testContainsValue() {
        map.put(1, "one");
        map.put(2, "two");

        Assert.assertTrue(map.containsValue("one"));
        Assert.assertTrue(map.containsValue("two"));
        Assert.assertFalse(map.containsValue("three"));
    }

    /**
     * 测试遍历映射内容的方法
     */
    @Test
    public void testTraversal() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        List<String> results = new ArrayList<>();
        map.traversal(new Visitor<Integer, String>() {
            @Override
            public boolean visit(Integer key, String value) {
                results.add(String.format("%d-%s", key, value));
                return false;
            }
        });
        Assert.assertEquals("[1-one, 2-two, 3-three]", results.toString());
    }
}
