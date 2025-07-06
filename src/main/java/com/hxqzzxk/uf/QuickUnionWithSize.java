package com.hxqzzxk.uf;

/**
 * 并查集-快速合并-基于size的优化
 * 支持动态合并和查找操作，并通过维护每个集合的大小来优化合并操作。
 */
public class QuickUnionWithSize extends QuickUnion {

    /**
     * 存储每个集合的大小，索引表示根节点，值表示该集合的元素数量
     */
    private int[] sizes;

    /**
     * 构造一个基于 size 优化的并查集实例。
     *
     * @param capacity 初始容量
     */
    public QuickUnionWithSize(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    /**
     * 合并两个元素所属的集合，并根据集合大小决定合并方向以保持较低的树高度。
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }

        // 如果v2所在的集合的元素数量更多，则将v1的根节点指向v2的根节点，
        // 然后更新v2所在集合的大小。
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else { // 对称操作
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
