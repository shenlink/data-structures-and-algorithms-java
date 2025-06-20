package com.hxqzzxk.list;

/**
 * 单向循环链表实现
 * @param <E> 元素类型
 */
public class SingleCircleLinkedList<E> extends AbstractSingleLinkedList<E> {
    /**
     * 在指定位置插入元素
     * 
     * @param index   插入位置，必须在 [0, size()] 范围内
     * @param element 要插入的元素
     */
    @Override
    public void add(int index, E element) {
        // 验证索引，防止索引越界
        checkIndexForAdd(index);
        // 在头节点添加元素
        if (index == 0) {
            // 创建新的头节点，next指针指向旧的头节点
            Node<E> newHead = new Node<E>(element, head);
            // 如果此时的元素总数是0，尾节点也指向这个新的头节点，否则，指向链表的最后一个节点
            // 注意：不能统一使用node(size - 1)，因为size == 0时，链表还没有节点，此时调用
            // node(size - 1)，返回的是错误的结果
            Node<E> tail = size == 0 ? newHead : node(size - 1);
            // 尾节点的next指针指向新的头节点
            tail.next = newHead;
            // 更新头节点
            head = newHead;
        } else {// 添加的不是头节点
            // 获取到要添加的索引的前一个节点prev
            Node<E> prev = node(index - 1);
            // prev的next指针指向新的节点node，node的next指针指向prev.next，
            // 也就是next节点，这样，node节点就与prev和next连接上了
            prev.next = new Node<E>(element, prev.next);
        }
        size++;
    }

    /**
     * 删除指定位置的节点
     * 
     * @param index 要删除的位置，必须在 [0, size()) 范围内
     * @return 被删除的元素
     */
    @Override
    public E remove(int index) {
        // 验证索引，防止索引越界
        checkIndex(index);
        // 保存要删除的节点，用于返回
        Node<E> node = head;
        // 删除的是头节点
        if (index == 0) {
            // 链表的元素总是是1，直接置空head节点
            if (size == 1) {
                head = null;
            } else {
                // 获取到尾节点
                Node<E> tail = node(size - 1);
                // 头节点指向旧的头节点的next节点，断掉与旧的头节点的联系
                head = head.next;
                // 尾节点的next指针指向新的头节点
                tail.next = head;
            }
        } else {// 删除的不是头节点
            // 获取到要删除的索引的前一个节点prev
            Node<E> prev = node(index - 1);
            // 获取到要删除的节点node，用于返回
            node = prev.next;
            // prev的next指向要删除的节点node的next节点，断掉与node的联系
            prev.next = node.next;
            // 注意：此时，可以node.next = null，但是，这不是必须的，为了可读性，可以加上
        }
        size--;
        return node.element;
    }
}