package com.hxqzzxk.list;

/**
 * 跳表实现
 * 提供了基于跳表的数据存储结构
 * 
 * @param <K> 键类型，必须实现Comparable接口
 * @param <V> 值类型
 */
@SuppressWarnings("unchecked")
public class SkipList<K extends Comparable<K>, V> {
    /**
     * 默认的最大层数
     */
    private static final int DEFAULT_MAX_LEVEL = 32;

    /**
     * 默认的概率，用于生成随机层数
     */
    private static final double DEFAULT_PROBABILITY = 0.5;

    /**
     * 跳表中的节点数量
     */
    private int size;

    /**
     * 跳表的有效层数
     */
    private int level;

    /**
     * 跳表的头节点
     */
    private Node<K, V> head;

    /**
     * 跳表节点类
     * 
     * @param <K> 节点键的类型
     * @param <V> 节点值的类型
     */
    private static class Node<K, V> {
        /**
         * 节点的键
         */
        K key;

        /**
         * 节点的值
         */
        V value;

        /**
         * 指向各层下一个节点的指针数组
         */
        Node<K, V>[] nexts;

        /**
         * 创建一个新的跳表节点
         * 
         * @param key   节点的键
         * @param value 节点的值
         * @param level 节点的层数
         */
        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }

    /**
     * 构造函数，初始化跳表
     */
    public SkipList() {
        // 默认给头节点最大层数
        head = new Node<>(null, null, DEFAULT_MAX_LEVEL);
    }

    /**
     * 清空跳表所有元素
     */
    public void clear() {
        head = null;
        size = 0;
        level = 0;
    }

    /**
     * 获取跳表中存储的元素数量
     * 
     * @return 元素数量
     */
    public int size() {
        return size;
    }

    /**
     * 检查跳表是否为空
     * 
     * @return 如果跳表没有元素则返回 true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据键获取对应的值
     * 
     * @param key 要查找的键
     * @return 对应的值，如果不存在则返回 null
     * @throws IllegalArgumentException 如果键为 null
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // 需要返回的节点，初始值是head
        Node<K, V> node = head;
        // 比较结果
        int cmp = -1;
        // 注意：需要从有效层数开始往下比较，因为层数是从1开始计算的，但是实际的编码中最低层的层数是0，
        // 所以要从level - 1层开始遍历
        for (int i = level - 1; i >= 0; i--) {
            // 默认比较结果
            cmp = -1;
            // 如果下一个节点不为null，且key大于下一个节，则更新node为下一个节点
            while (node.nexts[i] != null && (cmp = compare(key,
                    node.nexts[i].key)) > 0) {
                // 更新node为下一个节点
                // 注意：更新的时候要保留层数i
                node = node.nexts[i];
            }
            // 注意：此时不能返回node.value，因为上面比较的是key与node.nexts[i].key的值，
            // 所以cmp == 0时，要返回node.nexts[i].value
            // 如果key == 下一个节点的值，则返回下一个节点的值
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        return null;
    }

    /**
     * 添加或更新跳表节点
     * 如果键已存在则覆盖值，否则插入新节点
     * 
     * @param key   要添加的键
     * @param value 要添加的值
     * @return 如果键已存在返回旧值，否则返回 null
     * @throws IllegalArgumentException 如果键为 null
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }

        // 被覆盖的节点
        Node<K, V> node = head;
        // 保存要添加的节点的前驱节点
        Node<K, V>[] prevs = new Node[level];
        // 比较结果
        int cmp = -1;
        // 注意：需要从有效层数开始往下比较，因为层数是从1开始计算的，但是实际的编码中最低层的层数是0，
        // 所以要从level - 1层开始遍历
        for (int i = level - 1; i >= 0; i--) {
            // 比较结果
            cmp = -1;
            // 如果下一个节点不为null，且key大于下一个节，则更新node为下一个节点
            while (node.nexts[i] != null && (cmp = compare(key,
                    node.nexts[i].key)) > 0) {
                // 更新node为下一个节点
                // 注意：更新的时候要保留层数i
                node = node.nexts[i];
            }
            // 找到了要添加的key所在的节点
            if (cmp == 0) {
                // 保留该节点的value，用于返回
                V oldValue = node.nexts[i].value;
                // 更新该节点的value
                node.nexts[i].value = value;
                // 返回旧值
                return oldValue;
            }
            // 来到这里，有2种可能：
            // 1. node.nexts[i] ==
            // null，说明新创建的节点应该是要插入到跳表的最后，此时的node就是最后一个非null节点
            // 2. cmp < 0，则说明node.nexts[i].value是大于key的，那node就是在当前层i最后一个小于key的节点
            // 保存当前层的前驱节点，用于后续链接新节点
            // 注意：i是层数，需要保存层数
            prevs[i] = node;
        }
        // 随机生成层数
        int newLevel = randomLevel();
        // 生成新的节点
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        // 生成的层数newLevel跟之前的有效层数level比较，有2种情况：
        // 1. newLevel -1 >= level，则需要从newLevel -
        // 1到level层，都需要更新head的nexts[i]，使其指向newNode
        // 2. newLevel - 1 < level，不需要处理level的更新
        for (int i = newLevel - 1; i >= 0; i--) {
            // i大于等于level的这些层数对应的head.next[i]之前指向的都是null，需要更新，改为指向newNode
            // 注意：这里不是i >= level - 1
            if (i >= level) {
                head.nexts[i] = newNode;
            } else {
                // 注意：这里的添加与链表的添加有异曲同工之妙，只是换到跳表这里，需要维护nexts[i]
                // newNode的next[i]指向的是prevs[i].nexts[i]
                newNode.nexts[i] = prevs[i].nexts[i];
                // prevs[i]指向的是newNode
                prevs[i].nexts[i] = newNode;
            }
        }
        size++;
        // 更新有效层数
        level = Math.max(level, newLevel);
        return null;
    }

    /**
     * 删除指定键的节点
     * 
     * @param key 要删除的键
     * @return 被删除节点的值，如果键不存在则返回 null
     * @throws IllegalArgumentException 如果键为 null
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        // 要删除的节点
        Node<K, V> node = head;
        // 保存要删除的节点的前驱节点
        Node<K, V>[] prevs = new Node[level];
        // 要删除的节点是否存在，默认不存在
        boolean exist = false;
        // 比较结果
        int cmp = -1;
        for (int i = level - 1; i >= 0; i--) {
            while (node.nexts[i] != null && (cmp = compare(key,
                    node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 要删除的节点存在
            if (cmp == 0) {
                exist = true;
            }
            // 要删除的节点的前驱节点，保存有层数i
            // 注意：可能会有一些层数是大于要删除的节点node的层数的，对于这些数据，处理的时候会跳过
            prevs[i] = node;
        }
        // 要删除的节点不存在，直接返回null
        if (!exist) {
            return null;
        }

        // 注意：这里是node.nexts[0]，因为只有nexts[0]是确认不会数组越界的
        Node<K, V> removedNode = node.nexts[0];
        size--;
        // 类似于链表的删除，只是这里要维护nexts[i]
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prevs[i].nexts[i] = removedNode.nexts[i];
        }
        // 更新有效层数
        int newLevel = level;
        // newLevel - 1后，如果head.nexts[newLevel] ==
        // null，则说明newLevel层已经没有数据了，需要更新level
        while (--newLevel >= 0 && head.nexts[newLevel] == null) {
            level = newLevel;
        }
        // 返回被删除的节点的value
        return removedNode.value;
    }

    /**
     * 比较两个键的大小
     * 
     * @param k1 第一个键
     * @param k2 第二个键
     * @return 比较结果
     */
    private int compare(K k1, K k2) {
        return k1.compareTo(k2);
    }

    /**
     * 生成随机的层数
     * 
     * @return 随机生成的层数
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() < DEFAULT_PROBABILITY && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }
}