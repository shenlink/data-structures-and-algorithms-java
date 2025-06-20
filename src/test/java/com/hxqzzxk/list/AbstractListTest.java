package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * 抽象线性表测试类，实现了 ListTest 接口定义的基础测试方法
 */
public abstract class AbstractListTest implements ListTest {
    /**
     * 线性表实例
     */
    protected List<Integer> list;

    /**
     * 创建具体的线性表实例
     */
    public abstract void createList();

    /**
     * 测试清空线性表功能
     */
    @Test
    @Override
    public void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    /**
     * 测试获取线性表大小功能
     */
    @Test
    @Override
    public void testSize() {
        list.add(0);
        list.add(1);
        list.add(2);
        Assert.assertEquals(3, list.size());
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    /**
     * 测试判断线性表是否为空的功能
     */
    @Test
    @Override
    public void testIsEmpty() {
        Assert.assertTrue(list.isEmpty());
        list.add(0);
        Assert.assertFalse(list.isEmpty());
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    /**
     * 测试判断线性表是否包含指定元素的功能
     */
    @Test
    @Override
    public void testContains() {
        list.add(0);
        Assert.assertTrue(list.contains(0));
        Assert.assertFalse(list.contains(1));
        list.remove(0);
        Assert.assertFalse(list.contains(0));
    }

    /**
     * 测试向线性表尾部添加元素的功能
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(new Integer(1), list.get(0));
        Assert.assertEquals(new Integer(2), list.get(1));
        Assert.assertEquals(new Integer(3), list.get(2));
        Assert.assertEquals(3, list.size());
        list.add(4, 4);
    }

    /**
     * 测试获取指定索引处元素的功能
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testGet() {
        list.add(0);
        Assert.assertEquals(new Integer(0), list.get(0));
        list.get(1);
    }

    /**
     * 测试替换指定索引处元素的功能
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testSet() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(1, 2);
        Assert.assertEquals(new Integer(2), list.get(2));
        list.set(4, 4);
    }

    /**
     * 测试在指定索引处插入元素的功能
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 0);
        list.add(0, 0);
        list.add(4, list.size());
        Assert.assertEquals(new Integer(0), list.get(0));
        Assert.assertEquals(new Integer(1), list.get(1));
        Assert.assertEquals(new Integer(2), list.get(3));
        Assert.assertEquals(6, list.size());
        list.add(7, 5);
    }

    /**
     * 测试删除指定索引处元素的功能
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testRemove() {
        for (int i = 1; i < 6; i++) {
            list.add(i);
        }
        Assert.assertEquals(new Integer(1), list.remove(0));
        Assert.assertEquals(new Integer(3), list.remove(1));
        Assert.assertEquals(new Integer(5), list.remove(list.size() - 1));
        Assert.assertEquals(new Integer(4), list.get(1));
        list.remove(2);
    }

    /**
     * 测试查找指定元素首次出现位置的功能
     */
    @Test
    @Override
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(2, list.indexOf(3));
    }

    /**
     * 测试索引越界异常处理
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testCheckIndex() {
        list.add(0);
        list.get(1);
    }

    /**
     * 测试针对 add 操作的索引越界异常处理
     */
    @Test(expected = IndexOutOfBoundsException.class)
    @Override
    public void testCheckIndexForAdd() {
        list.add(0);
        list.add(2, 0);
    }
}
