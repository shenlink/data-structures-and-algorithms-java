package com.hxqzzxk.set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import com.hxqzzxk.set.Set.Visitor;

/**
 * 集合测试类，用于测试不同实现的 Set 接口功能
 * 提供了标准的单元测试方法验证集合的基本操作
 */
public abstract class SetTest {
    /**
     * 集合实例
     */
    protected Set<Integer> set;

    /**
     * 用于创建具体的集合实例
     */
    @Before
    public abstract void setUp();

    /**
     * 测试清空集合功能
     */
    @Test
    public void testClear() {
        Assert.assertTrue(set.isEmpty());
        set.add(1);
        set.add(2);
        set.clear();
        Assert.assertTrue(set.isEmpty());
    }

    /**
     * 测试获取集合大小功能
     */
    @Test
    public void testSize() {
        Assert.assertEquals(0, set.size());
        set.add(1);
        Assert.assertEquals(1, set.size());
        set.add(2);
        Assert.assertEquals(2, set.size());
    }

    /**
     * 测试判断集合是否为空的功能
     */
    @Test
    public void testIsEmpty() {
        Assert.assertTrue(set.isEmpty());
        set.add(1);
        Assert.assertFalse(set.isEmpty());
    }

    /**
     * 测试判断集合是否包含指定元素的功能
     */
    @Test
    public void testContains() {
        Assert.assertFalse(set.contains(1));
        set.add(1);
        Assert.assertTrue(set.contains(1));
    }

    /**
     * 测试向集合添加元素的功能
     * 验证 add() 方法能正确添加新元素，并保证集合特性
     */
    @Test
    public void testAdd() {
        Assert.assertTrue(set.isEmpty());
        Assert.assertFalse(set.contains(1));
        set.add(1);
        Assert.assertTrue(set.contains(1));
        set.add(2);
        Assert.assertTrue(set.contains(2));
        set.add(2);
        Assert.assertTrue(set.contains(2));
        Assert.assertEquals(2, set.size());
    }

    /**
     * 测试从集合中移除元素的功能
     * 验证 remove() 方法能正确删除指定元素
     */
    @Test
    public void testRemove() {
        set.add(1);
        set.add(2);
        set.remove(1);
        Assert.assertFalse(set.contains(1));
        Assert.assertTrue(set.contains(2));
    }

    /**
     * 测试遍历集合元素的功能
     * 验证 traversal() 方法能正确访问所有元素
     */
    @Test
    public void testTraversal() {
        set.add(1);
        set.add(2);
        set.add(3);

        List<Integer> results = new ArrayList<>();
        set.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                results.add(element);
                return false;
            }
        });
        Assert.assertEquals(3, results.size());
        Assert.assertTrue(results.contains(1));
        Assert.assertTrue(results.contains(2));
        Assert.assertTrue(results.contains(3));
    }
}