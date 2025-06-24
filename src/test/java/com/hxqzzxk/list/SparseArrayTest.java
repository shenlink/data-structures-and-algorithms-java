package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 稀疏数组测试类
 * 测试稀疏数组的二维数据存储与访问功能
 */
public class SparseArrayTest {
    /**
     * 测试用的稀疏数组实例
     */
    private SparseArray sparseArray;
    /**
     * 测试用的稀疏数组的行数
     */
    private static final int ROWS = 3;
    /**
     * 测试用的稀疏数组的列数
     */
    private static final int COLS = 3;
    /**
     * 测试用的稀疏数组的默认值
     */
    private static final int DEFAULT_VALUE = 0;

    /**
     * 初始化测试用的稀疏数组
     */
    @Before
    public void setUp() {
        sparseArray = new SparseArray(ROWS, COLS, DEFAULT_VALUE);
    }

    /**
     * 测试初始大小
     */
    @Test
    public void testInitialSize() {
        Assert.assertEquals(0, sparseArray.size());
    }

    /**
     * 测试获取默认值的方法
     */
    @Test
    public void testGetDefaultValue() {
        Assert.assertEquals(DEFAULT_VALUE, sparseArray.getDefaultValue());
    }

    /**
     * 测试元素存储操作
     */
    @Test
    public void testPut() {
        int row = 0, col = 0, value = 5;
        sparseArray.put(row, col, value);

        Assert.assertEquals(value, sparseArray.get(row, col));
        Assert.assertEquals(1, sparseArray.size());
    }

    /**
     * 测试元素获取操作
     */
    @Test
    public void testGet() {
        int row = 0, col = 0, value = 5;
        sparseArray.put(row, col, value);

        Assert.assertEquals(value, sparseArray.get(row, col));
        Assert.assertEquals(1, sparseArray.size());
    }

    /**
     * 测试元素移除操作
     */
    @Test
    public void testRemove() {
        int row = 1, col = 1, value = 7;
        sparseArray.put(row, col, value);
        sparseArray.remove(row, col);

        Assert.assertEquals(DEFAULT_VALUE, sparseArray.get(row, col));
        Assert.assertEquals(0, sparseArray.size());
    }

    /**
     * 测试清空所有元素操作
     */
    @Test
    public void testClear() {
        sparseArray.put(0, 0, 1);
        sparseArray.put(1, 1, 2);
        sparseArray.clear();

        Assert.assertEquals(0, sparseArray.size());
        Assert.assertEquals(DEFAULT_VALUE, sparseArray.get(0, 0));
    }

    /**
     * 测试检查键是否存在操作
     */
    @Test
    public void testContainsKey() {
        int row = 2, col = 2, value = 9;
        Assert.assertFalse(sparseArray.containsKey(row, col));

        sparseArray.put(row, col, value);
        Assert.assertTrue(sparseArray.containsKey(row, col));

        sparseArray.remove(row, col);
        Assert.assertFalse(sparseArray.containsKey(row, col));
    }

    /**
     * 测试获取所有键值对集合操作
     */
    @Test
    public void testEntrySet() {
        List<SparseArray.Entry> expectedEntries = Arrays.asList(
                new SparseArray.Entry(0, 0, 1),
                new SparseArray.Entry(1, 1, 2),
                new SparseArray.Entry(2, 2, 3));

        for (SparseArray.Entry entry : expectedEntries) {
            sparseArray.put(entry.getRow(), entry.getCol(), entry.getValue());
        }

        List<SparseArray.Entry> actualEntries = new ArrayList<>();
        for (SparseArray.Entry entry : sparseArray.entrySet()) {
            actualEntries.add(entry);
        }

        expectedEntries.sort(Comparator.comparingInt(SparseArray.Entry::getRow)
                .thenComparingInt(SparseArray.Entry::getCol));
        actualEntries.sort(Comparator.comparingInt(SparseArray.Entry::getRow)
                .thenComparingInt(SparseArray.Entry::getCol));

        Assert.assertEquals(expectedEntries, actualEntries);
    }

    /**
     * 测试转换为稀疏数组格式操作
     */
    @Test
    public void testToSparseArrayFormat() {
        int[][] expected = {
                {ROWS, COLS, 2},
                {0, 0, 1},
                {1, 1, 2}
        };

        sparseArray.put(0, 0, 1);
        sparseArray.put(1, 1, 2);

        int[][] result = sparseArray.toSparseArrayFormat();

        Assert.assertNotNull(result);
        Assert.assertEquals(expected.length, result.length);

        for (int i = 0; i < expected.length; i++) {
            Assert.assertArrayEquals(expected[i], result[i]);
        }
    }

    /**
     * 测试从稀疏数组格式恢复操作
     */
    @Test
    public void testFromSparseArrayFormat() {
        int[][] input = {
                {3, 3, 2},
                {0, 0, 10},
                {2, 2, 20}
        };

        sparseArray.fromSparseArrayFormat(input);

        Assert.assertEquals(3, sparseArray.rows());
        Assert.assertEquals(3, sparseArray.cols());
        Assert.assertEquals(2, sparseArray.size());
        Assert.assertEquals(10, sparseArray.get(0, 0));
        Assert.assertEquals(20, sparseArray.get(2, 2));
        Assert.assertEquals(DEFAULT_VALUE, sparseArray.get(1, 1));
    }

    /**
     * 测试获取行数操作
     */
    @Test
    public void testRows() {
        Assert.assertEquals(ROWS, sparseArray.rows());
    }

    /**
     * 测试获取列数操作
     */
    @Test
    public void testCols() {
        Assert.assertEquals(COLS, sparseArray.cols());
    }

    /**
     * 测试无效行索引访问异常处理
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRowAccess() {
        sparseArray.get(-1, -1);
    }

    /**
     * 测试无效列索引访问异常处理
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidColAccess() {
        sparseArray.get(4, 4);
    }
}
