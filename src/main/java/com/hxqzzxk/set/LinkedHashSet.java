package com.hxqzzxk.set;

import com.hxqzzxk.map.LinkedHashMap;
import com.hxqzzxk.map.Map;

/**
 * 顺序哈希集合实现类。
 * 基于 LinkedHashMap 实现，能够保证遍历顺序与元素插入顺序一致。
 *
 * @param <E> 集合中存储的元素类型
 */
public class LinkedHashSet<E> implements Set<E> {
    /**
     * 内部使用的哈希映射，键用于存储集合元素，值为占位对象。
     */
    private Map<E, Object> map = new LinkedHashMap<>();

    /**
     * 清空集合中的所有元素。
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * 获取集合元素个数。
     *
     * @return 当前集合中元素的数量
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * 判断集合是否为空。
     *
     * @return 如果集合没有元素则返回 true
     */
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * 是否包含某个元素。
     *
     * @param element 要查找的元素
     * @return 如果集合中存在该元素则返回 true
     */
    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    /**
     * 添加元素到集合中。
     *
     * @param element 要添加的元素
     */
    @Override
    public void add(E element) {
        map.put(element, null);
    }

    /**
     * 删除集合中的指定元素。
     *
     * @param element 要删除的元素
     */
    @Override
    public void remove(E element) {
        map.remove(element);
    }

    /**
     * 遍历集合。
     * 使用访问器对集合中的每一个元素进行处理。
     *
     * @param visitor 元素访问器
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
