package com.hxqzzxk.map;

/**
 * 映射接口
 */
public interface Map<K, V> {
    /**
     * 清空 map
     */
    void clear();

    /**
     * 返回 map 中键值对的数量
     *
     * @return 键值对数量
     */
    int size();

    /**
     * map 是否为空
     *
     * @return 如果 map 没有键值对则返回 true
     */
    boolean isEmpty();

    /**
     * 添加键值对
     *
     * @param key   要添加的键
     * @param value 要添加的值
     * @return 之前与该键关联的值，如果没有则返回 null
     */
    V put(K key, V value);

    /**
     * 根据 key 获取 value
     *
     * @param key 要查找的键
     * @return 与键关联的值，如果不存在则返回 null
     */
    V get(K key);

    /**
     * 删除键值对，返回 key 对应的 value
     *
     * @param key 要删除的键
     * @return 与键关联的值，如果不存在则返回 null
     */
    V remove(K key);

    /**
     * 是否包含 key
     *
     * @param key 要查找的键
     * @return 如果map包含指定键则返回 true
     */
    boolean containsKey(K key);

    /**
     * 是否包含 value
     *
     * @param value 要查找的值
     * @return 如果 map 包含指定值则返回 true
     */
    boolean containsValue(V value);

    /**
     * 遍历 map
     */
    void traversal(Visitor<K, V> visitor);

    /**
     * 抽象访问者类，用于遍历二叉树元素
     * 
     * @param <E> 二叉树节点中存储的元素类型
     */
    public static abstract class Visitor<K, V> {
        /**
         * 停止遍历标志
         * 当该属性为 true 时，遍历操作应当停止
         */
        boolean stop;

        /**
         * 访问指定键值对
         * 
         * @param key   元素对应的键
         * @param value 元素对应的值
         * @return 返回 true 停止遍历，返回 false 则继续遍历
         */
        public abstract boolean visit(K key, V value);
    }
}