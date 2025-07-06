package com.hxqzzxk.trie;

import java.util.HashMap;

/**
 * 字典树（Trie）实现，也称为前缀树。
 * 用于高效存储和检索键值对，特别适合处理字符串集合的操作。
 *
 * @param <V> 值的类型
 */
public class StandardTrie<V> {
    /**
     * 字典树中节点的总数量
     */
    private int size;

    /**
     * 字典树的根节点
     */
    private Node<V> root;

    /**
     * 内部类，表示字典树中的一个节点。
     */
    private static class Node<V> {
        /**
         * 父节点
         */
        Node<V> parent;

        /**
         * 子节点映射：字符 -> 节点
         */
        HashMap<Character, Node<V>> children;

        /**
         * 当前节点对应的字符
         */
        Character character;

        /**
         * 当前节点存储的值
         */
        V value;

        /**
         * 标记当前节点是否为一个完整单词的结尾
         */
        boolean isWord;

        /**
         * 构造一个新的节点。
         *
         * @param parent 父节点
         */
        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

    /**
     * 清空字典树的所有数据。
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * 获取字典树中存储的节点总数。
     *
     * @return 节点数量
     */
    public int size() {
        return size;
    }

    /**
     * 判断字典树是否为空。
     *
     * @return 如果字典树为空则返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据指定的键获取对应的值。
     *
     * @param key 键
     * @return 对应的值，如果键不存在或不是一个完整单词，则返回 null
     */
    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.isWord ? node.value : null;
    }

    /**
     * 检查字典树中是否包含指定的键。
     *
     * @param key 键
     * @return 如果包含该键且其是一个完整单词则返回 true，否则返回 false
     */
    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.isWord;
    }

    /**
     * 向字典树中添加一个键值对。
     *
     * @param key   键
     * @param value 值
     * @return 如果键已存在，则返回旧值；否则返回 null
     */
    public V add(String key, V value) {
        // key不能为null
        checkKey(key);

        // 根节点为空，创建根节点
        if (root == null) {
            root = new Node<>(null);
        }

        // 从根节点出发
        Node<V> node = root;
        int len = key.length();
        // 循环遍历key的每个字符，看是否已经存在，如果已经存在，
        // 继续往下找，如果不存在，则添加
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            // 子节点是否为空
            boolean childrenIsEmpty = node.children == null;
            // 子节点
            Node<V> childNode = childrenIsEmpty ? null : node.children.get(c);
            // 子节点为空，创建子节点
            if (childNode == null) {
                // 创建子节点
                childNode = new Node<>(node);
                // 保存字符
                childNode.character = c;
                // 加上children
                node.children = childrenIsEmpty ? new HashMap<>() : node.children;
                // 添加c到子节点中
                node.children.put(c, childNode);
            }
            // 更新node，继续往下寻找
            node = childNode;
        }

        // 找到最后，如果发现是一个单词，覆盖旧的值，返回旧值
        if (node.isWord) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 添加
        node.isWord = true;
        node.value = value;
        size++;
        return null;
    }

    /**
     * 从字典树中删除指定的键。
     *
     * @param key 键
     * @return 如果键存在，则返回其对应的值；否则返回 null
     */
    public V remove(String key) {
        // 找到节点
        Node<V> node = node(key);
        // 节点为空或不是单词，返回null
        if (node == null || !node.isWord) {
            return null;
        }
        size--;

        V oldValue = node.value;
        // 节点下面还有子节点，那就只能删除单词，不能删除子节点
        if (node.children != null && !node.children.isEmpty()) {
            node.value = null;
            node.isWord = false;
            return oldValue;
        }

        // 节点已经没有子节点了，可以由自己的父节点向上开始删除子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.children.remove(node.character);
            if (!parent.children.isEmpty() || parent.isWord) {
                break;
            }
            node = parent;
        }
        return oldValue;
    }

    /**
     * 判断字典树中是否存在以指定前缀开头的键。
     *
     * @param prefix 前缀
     * @return 如果存在以该前缀开头的键则返回 true，否则返回 false
     */
    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    /**
     * 查找与给定键匹配的最后一个节点。
     *
     * @param key 键
     * @return 匹配的节点，如果没有找到则返回 null
     */
    private Node<V> node(String key) {
        checkKey(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) {
                return null;
            }
            char c = key.charAt(i);
            node = node.children.get(c);
        }

        return node;
    }

    /**
     * 检查键是否有效（非空且非空字符串）。
     *
     * @param key 要检查的键
     * @throws IllegalArgumentException 如果键无效
     */
    private void checkKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }
}
