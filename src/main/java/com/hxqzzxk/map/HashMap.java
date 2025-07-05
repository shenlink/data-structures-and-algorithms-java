package com.hxqzzxk.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 哈希表实现，基于红黑树处理冲突。
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HashMap<K, V> implements Map<K, V> {
    /**
     * 红色节点标识常量，值为 false。
     */
    protected static final boolean RED = false;

    /**
     * 黑色节点标识常量，值为 true。
     */
    protected static final boolean BLACK = true;

    /**
     * table 数组的默认容量，默认为 16。
     */
    protected static final int DEFAULT_CAPACITY = 1 << 4;

    /**
     * 负载因子，默认值为 0.75。
     * 当元素数量超过负载因子乘以数组长度时，进行扩容。
     */
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 当前哈希表中键值对的数量
     */
    protected int size;

    /**
     * 哈希表的桶数组，每个元素指向一个红黑树的根节点
     */
    protected Node<K, V>[] table;

    /**
     * 默认构造函数，使用默认的容量初始化哈希表。
     */
    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * 表示哈希表中的节点，用于构建红黑树。
     *
     * @param <K> 键的类型
     * @param <V> 值的类型
     */
    protected static class Node<K, V> {
        /**
         * 键的哈希码
         */
        int hash;

        /**
         * 存储的键
         */
        K key;

        /**
         * 存储的值
         */
        V value;

        /**
         * 节点的颜色（RED 或 BLACK）
         */
        boolean color = RED;

        /**
         * 左子节点
         */
        Node<K, V> left;

        /**
         * 右子节点
         */
        Node<K, V> right;

        /**
         * 父节点
         */
        Node<K, V> parent;

        /**
         * 构造一个新的节点。
         *
         * @param key    键
         * @param value  值
         * @param parent 父节点
         */
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        /**
         * 判断该节点是否有两个子节点。
         *
         * @return 如果左右子节点都存在则返回 true
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         * 判断该节点是否是左子节点。
         *
         * @return 如果当前节点是其父节点的左子节点则返回 true
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 判断该节点是否是右子节点。
         *
         * @return 如果当前节点是其父节点的右子节点则返回 true
         */
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         * 获取兄弟节点。
         *
         * @return 当前节点的兄弟节点
         */
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        /**
         * 返回节点的字符串表示。
         *
         * @return 节点的字符串形式，格式为 "key-value"
         */
        @Override
        public String toString() {
            return key + "-" + value;
        }
    }

    /**
     * 清空哈希表中的所有键值对。
     */
    @Override
    public void clear() {
        if (size == 0) {
            return;
        }
        // 清空table数组
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * 获取哈希表中键值对的数量。
     *
     * @return 哈希表中键值对的数量
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断哈希表是否为空。
     *
     * @return 如果哈希表中没有键值对则返回 true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 将指定键映射到指定值。
     *
     * @param key   要插入的键
     * @param value 要插入的值
     * @return 与键关联的旧值，如果没有则返回 null
     */
    @Override
    public V put(K key, V value) {
        // 保证容量
        resize();

        // 计算出key所在的table数组的索引
        int index = index(key);
        // 获取根节点
        Node<K, V> root = table[index];
        // 根节点为空，直接添加根节点
        if (root == null) {
            table[index] = createNode(key, value, null);
            size++;
            // 修复红黑树的性质
            fixAfterPut(table[index]);
            return null;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false;
        // 在红黑树中寻找合适节点的流程：
        // 先计算出key的hash值h1，然后计算出每次
        // 访问到的节点的hash值h2，与key的hash值进行比较，
        // 如果h1 > h2，说明key应该插入到右子树，如果h1 < h2，
        // 则说明key应该插入到左子树，如果h1 == h2，则需要比较key，
        // 首先先Object.equals(k1, k2)，如果返回true，则说明key
        // 与节点的key相同，则覆盖该节点，
        // 如果此时Object.equals(k1, k2)返回false，则要看k1与k2
        // 能不能比较，如果能，则调用比较接口，否则，去红黑树中的左子树
        // 和右子树中找该节点，也就是扫描剩下的子树中所有的节点，如果找到了，
        // 则覆盖，如果没有，则使用内存地址比较。
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {

            } else if (searched) {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                if (node.left != null && (result = node(node.left, k1)) != null
                        || node.right != null && (result = node(node.right,
                                k1)) != null) {
                    node = result;
                    cmp = 0;
                } else {
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.hash = h1;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        fixAfterPut(newNode);
        return null;
    }

    /**
     * 通过键获取对应的值。
     *
     * @param key 要查找的键
     * @return 对应的值，如果没有找到则返回 null
     */
    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    /**
     * 删除指定键对应的键值对。
     *
     * @param key 要删除的键
     * @return 与键关联的值，如果没有找到则返回 null
     */
    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    /**
     * 判断哈希表是否包含指定的键。
     *
     * @param key 要检查的键
     * @return 如果哈希表包含该键则返回 true
     */
    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     * 判断哈希表是否包含指定的值。
     *
     * @param value 要检查的值
     * @return 如果哈希表包含该值则返回 true
     */
    @Override
    public boolean containsValue(V value) {
        if (size == 0) {
            return false;
        }

        // 对table数组中的所有的红黑树进行层序遍历，找到了，就返回true，
        // 最后还是没有找到，返回false
        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    /**
     * 遍历哈希表中的所有键值对。
     * 使用层序遍历的方式输出每个节点的信息。
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }

            Queue<Node<K, V>> queue = new LinkedList<>();
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value) || visitor.stop) {
                    return;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 创建一个新的节点
     *
     * @param key    要存储的键
     * @param value  要存储的值
     * @param parent 父节点
     * @return 新创建的节点
     */
    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    /**
     * 移除节点后的调整操作
     *
     * @param replaceNode 原先被删除的节点，因为度为2，使用后继节点来替代
     * @param removedNode 实际被删除的节点
     */
    protected void afterRemove(Node<K, V> replaceNode, Node<K, V> removedNode) {
    }

    /**
     * 扩容方法，当元素数量超过负载因子乘以数组长度时调用。
     * 该方法会创建一个新的两倍大小的桶数组，
     * 并将原有桶数组中的所有节点重新分布到新的桶数组中。
     */
    private void resize() {
        if ((float) size / table.length <= DEFAULT_LOAD_FACTOR) {
            return;
        }

        Node<K, V>[] oldTable = table;
        table = new Node[table.length << 1];
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (table[i] == null) {
                continue;
            }
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                moveNode(node);
            }

        }
    }

    /**
     * 移动节点，可以当成是添加节点
     *
     * @param newNode 要移动的节点
     */
    private void moveNode(Node<K, V> newNode) {
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode);
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {

            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        } while (node != null);
        newNode.parent = parent;
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        fixAfterPut(newNode);
    }

    /**
     * 删除节点
     *
     * @param node 要删除的节点
     * @return 与节点关联的值，如果没有找到则返回 null
     */
    protected V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        Node<K, V> originNode = node;
        size--;
        V oldValue = node.value;
        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            node = s;
        }

        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            fixAfterRemove(replacement);
        } else if (node.parent == null) {
            table[index] = null;
        } else {
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            fixAfterRemove(node);
        }

        afterRemove(originNode, node);

        return oldValue;
    }

    /**
     * 查找指定节点的后继节点。
     * 后继节点定义为红黑树中大于当前节点的最小节点。
     * 如果当前节点有右子节点，则后继节点是右子树中最左边的节点；
     * 如果没有右子树，则从父节点向上查找，直到当前节点是其父节点的左子节点。
     *
     * @param node 要查找后继节点的节点
     * @return 指定节点的后继节点
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null)
            return null;

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 根据给定的键获取对应的节点
     * 此方法用于在散列表中查找特定的节点，如果找到则返回该节点，否则返回null
     * 它首先通过计算键的索引来定位散列表中的位置，然后调用内部方法node递归地搜索匹配的节点
     * 
     * @param key 要查找的键，用于定位节点
     * @return 返回找到的节点，如果未找到则返回null
     */
    private Node<K, V> node(K key) {
        // 通过键获取散列表中的索引，并将该索引处的节点赋值给root
        Node<K, V> root = table[index(key)];
        // 如果root为null，说明散列表中该索引处没有节点，返回null
        // 否则，调用递归方法node在root及其子节点中搜索匹配的节点
        return root == null ? null : node(root, key);
    }

    /**
     * 根据键查找对应的节点。
     *
     * @param node 当前遍历的根节点
     * @param k1   要查找的键
     * @return 查找到的节点，如果不存在则返回 null
     */
    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        int cmp = 0;
        Node<K, V> result = null;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                return result;
            } else {
                node = node.left;
            }
        }

        return null;
    }

    /**
     * 根据key生成对应的索引（在桶数组中的位置）
     *
     * @param key 要计算索引的键
     * @return 键对应的索引位置
     */
    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    /**
     * 计算键的哈希值
     *
     * @param key 要计算哈希值的键
     * @return 键的哈希值
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    /**
     * 获取节点在桶数组中的索引位置
     *
     * @param node 要计算索引的节点
     * @return 节点对应的索引位置
     */
    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    /**
     * 删除后操作，修复红黑树的性质
     *
     * @param node 被删除的节点
     */
    private void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     * 添加后操作，修复红黑树的性质
     *
     * @param node 新添加的节点
     */
    private void fixAfterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            fixAfterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 左旋转操作。
     * 左旋转是指以某个节点作为旋转点，将其右子节点提升为新的父节点，
     * 原来的父节点变为新父节点的左子节点。
     * 该操作用于维护红黑树的平衡性。
     *
     * @param grand 需要进行左旋转的原始父节点（祖父节点）
     */
    private void rotateLeft(Node<K, V> grand) {
        // 获取原始父节点的右子节点，这是新的父节点
        Node<K, V> parent = grand.right;
        // 获取新父节点的左子节点，这将是旧父节点的右子节点
        Node<K, V> child = parent.left;

        // 将新父节点的左子节点赋予旧父节点的右子节点位置
        grand.right = child;
        // 将旧父节点设置为新父节点的左子节点
        parent.left = grand;

        // 调用afterRotate方法更新各个节点的父节点关系
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转操作。
     * 右旋转是指以某个节点作为旋转点，将其左子节点提升为新的父节点，
     * 原来的父节点变为新父节点的右子节点。
     * 该操作用于维护红黑树的平衡性。
     *
     * @param grand 需要进行右旋转的原始父节点（祖父节点）
     */
    private void rotateRight(Node<K, V> grand) {
        // 获取原始父节点的左子节点，这是新的父节点
        Node<K, V> parent = grand.left;
        // 获取新父节点的右子节点，这将是旧父节点的左子节点
        Node<K, V> child = parent.right;

        // 将新父节点的右子节点赋予旧父节点的左子节点位置
        grand.left = child;
        // 将旧父节点设置为新父节点的右子节点
        parent.right = grand;

        // 调用afterRotate方法更新各个节点的父节点关系
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转后修复节点的父子关系。
     * 此方法处理旋转之后的节点连接，包括更新父节点与子节点的引用。
     *
     * @param grand  原始祖父节点，在旋转后将成为子树的一部分
     * @param parent 在旋转中成为新的父节点
     * @param child  旋转过程中涉及的子节点，可能为 null
     */
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    /**
     * 设置节点的颜色
     * 
     * @param node  待设置颜色的节点
     * @param color 节点的新颜色
     * @return 返回颜色被设置颜色后的节点
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    /**
     * 将节点设置为红色
     *
     * @param node 要设置颜色的节点
     * @return 设置为红色后的节点
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 将节点设置为黑色
     *
     * @param node 要设置颜色的节点
     * @return 设置为黑色后的节点
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 获取节点的颜色
     *
     * @param node 要获取颜色的节点
     * @return 节点的颜色，如果节点为null则返回黑色
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断节点是否是黑色
     *
     * @param node 要判断颜色的节点
     * @return 如果节点是黑色或null则返回true，否则返回false
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否是红色
     *
     * @param node 要判断颜色的节点
     * @return 如果节点是红色则返回true，否则返回false
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }
}