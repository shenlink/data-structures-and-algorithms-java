package com.hxqzzxk.tree;

/**
 * 二叉搜索树实现。
 * 支持添加、删除和查找操作，元素必须是可比较的。
 * 
 * @param <E> 元素类型，必须是可比较的
 */
@SuppressWarnings("rawtypes")
public class BinarySearchTree<E extends Comparable> extends BinaryTree<E> {
    /**
     * 添加一个元素到二叉搜索树中。
     *
     * @param element 要添加的元素，不能为null
     */
    public void add(E element) {
        // 元素不能为null
        checkElement(element);
        // 根节点是null，那就是添加的第一个节点
        if (root == null) {
            // 创建根节点
            root = createNode(element, null);
            // 数量加1
            size++;
            afterAdd(root);
            return;
        }

        // 从根节点开始找
        Node<E> node = root;
        // 找到的node的父节点，这位默认是root，是因为node的父节点为
        // null的情况就是添加根节点，这在上面处理了
        Node<E> parent = root;
        // 元素与二叉树的元素比较结果
        int cmp = 0;
        do {
            cmp = compare(element, node.element);
            // 记录当前节点为parent，因为后面要添加的节点的父节点就是这个parent
            // 添加节点的时候，是需要知道这个节点的父节点是哪个，在循环的过程中，
            // 遇到的节点都可能是最后添加的节点的父节点
            parent = node;
            // 比较结果大于0，往右边找
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {// 比较结果小于0，往左边找
                node = node.left;
            } else {// 元素与node.element相等，说明要添加的元素已经存在，直接覆盖
                node.element = element;
                return;
            }
        } while (node != null);// 结束循环的条件是node为null，添加的是叶子节点
        // 创建节点，parent就是添加节点的父节点
        Node<E> newNode = createNode(element, parent);
        // 挂载节点到树上
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 在添加元素后执行的回调方法。
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {
    }

    /**
     * 删除指定的元素。
     *
     * @param element 要删除的元素
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 删除指定的节点。
     *
     * @param node 要删除的节点
     */
    private void remove(Node<E> node) {
        // 树中没有空节点，传入的节点为空，直接返回
        if (node == null) {
            return;
        }

        size--;

        // 度为2的节点
        // 删除度为2的节点，实际上是找到这个节点的前驱节点或后继节点s，
        // 使用s的元素值覆盖node的元素值，然后，删除s
        // 这样就可以把删除度为2的节点的操作合并到删除度为1的节点的操作上了
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> s = predecessor(node);
            // 使用后继节点替代node
            node.element = s.element;
            // 改为删除后继节点
            node = s;
        }
        // 度为1或0的节点
        // 能来到这里，说明前面节点的度为2的情况已经被处理了，现在这里，节点的度只能是0或1
        // 一：如果删除的节点的度为1，分为两种情况，
        // 1. 如果node是根节点，那直接让replacement替换node，让这个子节点成为根节点
        // 2. 如果node不是根节点，那就需要找到该节点的子节点，然后，用该子节点替换node，
        // 这里是优先考虑左子节点
        // 二： 如果没有子节点，那就是node的度为0，这种情况很简单，分为两种情况：
        // 1. 如果node是根节点，那直接root = null
        // 2. 如果node不是根节点，那就先判断出node节点是node的父节点的左子节点还是右子节点，
        // 把父节点的相应的子节点置为null即可
        Node<E> replacement = node.left != null ? node.left : node.right;
        // 度为1的节点
        // 删除度为1的节点，核心是让replacement的代替node节点，
        // 第一步，先让replacement的parent指向node的parent
        // 第二步：node是根节点的话，直接让replacement成为根节点，
        // 否则，让node的parent指向replacement，注意：要保持原先的方向
        // 原来的node是左子节点的话，那replacement也得是左子节点，
        // 同理，node是右子节点的话，replacement也得是右子节点
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node.parent.left == node) {// 要保持原先的树结构，所以要判断node
                // 是左子节点还是右子节点
                node.parent.left = replacement;
            } else {// 要保持原先的树结构，所以要判断node是左子节点还是右子节点
                node.parent.right = replacement;
            }
            afterRemove(replacement);
        } else if (node.parent == null) {// 度为0的节点，且是根节点，那就是只有一个节点了，直接删除根节点即可
            // 度为0的节点，且是根节点
            root = null;
            afterRemove(node);
        } else {
            // 度为0的节点，且不是根节点
            // 注意：要保持方向性，所以要判断node是左子节点还是右子节点，
            // node是左子节点的话，那就删除左子节点，否则，删除右子节点
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    /**
     * 在删除节点后执行的回调方法。
     *
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) {
    }

    /**
     * 判断二叉搜索树中是否包含某个元素。
     *
     * @param element 要检查的元素
     * @return 如果包含该元素返回true，否则返回false
     */
    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 查找元素所在的节点。
     *
     * @param element 要查找的元素
     * @return 元素所在的节点，如果找不到返回null
     */
    private Node<E> node(E element) {
        // 从根节点开始找起
        // 比较结果
        Node<E> node = root;
        int cmp = 0;
        // node == null就是没有找到，结束循环
        while (node != null) {
            // cmp > 0，往右边找，cmp < 0，往左边找，cmp = 0，说明找到了
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        // 没找到，返回null
        return null;
    }

    /**
     * 比较两个元素。
     *
     * @param e1 第一个元素
     * @param e2 第二个元素
     * @return 比较结果，e1大于e2返回正数，小于返回负数，等于返回0
     */
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }

    /**
     * 检查元素是否为null。
     *
     * @param element 要检查的元素
     * @throws IllegalArgumentException 如果元素为null
     */
    private void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}