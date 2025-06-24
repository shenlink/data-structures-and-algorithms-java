package com.hxqzzxk.list;

/**
 * 抽象的双向链表实现
 * 提供了双向链表的基本操作
 *
 * @param <E> 元素类型
 */
public abstract class AbstractDoubleLinkedList<E> extends AbstractList<E> {
    /**
     * 链表的头节点
     */
    protected Node<E> head;

    /**
     * 链表的尾节点
     */
    protected Node<E> tail;

    /**
     * 链表节点类
     *
     * @param <E> 节点存储的元素类型
     */
    protected static class Node<E> {
        /**
         * 指向前一个节点的指针
         */
        Node<E> prev;

        /**
         * 节点存储的值
         */
        E element;

        /**
         * 指向后一个节点的指针
         */
        Node<E> next;

        /**
         * 创建一个新的链表节点
         *
         * @param prev    指向前一个节点的指针
         * @param element 节点存储的值
         * @param next    指向后一个节点的指针
         */
        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * 返回表示当前节点的字符串
         * 此方法查看当前对象的前驱（prev）、当前元素（element）和后继（next），并将它们的字符串表示拼接起来
         * 如果前驱或后继节点为空，则在相应位置以 null 表示
         *
         * @return 返回包含前驱、当前元素和后继链表节点信息的字符串
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (prev == null) {
                stringBuilder.append("(").append("null").append(", ");
            } else {
                stringBuilder.append("(").append(prev.element.toString()).append(", ");
            }
            stringBuilder.append(element.toString()).append(", ");
            if (next == null) {
                stringBuilder.append("null").append(")");
            } else {
                stringBuilder.append(next.element.toString()).append(")");
            }
            return stringBuilder.toString();
        }
    }

    /**
     * 清空链表所有元素
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index 要获取的元素位置，必须在 [0, size()) 范围内
     * @return 位于指定位置处的元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).element;
    }

    /**
     * 替换指定位置的元素
     *
     * @param index   要替换的位置，必须在 [0, size()) 范围内
     * @param element 新元素
     * @return 被替换的旧元素
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * 查找指定元素第一次出现的位置
     *
     * @param element 要查找的元素
     * @return 元素首次出现的位置位置，如果未找到则返回 -1
     */
    @Override
    public int indexOf(E element) {
        // node = head，不能修改head的值
        Node<E> node = head;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 根据位置获取对应的链表节点
     *
     * @param index 要获取的位置位置
     * @return 对应位置位置的链表节点
     */
    protected Node<E> node(int index) {
        // 位置值小于链表长度的一半，从头节点开始遍历
        if (index < (size >> 1)) {
            Node<E> node = head;
            // 这里的判断条件是i < index，因为在i= index - 1时，还会进入一次循环，
            // 此时node = node.next，这时候的node就是index位置的节点了
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
        // 位置值大于等于链表长度的一半，从尾节点开始遍历
        Node<E> node = tail;
        // 从size - 1开始，size - 1才是链表的尾节点所在的位置
        // 这里的判断条件是i > index，因为在i= index + 1时，还会进入一次循环，
        // 此时node = node.prev，这时候的node就是index位置的节点了
        for (int i = size - 1; i > index; i--) {
            node = node.prev;
        }
        return node;
    }

    /**
     * 返回链表的字符串表示
     *
     * @return 链表的详细信息，包括头节点、尾节点、大小和所有元素
     */
    @Override
    public String toString() {
        // 使用StringBuilder来构建最终的字符串表示
        StringBuilder stringBuilder = new StringBuilder();
        // 以"head: "开始，如果head为null则显示"null"，否则调用head的toString方法
        stringBuilder.append("head: ")
                .append(head == null ? "null" : head.toString())
                .append(", ")
                // 以"tail: "开始，如果tail为null则显示"null"，否则调用tail的toString方法
                .append("tail: ")
                .append(tail == null ? "null" : tail.toString())
                .append(", ")
                // 添加"size: "及其值
                .append("size: ")
                .append(size)
                .append(", elements: [");
        // 从头节点开始遍历链表
        Node<E> node = head;
        for (int i = 0; i < size; i++) {
            // 如果不是第一个元素，添加分隔符", "
            if (i != 0) {
                stringBuilder.append(", ");
            }
            // 添加当前节点的字符串表示
            stringBuilder.append(node);
            // 移动到下一个节点
            node = node.next;
        }
        // 添加结束符"]"
        stringBuilder.append("]");
        // 返回构建完成的字符串
        return stringBuilder.toString();
    }
}