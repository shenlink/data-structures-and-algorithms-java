package com.hxqzzxk.list;

/**
 * 双向循环链表实现
 *
 * @param <E> 元素类型
 */
public class DoubleCircleLinkedList<E> extends AbstractDoubleLinkedList<E> {
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
        // 添加到链表头部
        if (index == 0) {
            // 创建出新的链表头，prev指针先指向null，next指针指向旧的头节点
            Node<E> newHead = new Node<E>(null, element, head);
            // 此时链表的元素总数是0
            if (size == 0) {
                // 新的头节点的prev和next指针都指向自己
                newHead.prev = newHead;
                newHead.next = newHead;
                // 尾节点也指向新的头节点
                tail = newHead;
            } else {
                // 获取到尾节点
                Node<E> tail = node(size - 1);
                // 更新尾节点的next指针，指向新的头节点
                tail.next = newHead;
                // 更新头节点的prev指针，指向尾节点
                newHead.prev = tail;
            }
            // 更新头节点
            head = newHead;
        } else if (index == size) {// 添加到链表的尾部
            // 创建出新的尾节点，尾节点的prev指针指向旧的尾节点，next指向头节点
            Node<E> newTail = new Node<E>(tail, element, head);
            // 更新尾节点的next指针，指向新的尾节点
            tail.next = newTail;
            // 更新头节点的prev指针，指向新的尾节点
            head.prev = newTail;
            // 更新尾节点，指向新的尾节点
            tail = newTail;
        } else {// 添加的不是头节点和尾节点
            // 获取到添加位置的前一个节点
            Node<E> prev = node(index - 1);
            // 创建要插入链表的节点，prev指针指向前一个节点，next指针指向后一个节点
            // 新节点node的prev指向前一个节点prev，next指向prev的下一个节点next
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
        // 保存要删除的节点，默认是head节点，用于返回
        Node<E> node = head;
        // 删除的是头节点
        if (index == 0) {
            // 只有一个节点，直接置空head和tail节点
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                // 更新头节点，头节点指向旧的头的下一个节点
                head = head.next;
                // 尾节点指向新的头节点
                tail.next = head;
                // 新的头节点的prev指针指向尾节点
                head.prev = tail;
            }
        } else if (index == size - 1) {// 删除的是尾节点
            // 获取到尾节点的前一个节点prev
            Node<E> prev = node(index - 1);
            // 获取到尾节点，保存，用于返回
            node = prev.next;
            // prev指向头节点，断掉与尾节点的联系
            prev.next = head;
            // 头节点的prev指针指向新的尾节点
            head.prev = prev;
            // tail指向新的尾节点
            tail = prev;
        } else {// 删除的是中间的节点
            // 获取到要删除的节点的前一个节点prev
            Node<E> prev = node(index - 1);
            // 获取到要删除的节点，用于返回
            node = prev.next;
            // prev的next指针指向要node的下一个节点next，断掉与要删除节点的联系
            prev.next = prev.next.next;
            // next节点的prev指向prev，断掉next节点的node联系
            node.next.prev = prev;
            // 注意：node.prev = null和node.next = null不是必须的
            // 但是，如果是为了可读性，也是可以加上的
        }
        size--;
        return node.element;
    }
}