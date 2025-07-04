package com.hxqzzxk.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.hxqzzxk.tree.BinaryTree.Visitor;

/**
 * 测试二叉搜索树 (BinarySearchTree) 的各项功能
 */
public class BinarySearchTreeTest {
    /**
     * 被测试的二叉搜索树实例
     * 该树存储Integer类型的数据
     */
    private BinarySearchTree<Integer> tree;

    /**
     * 在每个测试方法执行前初始化一个空的二叉搜索树
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTree<>();
    }

    /**
     * 测试 add 方法：验证添加节点后树的结构和大小是否正确。
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
        Assert.assertEquals("size: 5, height: 5\n" +
                "1: 0 \n" +
                "2: null 1 \n" +
                "3: null null null 2 \n" +
                "4: null null null null null null null 3 \n" +
                "5: null null null null null null null null null " +
                "null null null null null null 4 ", tree.toString());
        tree.clear();
        Integer[] data = new Integer[]{10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals("size: 7, height: 3\n" +
                "1: 10 \n" +
                "2: 5 15 \n" +
                "3: 3 7 12 18 ", tree.toString());

    }

    /**
     * 测试 remove 方法：验证删除节点后树的结构、大小及是否存在该节点。
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
    }

    /**
     * 测试 contains 方法：验证树中是否包含指定的元素。
     */
    @Test
    public void testContains() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(5));
        Assert.assertTrue(tree.contains(15));
        Assert.assertFalse(tree.contains(20));
    }

    /**
     * 测试非递归前序遍历 preOrderNR：验证输出结果是否符合预期。
     */
    @Test
    public void testPreOrderNR() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.preOrderNR(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[10, 5, 3, 7, 15, 12, 18]", result.toString());
    }

    /**
     * 测试另一种非递归前序遍历 preOrderNR2：验证输出结果是否符合预期。
     */
    @Test
    public void testPreOrderNR2() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.preOrderNR2(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[10, 5, 3, 7, 15, 12, 18]", result.toString());
    }

    /**
     * 测试递归前序遍历 preOrder：验证输出结果是否符合预期。
     */
    @Test
    public void testPreOrder() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.preOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[10, 5, 3, 7, 15, 12, 18]", result.toString());
    }

    /**
     * 测试非递归中序遍历 inOrderNR：验证输出结果是否符合预期。
     */
    @Test
    public void testInOrderNR() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.inOrderNR(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[3, 5, 7, 10, 12, 15, 18]", result.toString());
    }

    /**
     * 测试递归中序遍历 inOrder：验证输出结果是否符合预期。
     */
    @Test
    public void testInOrder() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.inOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[3, 5, 7, 10, 12, 15, 18]", result.toString());
    }

    /**
     * 测试非递归后序遍历 postOrderNR：验证输出结果是否符合预期。
     */
    @Test
    public void testPostOrderNR() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.postOrderNR(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[3, 7, 5, 12, 18, 15, 10]", result.toString());
    }

    /**
     * 测试递归后序遍历 postOrder：验证输出结果是否符合预期。
     */
    @Test
    public void testPostOrderTraversal() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }

        List<Integer> result = new ArrayList<>();
        tree.postOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[3, 7, 5, 12, 18, 15, 10]", result.toString());
    }

    /**
     * 测试层序遍历 levelOrder：验证输出结果是否符合预期。
     */
    @Test
    public void testLevelOrder() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        List<Integer> result = new ArrayList<>();
        tree.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                result.add(element);
                return false;
            }
        });
        Assert.assertEquals("[10, 5, 15, 3, 7, 12, 18]", result.toString());
    }

    /**
     * 测试 isComplete 方法：验证树是否为完全二叉树。
     */
    @Test
    public void testIsComplete() {
        Assert.assertFalse(tree.isComplete());
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertTrue(tree.isComplete());
        tree.remove(5);
        Assert.assertFalse(tree.isComplete());
        tree.clear();
        data = new Integer[]{10, 5, 15, 3, 7};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertTrue(tree.isComplete());
        tree.add(18);
        Assert.assertFalse(tree.isComplete());
    }

    /**
     * 测试 height 方法（非递归）：验证返回的树高度是否正确。
     */
    @Test
    public void testHeight() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals(3, tree.height());
    }

    /**
     * 测试 heightR 方法（递归）：验证返回的树高度是否正确。
     */
    @Test
    public void testHeightR() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals(3, tree.heightR());
    }

    /**
     * 测试 clear 方法：验证清空后树的状态是否正确。
     */
    @Test
    public void testClear() {
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        tree.clear();
        Assert.assertEquals(0, tree.size());
        Assert.assertTrue(tree.isEmpty());
        Assert.assertEquals("size: 0, height: 0\n", tree.toString());
    }

    /**
     * 测试 toString 方法：验证树的字符串表示是否正确。
     */
    @Test
    public void testToString() {
        Assert.assertEquals("size: 0, height: 0\n", tree.toString());
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        Assert.assertEquals("size: 6, height: 5\n" +
                        "1: 2 \n" +
                        "2: 1 3 \n" +
                        "3: null null null 4 \n" +
                        "4: null null null null null null null 5 \n" +
                        "5: null null null null null null null null " +
                        "null null null null null null null 6 ",
                tree.toString());
        tree.clear();
        Integer[] data = {10, 5, 15, 3, 7, 12, 18};
        for (Integer value : data) {
            tree.add(value);
        }
        Assert.assertEquals("size: 7, height: 3\n" +
                        "1: 10 \n" +
                        "2: 5 15 \n" +
                        "3: 3 7 12 18 ",
                tree.toString());
    }
}
