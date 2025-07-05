package com.hxqzzxk.set;

import com.hxqzzxk.tree.BinaryTree;
import com.hxqzzxk.tree.RedBlackTree;

/**
 * 基于红黑树实现的集合。
 * 特点是遍历时能够按照元素的自然顺序进行访问。
 *
 * @param <E> 集合中存储的元素类型，必须实现 Comparable 接口
 */
@SuppressWarnings("rawtypes")
public class TreeSet<E extends Comparable> implements Set<E> {
    /**
     * 内部使用的红黑树，用于存储集合元素并保持有序
     */
    private RedBlackTree<E> tree = new RedBlackTree<>();

    /**
     * 清空集合中的所有元素。
     */
    @Override
    public void clear() {
        tree.clear();
    }

    /**
     * 获取当前集合中元素的数量。
     *
     * @return 集合中元素的个数
     */
    @Override
    public int size() {
        return tree.size();
    }

    /**
     * 检查集合是否为空。
     *
     * @return 如果集合没有元素则返回 true
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * 判断集合中是否包含指定元素。
     *
     * @param element 要查找的元素
     * @return 如果集合中存在该元素则返回 true
     */
    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    /**
     * 向集合中添加一个元素。
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        tree.add(element);
    }

    /**
     * 从集合中删除指定的元素。
     *
     * @param element 要删除的元素
     */
    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    /**
     * 遍历集合中的所有元素。
     * 使用内部红黑树的中序遍历方式处理每个元素。
     * 注意：这个顺序不是添加的顺序，而是元素比较的顺序。
     *
     * @param visitor 访问器
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        tree.inOrder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                if (visitor.visit(element) || visitor.stop) {
                    return true;
                }
                return false;
            }
        });
    }
}