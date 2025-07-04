package com.hxqzzxk.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * RedBlackTreeTest 类用于测试 RedBlackTree 的各种功能。
 */
public class RedBlackTreeTest {
    /**
     * 用于测试的红黑树实例。
     */
    private RedBlackTree<Integer> tree;

    /**
     * 每个测试方法执行前初始化一个新的红黑树实例。
     */
    @Before
    public void setUp() {
        tree = new RedBlackTree<>();
    }

    /**
     * 测试红黑树的 add 方法。
     * 验证添加元素后树的大小、高度以及元素的存在性。
     */
    @Test
    public void testAdd() {
        Assert.assertEquals("size: 0, height: 0\n", tree.toString());
        tree.add(10);
        Assert.assertEquals("size: 1, height: 1\n" +
                "1: 10 ", tree.toString());
        tree.add(5);
        tree.add(15);
        Assert.assertEquals(3, tree.size());
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(5));
        Assert.assertTrue(tree.contains(15));
        Assert.assertFalse(tree.contains(20));
        tree.clear();
        for (int i = 0; i < 5; i++) {
            tree.add(i);
        }
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(tree.contains(i));
        }
        Assert.assertFalse(tree.contains(6));
        Assert.assertEquals("size: 5, height: 3\n" +
                "1: 1 \n" +
                "2: 0 3 \n" +
                "3: null null 2 4 ", tree.toString());
        tree.clear();
        Integer[] data = new Integer[]{10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals("size: 7, height: 3\n" +
                "1: 10 \n" +
                "2: 5 15 \n" +
                "3: 3 7 12 18 ", tree.toString());
        tree.clear();
        for (int i = 1; i < 20; i++) {
            tree.add(i);
        }
        Assert.assertEquals("size: 19, height: 6\n" +
                "1: 8 \n" +
                "2: 4 12 \n" +
                "3: 2 6 10 14 \n" +
                "4: 1 3 5 7 9 11 13 16 \n" +
                "5: null null null null null null null null null null null " +
                "null null null 15 18 \n" +
                "6: null null null null null null null null null null null " +
                "null null null null null null null null null null null null " +
                "null null null null null null null 17 19 ", tree.toString());
    }

    /**
     * 测试红黑树的 remove 方法。
     * 验证删除元素后树的大小、高度以及元素的存在性。
     */
    @Test
    public void testRemove() {
        // 删除空树
        tree.remove(10);
        // 删除根节点
        tree.add(10);
        tree.remove(10);
        Assert.assertEquals(0, tree.size());
        Assert.assertTrue(tree.isEmpty());
        Assert.assertEquals("size: 0, height: 0\n", tree.toString());

        // 删除重复值
        tree.remove(10);
        // 删除度为0的节点
        tree.clear();
        Integer[] data = new Integer[]{10, 5, 15};
        for (Integer value : data) {
            tree.add(value);
        }
        tree.remove(5);
        Assert.assertEquals(2, tree.size());
        Assert.assertFalse(tree.contains(5));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 2, height: 2\n" +
                "1: 10 \n" +
                "2: null 15 ", tree.toString());

        // 删除重复值
        tree.remove(5);
        Assert.assertEquals(2, tree.size());
        Assert.assertFalse(tree.contains(5));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 2, height: 2\n" +
                "1: 10 \n" +
                "2: null 15 ", tree.toString());

        tree.clear();
        data = new Integer[]{7, 4, 9, 2, 5};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals(5, tree.size());
        tree.remove(5);
        Assert.assertEquals("size: 4, height: 3\n" +
                "1: 7 \n" +
                "2: 4 9 \n" +
                "3: 2 null null null ", tree.toString());

        // 删除度为1的节点
        tree.clear();
        data = new Integer[]{10, 5, 15, 3, 16};
        for (Integer value : data) {
            tree.add(value);
        }
        tree.remove(5);
        Assert.assertEquals(4, tree.size());
        Assert.assertFalse(tree.contains(5));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 4, height: 3\n" +
                "1: 10 \n" +
                "2: 3 15 \n" +
                "3: null null null 16 ", tree.toString());
        tree.remove(15);
        Assert.assertEquals(3, tree.size());
        Assert.assertFalse(tree.contains(15));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 3, height: 2\n" +
                "1: 10 \n" +
                "2: 3 16 ", tree.toString());

        // 删除度为2的节点
        tree.clear();
        data = new Integer[]{10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        tree.remove(5);
        Assert.assertEquals(6, tree.size());
        Assert.assertFalse(tree.contains(5));
        Assert.assertTrue(tree.contains(7));
        Assert.assertEquals("size: 6, height: 3\n" +
                "1: 10 \n" +
                "2: 3 15 \n" +
                "3: null 7 12 18 ", tree.toString());
        tree.remove(15);
        Assert.assertEquals(5, tree.size());
        Assert.assertFalse(tree.contains(15));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 5, height: 3\n" +
                "1: 10 \n" +
                "2: 3 12 \n" +
                "3: null 7 null 18 ", tree.toString());

        // 删除不存在的值
        tree.remove(20);
        Assert.assertEquals(5, tree.size());
        Assert.assertFalse(tree.contains(15));
        Assert.assertTrue(tree.contains(10));
        Assert.assertEquals("size: 5, height: 3\n" +
                "1: 10 \n" +
                "2: 3 12 \n" +
                "3: null 7 null 18 ", tree.toString());

        // 测试添加多个元素
        tree.clear();
        for (int i = 1; i <= 20; i++) {
            tree.add(i);
        }
        Assert.assertEquals(20, tree.size());
        tree.remove(1);
        Assert.assertEquals("size: 19, height: 6\n" +
                "1: 8 \n" +
                "2: 4 12 \n" +
                "3: 2 6 10 16 \n" +
                "4: null 3 5 7 9 11 14 18 \n" +
                "5: null null null null null null null null null null null " +
                "null 13 15 17 19 \n" +
                "6: null null null null null null null null null null null " +
                "null null null null null null null null null null null null " +
                "null null null null null null null null 20 ", tree.toString());
        tree.remove(11);
        Assert.assertEquals("size: 18, height: 5\n" +
                "1: 8 \n" +
                "2: 4 16 \n" +
                "3: 2 6 12 18 \n" +
                "4: null 3 5 7 10 14 17 19 \n" +
                "5: null null null null null null null null 9 null 13 15 null" +
                " null null 20 ", tree.toString());
        tree.remove(19);
        Assert.assertEquals("size: 17, height: 5\n" +
                "1: 8 \n" +
                "2: 4 16 \n" +
                "3: 2 6 12 18 \n" +
                "4: null 3 5 7 10 14 17 20 \n" +
                "5: null null null null null null null null 9 null 13 15 null" +
                " null null null ", tree.toString());
    }
}
