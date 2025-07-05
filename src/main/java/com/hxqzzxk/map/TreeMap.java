package com.hxqzzxk.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 基于树的Map，底层使用红黑树实现
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TreeMap<K extends Comparable, V> implements Map<K, V> {
    /**
     * 红色节点标识
     */
    private static final boolean RED = false;

    /**
     * 黑色节点标识
     */
    private static final boolean BLACK = true;

    /**
     * 映射键值对的数量
     */
    private int size;

    /**
     * 红黑树的根节点
     */
    private Node<K, V> root;

    /**
     * 红黑树节点类，表示红黑树中的一个键值对节点
     *
     * @param <K> 键的类型
     * @param <V> 值的类型
     */
    private static class Node<K, V> {
        /**
         * 键值对的键
         */
        K key;

        /**
         * 键值对的值
         */
        V value;

        /**
         * 节点的颜色，RED表示红色，BLACK表示黑色
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
         * 构造方法
         *
         * @param key    节点的键
         * @param value  节点的值
         * @param parent 父节点
         */
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 判断当前节点是否有两个子节点
         *
         * @return 如果左右子节点都存在返回true，否则返回false
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         * 判断当前节点是否是父节点的左子节点
         *
         * @return 如果当前节点是父节点的左子节点返回true，否则返回false
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 判断当前节点是否是父节点的右子节点
         *
         * @return 如果当前节点是父节点的右子节点返回true，否则返回false
         */
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         * 获取当前节点的兄弟节点
         *
         * @return 返回兄弟节点，如果没有父节点或没有兄弟节点则返回null
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
         * 获取节点的字符串表示
         * 
         * @return 返回节点的字符串表示
         */
        @Override
        public String toString() {
            return key.toString() + "-" + value.toString();
        }
    }

    /**
     * 清空映射中的所有键值对
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 获取映射中键值对的数量
     *
     * @return 键值对数量
     */
    public int size() {
        return size;
    }

    /**
     * 判断映射是否为空
     *
     * @return 如果映射为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加键值对到映射中
     *
     * @param key   要添加的键，不能为空
     * @param value 要添加的值
     * @return 与键关联的旧值，如果没有则返回null
     */
    @Override
    public V put(K key, V value) {
        // key不能为空
        checkKey(key);

        // 添加第一个节点
        if (root == null) {
            // 创建根节点
            root = new Node<>(key, value, null);
            size++;

            // 新添加节点之后的处理
            // 修复红黑树的性质
            afterPut(root);
            return null;
        }

        // 添加的不是第一个节点
        // 找到父节点
        // 默认父节点根节点
        Node<K, V> parent = root;
        // 最后找到的节点
        Node<K, V> node = root;
        // 比较结果
        int cmp = 0;
        do {
            // 比较结果
            cmp = compare(key, node.key);
            // 保存当前节点为父节点，除了要添加的节点已经在树中的情况，
            // 另外两种情况，添加的时候需要获取到加节点的父节点，在最后
            // 一次循环的时候，newNode = node.right或newNode = node.left，
            // 此时newNode == null，此时，在node = node.right或node = node.left
            // 之前就保存下来的node就是这个newNode的父节点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        // 修复红黑树的性质
        afterPut(newNode);
        return null;
    }

    /**
     * 根据键获取对应的值
     *
     * @param key 要查找的键
     * @return 与键关联的值，如果不存在则返回null
     */
    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    /**
     * 删除指定键的键值对
     *
     * @param key 要删除的键
     * @return 被删除的值，如果不存在则返回null
     */
    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    /**
     * 判断映射是否包含指定的键
     *
     * @param key 要检查的键
     * @return 如果映射包含该键则返回true，否则返回false
     */
    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    /**
     * 判断映射是否包含指定的值
     *
     * @param value 要检查的值
     * @return 如果映射包含该值则返回true，否则返回false
     */
    @Override
    public boolean containsValue(V value) {
        if (root == null)
            return false;

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);

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

        return false;
    }

    /**
     * 遍历映射中的所有元素
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        traversal(root, visitor);
    }

    /**
     * 使用中序遍历访问红黑树节点
     * 通常我们期望通过TreeMap获取按顺序排列的元素
     *
     * @param node 当前访问的节点
     */
    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) {
            return;
        }

        traversal(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    /**
     * 删除红黑树节点
     *
     * @param node 要删除的节点
     * @return 删除节点关联的值
     */
    private V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }

        return oldValue;
    }

    /**
     * 删除节点后，修复红黑树的性质
     *
     * @param node 删除或替换后的当前节点
     */
    private void afterRemove(Node<K, V> node) {
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
                    afterRemove(parent);
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
                    afterRemove(parent);
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
     * 获取红黑树的前驱节点
     *
     * @param node 当前节点
     * @return 返回前驱节点
     */
    @SuppressWarnings("unused")
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 前驱节点在左子树当中（left.right.right.right....）
        Node<K, V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 获取红黑树后继节点
     *
     * @param node 当前节点
     * @return 返回后继节点
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

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
     * 根据key获取到红黑树的节点
     *
     * @param key 要查找的键
     * @return 返回红黑树的节点
     */
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp == 0) {
                return node;
            }
            if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 添加之后的操作，修复红黑树的性质
     *
     * @param node 新添加的节点
     */
    private void afterPut(Node<K, V> node) {
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
            afterPut(grand);
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
     * 左旋转
     *
     * @param grand 需要左旋的节点的祖父节点
     */
    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 右旋转
     *
     * @param grand 需要右旋的节点的祖父节点
     */
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转之后的处理
     * 修复grand，parent，child之间的父子关系
     *
     * @param grand  旋转前的祖父节点
     * @param parent 旋转后的子树根节点
     * @param child  子节点
     */
    private void afterRotate(Node<K, V> grand, Node<K, V> parent,
            Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    /**
     * 设置节点颜色
     *
     * @param node  节点
     * @param color 颜色
     * @return 返回设置颜色后的节点
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    /**
     * 将节点设为红色
     *
     * @param node 节点
     * @return 返回红色的节点
     */
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    /**
     * 将节点设为黑色
     *
     * @param node 节点
     * @return 返回黑色的节点
     */
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 获取节点的颜色
     *
     * @param node 节点
     * @return 返回节点的颜色
     */
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    /**
     * 判断节点是否为黑色
     *
     * @param node 节点
     * @return 如果节点为黑色返回true，否则返回false
     */
    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否为红色
     *
     * @param node 节点
     * @return 如果节点为红色返回true，否则返回false
     */
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    /**
     * 比较两个键的大小
     *
     * @param e1 第一个键
     * @param e2 第二个键
     * @return 返回e1和e2比较的结果
     */
    private int compare(K e1, K e2) {
        return e1.compareTo(e2);
    }

    /**
     * 检查键是否为空
     *
     * @param key 键
     */
    private void checkKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }
}
