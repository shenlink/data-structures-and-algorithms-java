package com.hxqzzxk.uf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 并查集-泛型版本
 *
 * @param <E> 元素类型
 */
public class GenericUnionFind<E> {
    /**
     * 内部节点类，表示并查集中的一个元素节点。
     * 每个节点包含值、父节点和 rank（用于优化合并）。
     *
     * @param <E> 节点存储的值类型
     */
    private static class Node<E> {
        /**
         * 节点存储的值
         */
        E value;

        /**
         * 指向该节点的父节点
         */
        Node<E> parent;

        /**
         * 节点的 rank，用于平衡树的高度，在合并时起到优化作用。
         */
        int rank;

        /**
         * 构造一个新的节点。
         *
         * @param value 节点存储的值
         */
        public Node(E value) {
            this.value = value;
            this.parent = this;
            this.rank = 1;
        }
    }

    /**
     * 存储所有元素到对应节点的映射，用于快速查找和操作。
     */
    private Map<E, Node<E>> nodes = new HashMap<>();

    /**
     * 创建一个新的集合，包含一个单独的元素。
     *
     * @param v 需要加入并查集的元素
     */
    public void makeSet(E v) {
        if (nodes.containsKey(v)) {
            return;
        }
        nodes.put(v, new Node<>(v));
    }

    /**
     * 查找给定元素所属集合的代表元素（根节点）。
     *
     * @param v 需要查找的元素
     * @return 所属集合的代表元素；如果未找到则返回 null
     */
    public E find(E v) {
        Node<E> node = findNode(v);
        return node == null ? null : node.value;
    }

    /**
     * 合并两个元素所属的集合。
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     */
    public void union(E v1, E v2) {
        Node<E> node1 = findNode(v1);
        Node<E> node2 = findNode(v2);
        if (node1 == null || node2 == null) {
            return;
        }
        if (Objects.equals(node1.value, node2.value)) {
            return;
        }

        if (node1.rank < node2.rank) {
            node1.parent = node2;
        } else if (node1.rank > node2.rank) {
            node2.parent = node1;
        } else {
            node1.parent = node2;
            node2.rank += 1;
        }
    }

    /**
     * 判断两个元素是否属于同一个集合。
     *
     * @param v1 第一个元素
     * @param v2 第二个元素
     * @return 如果属于同一个集合则返回 true，否则返回 false
     */
    public boolean isConnected(E v1, E v2) {
        return Objects.equals(find(v1), find(v2));
    }

    /**
     * 内部方法：查找给定元素对应的节点的根节点，并进行路径压缩。
     *
     * @param v 需要查找的元素
     * @return 根节点对象
     */
    private Node<E> findNode(E v) {
        Node<E> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        while (!Objects.equals(node.value, node.parent.value)) {
            node = node.parent.parent;
            node = node.parent;
        }
        return node;
    }
}
