package com.hxqzzxk.filter;

/**
 * 布隆过滤器（Bloom Filter），用于高效判断一个元素是否可能存在于集合中。
 * 该实现基于位数组和多个哈希函数，能够以较低的空间开销支持大量数据的快速查询。
 * <p>
 * 布隆过滤器的特点：
 * - 存在“误判”的可能性，即未插入的元素可能被错误地判断为存在，如果返回存在，则可能是不存在的，也可能是存在的；
 * - 不会出现“漏判”，即已插入的元素绝不会被错误地判断为不存在，如果返回不存在，那就是一定不存在；
 * - 添加元素后不能删除（标准布隆过滤器）；
 * - 空间效率高，适合大规模数据场景。
 * </p>
 *
 * @param <E> 元素类型
 */
public class BloomFilter<E> {
    /**
     * 位数组的大小（以位为单位）
     */
    private int bitSize;

    /**
     * 使用 long 数组来存储位数组。
     * 每个 long 变量可以存储 Long.SIZE 位。
     */
    private long[] bits;

    /**
     * 哈希函数的数量
     */
    private int hashSize;

    /**
     * 构造一个新的布隆过滤器。
     *
     * @param n 预期插入的元素数量
     * @param p 期望的误判率 (0 < p < 1)
     * @throws IllegalArgumentException 如果参数不合法
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong n or p");
        }

        double ln2 = Math.log(2);
        // 求出二进制向量的长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        // 求出哈希函数的个数
        hashSize = (int) (bitSize * ln2 / n);
        // bits数组的长度
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    /**
     * 将指定元素添加到布隆过滤器中。
     * 通过多个哈希函数计算出多个索引位置，并将这些位置的位设置为 1。
     *
     * @param value 要添加的元素
     * @return 如果在添加前至少有一个位已经被置为 1，则返回 true；否则返回 false
     * @throws IllegalArgumentException 如果传入的值为 null
     */
    public boolean put(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }

        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        boolean result = false;
        for (int i = 0; i < hashSize; i++) {
            int combineHash = hash1 + i * hash2;
            if (combineHash < 0) {
                combineHash = ~combineHash;
            }
            int index = combineHash % bitSize;
            if (set(index)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断布隆过滤器是否包含指定元素。
     * 通过相同的哈希函数计算出对应的位索引，只要有一个位置的位为 0，则认为该元素不存在。
     *
     * @param value 要检查的元素
     * @return 如果所有对应位都为 1，则返回 true；否则返回 false
     * @throws IllegalArgumentException 如果传入的值为 null
     */
    public boolean contains(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }

        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 0; i < hashSize; i++) {
            int combineHash = hash1 + i * hash2;
            if (combineHash < 0) {
                combineHash = ~combineHash;
            }
            int index = combineHash % bitSize;
            // 只要有一个索引的位置为false，则返回false
            if (!get(index)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置位数组中指定索引位置的位为 1。
     *
     * @param index 位数组中的索引
     * @return 如果在设置前该位已经为 1，则返回 true；否则返回 false
     */
    private boolean set(int index) {
        long value = bits[index / Long.SIZE];
        int bitValue = 1 << (index % Long.SIZE);
        bits[index / Long.SIZE] = value | bitValue;
        return (bitValue & value) != 0;
    }

    /**
     * 获取位数组中指定索引位置的值。
     *
     * @param index 位数组中的索引
     * @return 如果该位为 1，则返回 true；否则返回 false
     */
    private boolean get(int index) {
        long value = bits[index / Long.SIZE];
        int bitValue = 1 << (index % Long.SIZE);
        return (value & bitValue) != 0;
    }
}
