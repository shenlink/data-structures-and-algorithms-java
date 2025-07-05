package com.hxqzzxk.set;

import com.hxqzzxk.map.HashMap;
import com.hxqzzxk.map.Map;

/**
 * 哈希集合实现类。
 * 该集合基于 HashMap 实现，仅使用键存储元素，值为占位对象。
 *
 * @param <E> 集合中存储的元素类型
 */
public class HashSet<E> implements Set<E> {
    /**
     * 内部使用的哈希映射，键用于存储集合元素，值为占位对象。
     */
    private Map<E, Object> map = new HashMap<>();

    /**
     * 清空集合中的所有元素。
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * 返回当前集合中元素的数量。
     *
     * @return 集合中元素的数量
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * 检查集合是否为空。
     *
     * @return 如果集合没有元素则返回 true
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * 判断集合中是否包含指定元素。
     *
     * @param element 要查找的元素
     * @return 如果集合中存在该元素则返回 true
     */
    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    /**
     * 向集合中添加一个元素。
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        map.put(element, null);
    }

    /**
     * 从集合中删除指定的元素。
     *
     * @param element 要删除的元素
     */
    @Override
    public void remove(E element) {
        map.remove(element);
    }

    /**
     * 遍历集合中的所有元素。
     *
     * @param visitor 访问器，用于处理每个元素
     */
    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                if (visitor.visit(key) || visitor.stop) {
                    return true;
                }
                return false;
            }
        });
    }
}
