package com.hxqzzxk.list;

/**
 * 单向链表实现
 * @param <E> 元素类型
 */
public class SingleLinkedList<E> extends AbstractSingleLinkedList<E> {
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
        // 添加的元素是头节点
        if (index == 0) {
            // 创建新的头节点，next指针指向旧的头节点
            head = new Node<E>(element, head);
        } else {// 添加的不是头节点
            // 获取要添加的索引的前一个节点prev
            Node<E> prev = node(index - 1);
            // prev.next指向新创建的节点node，node.next指向prev.next，
            // 也就是next节点，这样，node节点就与prev和next连接上了
            prev.next = new Node<E>(element, prev.next);
        }
        size++;
    }

    /**
     * 删除指定索引位置的元素
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
            // 头节点指向旧的头节点的下一个节点
            head = head.next;
        } else {// 删除的不是头节点
            // 获取要删除的索引的前一个节点prev
            Node<E> prev = node(index - 1);
            // 获取到要删除的节点，用于返回
            node = prev.next;
            // prev的next指针指向要node的下一个节点next，断掉与要删除节点的联系
            prev.next = prev.next.next;
        }
        size--;
        return node.element;
    }
}
