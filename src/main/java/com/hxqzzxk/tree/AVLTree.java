package com.hxqzzxk.tree;

/**
 * AVL树实现类
 * 每次插入或删除节点后，都会检查并修复树的平衡性，以确保查找、插入和删除的时间复杂度为 O(log n)。
 * 通过四种旋转操作（LL、LR、RR、RL）维持树的平衡
 *
 * @param <E> 元素类型，必须是可比较的
 */
@SuppressWarnings("rawtypes")
public class AVLTree<E extends Comparable> extends BalancedBinarySearchTree<E> {
    /**
     * AVL 树的节点类，继承自 {@link Node}，增加了高度属性用于平衡判断。
     */
    protected static class AVLNode<E> extends Node<E> {
        /**
         * 每个节点的高度，默认为 1。
         * 即使是根节点，其高度也为 1。
         */
        int height = 1;

        /**
         * 创建一个 AVLNode 节点
         *
         * @param element 节点元素
         * @param parent  节点的父节点
         */
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 获取当前节点的平衡因子。
         * 一个节点的平衡因为是左子树高度减去右子树高度
         * 当节点的左子节点为空时，那左边的高度就是0，否则就是获取左子节点的高度
         * 当节点的右子节点为空时，那右边的高度就是0，否则就是获取右子节点的高度
         * 节点的高度 = 左子节点的高度 - 右子节点的高度
         *
         * @return 平衡因子，等于左子树高度减去右子树高度
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新当前节点的高度。
         * 高度为左子节点和右子节点中的最大高度加 1。
         */
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 获取高度更高的子节点。
         * 如果左右子节点高度相同，是左子节点的话就返回左子节点，否则返回右子节点
         *
         * @return 返回高度更高的子节点
         */
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }
    }

    /**
     * 添加新元素后的平衡操作。
     * 插入一个新节点后，从该节点向上检查祖先节点是否失衡，若失衡则进行旋转操作恢复平衡。
     * 添加之后的操作导致的失衡有4种情况，分别是 LL, LR，RR, RL。
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 向上查看节点node的父节点，看一下node的父节点和所有祖先节点是否平衡，
        // 1. 不平衡的话，通过旋转需要修复，直到平衡为止，
        // 2. 平衡的话，那也需要更新每个节点的高度
        // 注意：插入一个节点node后，node的父节点不可能失衡
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    /**
     * 删除节点后的平衡操作。
     * 删除一个节点后，从该节点向上检查祖先节点是否失衡，若失衡则进行旋转操作恢复平衡。
     *
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    /**
     * 创建新的 AVL 树节点。
     *
     * @param element 节点元素
     * @param parent  节点的父节点
     * @return 创建的新节点
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    /**
     * 恢复指定节点的平衡。
     * 插入操作后，插入的节点node的父节点不可能失衡，只有祖父节点及之上的节点可能失衡，
     * 删除操作后，会导致父节点或祖先节点失衡，但是只会有一个节点失衡。
     * 失衡的节点的高度绝对值是2，必然有一个parent节点和child节点，所以可以放心地获取parent和node。
     * 失衡后的旋转包括四种基本旋转类型：LL、LR、RR、RL。
     *
     * @param grand 失衡的祖父节点
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        // L
        if (parent.isLeftChild()) {
            // LL
            // 需要右旋转
            if (node.isLeftChild()) {
                rotateRight(grand);
            } else {
                // LR
                // 需要先对parent进行左旋转
                // 然后对grand进行右旋转
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {// R
            // RL
            // 需要先对parent进行右旋转
            // 再对grand进行左旋转
            if (node.isLeftChild()) {
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR
                // 需要进行左旋转
                rotateLeft(grand);
            }
        }
    }

    /**
     * 旋转后的处理，包括更新节点父子关系和高度。
     *
     * @param grand  原始祖父节点
     * @param parent 旋转后的父节点
     * @param child  旋转过程中涉及的子节点，可能为 null
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新grand，parent，和child的父子关系
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 判断一个节点是否平衡。
     *
     * @param node 需要判断的节点
     * @return 如果平衡返回 true，否则返回 false
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新指定节点的高度。
     *
     * @param node 需要更新高度的节点
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }
}