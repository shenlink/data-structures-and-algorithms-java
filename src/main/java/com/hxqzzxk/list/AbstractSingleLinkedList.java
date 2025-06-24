package com.hxqzzxk.list;

/**
 * 抽象的单向链表实现
 * 提供了单向链表的基本操作
 *
 * @param <E> 元素类型
 */
public abstract class AbstractSingleLinkedList<E> extends AbstractList<E> {
    /**
     * 链表的头节点
     */
    protected Node<E> head;

    /**
     * 链表节点类
     *
     * @param <E> 节点存储的元素类型
     */
    protected static class Node<E> {
        /**
         * 节点存储的元素
         */
        E element;

        /**
         * 指向下一个节点的指针
         */
        Node<E> next;

        /**
         * 创建一个新的节点
         *
         * @param element 节点存储的元素
         * @param next    指向下一个节点的指针
         */
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * 返回表示当前节点的字符串
         *
         * @return String 表示当前节点的字符串，格式为"(element, next.element)"
         */
        @Override
        public String toString() {
            // 创建StringBuilder构建返回结果
            StringBuilder stringBuilder = new StringBuilder();
            // 添加当前节点元素和分隔符
            stringBuilder.append("(").append(element.toString()).append(", ");
            // 处理下一个节点的情况
            if (next == null) {
                // 下一个节点为空时的特殊处理
                stringBuilder.append("null").append(")");
            } else {
                // 下一个节点非空时的正常处理
                stringBuilder.append(next.element.toString()).append(")");
            }
            // 返回构建完成的字符串
            return stringBuilder.toString();
        }
    }

    /**
     * 清空链表所有元素
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * 获取指定索引的元素
     *
     * @param index 要获取的元素位置，必须在 [0, size()) 范围内
     * @return 位于指定索引处的元素
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
     * @return 元素首次出现的索引位置，如果未找到则返回 -1
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
     * 根据索引获取对应的链表节点
     *
     * @param index 要获取的索引位置
     * @return 对应索引位置的链表节点
     */
    protected Node<E> node(int index) {
        // node = head，不能修改head的值
        Node<E> node = head;
        // 这里的判断条件是 i < index，不能是 i <= index，因为当i = index - 1时，
        // 会再执行一次循环体里面的代码，此时node = node.next，这时候的node就是index位置的节点了
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 返回链表的字符串表示
     *
     * @return 链表的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        // 构建头部信息、大小信息和元素列表的起始部分
        stringBuilder.append("head: ")
                .append(head == null ? "null" : head.toString())
                .append(", ")
                .append("size: ")
                .append(size)
                .append(", elements: [");
        // 遍历链表节点，构建元素列表部分
        Node<E> node = head;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(node);
            node = node.next;
        }
        // 添加元素列表的结束部分
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}