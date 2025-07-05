package com.hxqzzxk.set;

import com.hxqzzxk.list.List;
import com.hxqzzxk.list.SingleLinkedList;

/**
 * 基于线性表实现的集合。
 * 可以保证遍历时按照元素添加的顺序进行访问。
 */
public class ListSet<E> implements Set<E> {
    /**
     * 内部使用的线性表，用于存储集合元素。
     */
    private List<E> list = new SingleLinkedList<>();

    /**
     * 清空集合中的所有元素。
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * 获取集合中元素的数量。
     *
     * @return 当前集合中元素的数量
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * 判断集合是否为空。
     *
     * @return 如果集合没有元素则返回 true
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 判断集合是否包含指定元素。
     *
     * @param element 要查找的元素
     * @return 如果集合中存在该元素则返回 true
     */
    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    /**
     * 向集合中添加一个元素。
     * 如果元素已存在，则更新该元素的值；否则将新元素添加到列表末尾。
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        if (list.contains(element)) {
            list.set(list.indexOf(element), element);
        } else {
            list.add(element);
        }
    }

    /**
     * 删除集合中的指定元素。
     *
     * @param element 要删除的元素
     */
    @Override
    public void remove(E element) {
        if (list.contains(element)) {
            list.remove(list.indexOf(element));
        }
    }

    /**
     * 遍历集合中的所有元素。
     * 按照插入顺序依次处理每个元素，并打印到控制台。
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i)) || visitor.stop) {
                return;
            }
        }
    }
}
