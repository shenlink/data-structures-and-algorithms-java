package com.hxqzzxk.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 稀疏数组实现
 * 用于存储大部分元素为默认值的二维数组
 */
public class SparseArray {
    /**
     * 存储数据的二维数组
     */
    private int[][] data;

    /**
     * 行数
     */
    private int rows;

    /**
     * 列数
     */
    private int cols;

    /**
     * 默认值
     */
    private final int defaultValue;

    /**
     * 非默认值的数量
     */
    private int size;

    /**
     * 创建一个新的稀疏数组
     * 
     * @param rows         行数
     * @param cols         列数
     * @param defaultValue 默认值
     */
    public SparseArray(int rows, int cols, int defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.defaultValue = defaultValue;
        data = new int[rows][cols];
        // 填充默认值
        fillDefaultValue();
    }

    /**
     * 设置指定位置的值
     * 
     * @param row   行索引
     * @param col   列索引
     * @param value 要设置的值
     */
    public void put(int row, int col, int value) {
        validate(row, col);
        data[row][col] = value;
        size++;
    }

    /**
     * 获取指定位置的值
     * 
     * @param row 行索引
     * @param col 列索引
     * @return 指定位置的值
     */
    public int get(int row, int col) {
        validate(row, col);
        return data[row][col];
    }

    /**
     * 删除指定位置的值（设置为默认值）
     * 
     * @param row 行索引
     * @param col 列索引
     */
    public void remove(int row, int col) {
        validate(row, col);
        data[row][col] = defaultValue;
        size--;
    }

    /**
     * 清空所有非默认值
     */
    public void clear() {
        data = new int[rows][cols];
        fillDefaultValue();
        size = 0;
    }

    /**
     * 获取当前存储的非默认值的数量
     * 
     * @return 非默认值的数量
     */
    public int size() {
        return size;
    }

    /**
     * 获取稀疏数组的默认值
     * 
     * @return 默认值
     */
    public int getDefaultValue() {
        return defaultValue;
    }

    /**
     * 检查指定位置是否存储了非默认值
     * 
     * @param row 行索引
     * @param col 列索引
     * @return 如果存储了非默认值则返回 true
     */
    public boolean containsKey(int row, int col) {
        validate(row, col);
        return data[row][col] != defaultValue;
    }

    /**
     * 获取所有非默认值的键值对集合
     * 
     * @return 所有非默认值的键值对集合
     */
    public Iterable<Entry> entrySet() {
        List<Entry> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] != defaultValue) {
                    result.add(new Entry(i, j, data[i][j]));
                }
            }
        }
        return result;
    }

    /**
     * 将稀疏数组转换为三元组格式
     * 
     * @return 三元组格式的二维数组
     */
    public int[][] toSparseArrayFormat() {
        int[][] sparseArray = new int[size + 1][3];
        // 在三元组二维数组第一行存储稀疏数组的行数、列数和非默认值的数量
        sparseArray[0][0] = rows;
        sparseArray[0][1] = cols;
        sparseArray[0][2] = size;
        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果不是默认值，存储该值所在的行、列和值
                if (data[i][j] != defaultValue) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = data[i][j];
                    count++;
                }
            }
        }
        return sparseArray;
    }

    /**
     * 从三元组格式恢复稀疏数组
     * 
     * @param sparseArray 三元组格式的二维数组
     */
    public void fromSparseArrayFormat(int[][] sparseArray) {
        // 三元组二维数组的第一行存储的是稀疏数组的行数、列数和非默认值的数量
        rows = sparseArray[0][0];
        cols = sparseArray[0][1];
        size = sparseArray[0][2];
        data = new int[rows][cols];
        // 需要先填充默认值
        fillDefaultValue();
        for (int i = 1; i < sparseArray.length; i++) {
            data[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
    }

    /**
     * 获取逻辑上的行数
     * 
     * @return 逻辑上的行数
     */
    public int rows() {
        return rows;
    }

    /**
     * 获取逻辑上的列数
     * 
     * @return 逻辑上的列数
     */
    public int cols() {
        return cols;
    }

    /**
     * 键值对条目类
     */
    public static class Entry {
        /**
         * 行索引
         */
        private final int row;

        /**
         * 列索引
         */
        private final int col;

        /**
         * 存储的值
         */
        private final int value;

        /**
         * 创建一个新的条目
         * 
         * @param row   行索引
         * @param col   列索引
         * @param value 存储的值
         */
        public Entry(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        /**
         * 获取行索引
         * 
         * @return 行索引
         */
        public int getRow() {
            return row;
        }

        /**
         * 获取列索引
         * 
         * @return 列索引
         */
        public int getCol() {
            return col;
        }

        /**
         * 获取存储的值
         * 
         * @return 存储的值
         */
        public int getValue() {
            return value;
        }

        /**
         * 重写 equals 方法，用于比较两个对象是否相等
         * 此方法重写了Object类的equals方法，目的是比较两个Entry对象的row、col和value属性是否完全相等
         * 
         * @param o 被比较的对象
         * @return 如果两个对象相等则返回true，否则返回false
         */
        @Override
        public boolean equals(Object o) {
            // 如果是同一个对象，返回true
            if (this == o) {
                return true;
            }
            // 如果传入的对象不是Entry类型，则返回false
            if (!(o instanceof Entry)) {
                return false;
            }
            // 将传入的对象转换为Entry类型，并进行属性值的比较
            Entry entry = (Entry) o;
            // 比较两个Entry对象的row、col和value属性是否相等
            return row == entry.row && col == entry.col && value == entry.value;
        }

        /**
         * 重写hashCode方法，用于生成对象的哈希码
         * 这对于在哈希集合中（如HashMap, HashSet）使用对象作为键时尤为重要
         * 
         * @return 对象的哈希码，由row, col和value的哈希值组成
         */
        @Override
        public int hashCode() {
            return Objects.hash(row, col, value);
        }

        /**
         * 返回稀疏数组的字符串表示
         * 
         * @return 包含稀疏数组的行(row)、列(col)和值(value)信息的字符串
         */
        @Override
        public String toString() {
            // 返回一个字符串，格式为"(行, 列, 值)"
            return "(" + row + ", " + col + ", " + value + ")";
        }
    }

    /**
     * 验证行和列索引
     * 
     * @param row 行索引
     * @param col 列索引
     */
    private void validate(int row, int col) {
        validateRow(row);
        validateCol(col);
    }

    /**
     * 验证行索引
     * 
     * @param row 行索引
     */
    private void validateRow(int row) {
        if (row < 0 || row > rows) {
            throw new IllegalArgumentException("Invalid rows: " + row);
        }
    }

    /**
     * 验证列索引
     * 
     * @param col 列索引
     */
    private void validateCol(int col) {
        if (col < 0 || col > cols) {
            throw new IllegalArgumentException("Invalid cols: " + col);
        }
    }

    /**
     * 填充默认值
     */
    private void fillDefaultValue() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = defaultValue;
            }
        }
    }
}