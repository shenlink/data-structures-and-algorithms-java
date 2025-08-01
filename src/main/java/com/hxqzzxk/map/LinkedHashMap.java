package com.hxqzzxk.map;

import java.util.Objects;

/**
 * LinkedHashMap 实现，基于 HashMap 并维护插入顺序。
 * 通过双向链表记录键值对的插入顺序，在遍历时可以按照插入顺序输出。
 *
 * @param <K> 键的类型
 * @param <V> 值的类型
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    /**
     * 双向链表的头节点，指向第一个插入的节点。
     */
    private LinkedNode<K, V> head;

    /**
     * 双向链表的尾节点，指向最后一个插入的节点。
     */
    private LinkedNode<K, V> tail;

    /**
     * 表示 LinkedHashMap 中的节点，继承自 HashMap.Node，
     * 并增加指向前一个和后一个节点的引用。
     *
     * @param <K> 键的类型
     * @param <V> 值的类型
     */
    private static class LinkedNode<K, V> extends Node<K, V> {
        /**
         * 指向前一个节点
         */
        LinkedNode<K, V> prev;

        /**
         * 指向后一个节点
         */
        LinkedNode<K, V> next;

        /**
         * 构造一个新的 LinkedNode 节点。
         *
         * @param key    键
         * @param value  值
         * @param parent 父节点（红黑树中的父节点）
         */
        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

    /**
     * 清空哈希表中的所有键值对，并重置双向链表的头节点和尾节点。
     */
    @Override
    public void clear() {
        super.clear();
        head = null;
        tail = null;
    }

    /**
     * 判断哈希表是否包含指定的值。
     * 遍历双向链表查找是否存在该值。
     *
     * @param value 要检查的值
     * @return 如果哈希表包含该值则返回 true
     */
    @Override
    public boolean containsValue(V value) {
        LinkedNode<K, V> node = head;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 遍历哈希表中的所有键值对。
     * 使用双向链表按插入顺序输出每个节点的信息。
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) {
            return;
        }
        LinkedNode<K, V> node = head;
        while (node != null) {
            if (visitor.visit(node.key, node.value) || visitor.stop) {
                return;
            }
            node = node.next;
        }
    }

    /**
     * 创建一个新的 LinkedHashMap 节点，并将其加入双向链表的末尾。
     *
     * @param key    要存储的键
     * @param value  要存储的值
     * @param parent 父节点
     * @return 新创建的节点
     */
    @Override
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return node;
    }

    /**
     * 移除节点后的调整操作。
     * 主要用于维护双向链表的完整性。
     *
     * @param replacedNode 原先被删除的节点，因为度为2，使用后继节点来替代
     * @param removedNode  实际被删除的节点
     */
    @Override
    protected void afterRemove(Node<K, V> replacedNode, Node<K, V> removedNode) {
        // 将传入的节点转换为 LinkedNode 类型，以便进行后续操作
        LinkedNode<K, V> node1 = (LinkedNode<K, V>) replacedNode;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) removedNode;
        // 当 node1 和 node2 不同时，表示删除的是一个度为2的节点，
        // 此时实际删除的节点是 node2，假设此时链表中 node2 在 node1
        // 前面，遍历顺序是 A <=> 2 <=> B <=> 1 <=> C，
        // 当删除 node1 时，实际删除的是 node2，
        // 预期的遍历顺序是 A <=> 2 <=> B <=> C，
        // 但是如果此时没有交换 node1 和 node2 的位置，那遍历顺序
        // 就会变成 A <=> B <=> 1 <=> C，与预期的遍历顺序不符
        // 如果交换 node1 与 node2 的位置，那链表就变成了
        // A <=> 1 <=> B <=> 2 <=> C，
        // 此时，node1 和 node2 的位置已经交换，在红黑树那里，node1 和 node2
        // 的值也已经交换了，所以，在之前删除 node2 时，node2 的值
        // 已经覆盖到 node1 了，所哟，此时的链表可以看成是
        // A <=> node1(2) <=> B <=> node2(2) <=> C，
        // 在之后删除 node2 之后，链表的遍历顺序就变成了
        // A <=> 2 <=> B <=> C，与预期的遍历顺序一致
        if (node1 != node2) {
            // 交换 node1 和node2 在链表中的位置
            // 首先处理前驱指针(prev)，交换 node1 和 node2 的 prev 指向
            LinkedNode<K, V> tmp = node1.prev;
            tmp.prev = node2.prev;
            node2.prev = tmp;

            // 更新 node1 的前驱节点的后继(next)指向 node1
            if (node1.prev == null) {
                head = node1; // node1 成为新的头节点
            } else {
                node1.prev.next = node1; // node1 的前驱节点指向它自身
            }

            // 更新 node2 的前驱节点的后继(next)指向 node2
            if (node2.prev == null) {
                tail = node2;
            } else {
                node2.prev.next = node2;
            }

            // 接着处理后继指针(next)，交换 node1 和 node2 的 next 指向
            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;

            // 更新 node1 的后继节点的前驱(prev)指向 node1
            if (node1.next == null) {
                tail = node1;
            } else {
                node1.next.prev = node1;
            }

            // 更新 node2 的后继节点的前驱(prev)指向 node2
            if (node2.next == null) {
                tail = node2;
            } else {
                node2.next.prev = node2;
            }
        }

        // 删除实际的节点 node2，更新其前后节点的连接关系
        LinkedNode<K, V> prev = node2.prev;
        LinkedNode<K, V> next = node2.next;

        // 如果 prev 为空，说明 node2 是头节点，因此更新 head 指向 next
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        // 如果 next 为空，说明 node2 是尾节点，因此更新 tail 指向 prev
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }
    }
}
