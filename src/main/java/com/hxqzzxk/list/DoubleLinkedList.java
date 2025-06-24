package com.hxqzzxk.list;

/**
 * 双向链表实现
 *
 * @param <E> 元素类型
 */
public class DoubleLinkedList<E> extends AbstractDoubleLinkedList<E> {
    /**
     * 在指定位置插入一个元素
     *
     * @param index   插入位置，必须在 [0, size()] 范围内
     * @param element 要插入的元素
     */
    @Override
    public void add(int index, E element) {
        // 验证索引，防止索引越界
        checkIndexForAdd(index);
        // 添加到链表的头节点
        if (index == 0) {
            // 更新头节点，并将新的头节点指向旧的头节点
            head = new Node<E>(null, element, head);
            // 注意：如果此时的链表是空的，那么头节点和尾节点指向的是同一个链表节点
            if (size == 0) {
                tail = head;
            }
        } else if (index == size) { // 添加到链表的尾部
            // 创建出新的尾节点，并且将这个新的尾节点的prev指针指向旧的尾节点
            Node<E> newTail = new Node<E>(tail, element, null);
            // 旧的尾节点的next指针指向新的尾节点
            tail.next = newTail;
            // 更新尾节点
            tail = newTail;
        } else { // 不是在头部或尾部添加
            // 找到要插入的位置的前一个节点
            Node<E> prev = node(index - 1);
            // 创建要插入链表的节点，并将这个节点的prev指针指向前一个节点，next指针指向后一个节点
            Node<E> node = new Node<E>(prev, element, prev.next);
            // next节点的prev指针指向新节点
            prev.next.prev = node;
            // prev节点的next指针指向新节点
            prev.next = node;
        }
        size++;
    }

    /**
     * 删除指定位置的元素
     *
     * @param index 要删除的位置，必须在 [0, size()) 范围内
     * @return 被删除的元素
     */
    @Override
    public E remove(int index) {
        // 验证索引，防止索引越界
        checkIndex(index);
        Node<E> node = head;
        // 删除的是头节点
        if (index == 0) {
            // 更新头节点，指向旧的头节点的下一个节点
            head = head.next;
            // 注意：如果链表中只有一个节点，那么此时head == null
            if (head != null) {
                // 更新head的prev指针，断掉与旧的头节点的联系
                head.prev = null;
            }
        } else if (index == size - 1) {// 删除的是尾节点
            // 保存尾节点，用于返回
            node = tail;
            // 更新尾节点，指向旧的尾节点节点的前一个节点
            tail = tail.prev;
            // 此时的tail不可能是null，但是为了保持对称，保留这个判断
            if (tail != null) {
                // 更新tail的next指针，断掉与旧的尾节点的联系
                tail.next = null;
            }
        } else {// 删除的不是头节点和尾节点
            // 找到要删除的节点的前一个节点
            Node<E> prev = node(index - 1);
            // 获取到要删除的节点，用于返回
            node = prev.next;
            // prev的next指针指向要node的下一个节点next，断掉与要删除节点的联系
            prev.next = prev.next.next;
            // 注意：此时的prev.next已经指向next
            // next指向prev，断掉与node的联系
            prev.next.prev = prev;
            // 注意：node.prev = null和node.next = null不是必须的
            // 但是，如果是为了可读性，也是可以加上的
        }
        size--;
        return node.element;
    }
}