package com.hxqzzxk.tree;

/**
 * 平衡二叉搜索树实现。
 * 提供左旋和右旋操作以维持树的平衡，并修复旋转后的节点关系。
 *
 * 该类基于BinarySearchTree实现，通过旋转操作保持树的平衡性，
 * 适用于AVL树、红黑树等需要平衡操作的场景.
 * 
 * @param <E> 元素类型，必须是可比较的
 */
@SuppressWarnings("rawtypes")
public class BalancedBinarySearchTree<E extends Comparable> extends BinarySearchTree<E> {
    /**
     * 对某个节点进行左旋转操作。
     * 左旋转会调整祖父节点（grand）及其右子节点（parent）的位置，使 parent 成为新的父节点。
     * 具体步骤：
     * - parent 就是 grand 的右子节点
     * - grand.right = parent.left
     * - parent.left = grand
     * - 然后修复节点的父子关系
     * 更加抽象地看，对一个节点 node 进行左旋转：
     * - 先找到它的右子节点（在 AVL 树和红黑树中通常不为空）
     * - 然后 node.right = node.right.left
     * - node.right.parent = node
     *
     * @param grand 需要左旋转的节点
     */
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 对某个节点进行右旋转操作。
     * 右旋转会调整祖父节点（grand）及其左子节点（parent）的位置，使 parent 成为新的父节点。
     * 具体步骤：
     * - parent 就是 grand 的左子节点
     * - grand.left = parent.right
     * - parent.right = grand
     * - 然后修复节点的父子关系
     * 更加抽象地看，对一个节点 node 进行右旋转：
     * - 先找到它的左子节点（在 AVL 树和红黑树中通常不为空）
     * - 然后 node.left = node.left.right
     * - node.left.parent = node
     *
     * @param grand 需要右旋转的节点
     */
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
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
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新parent
        // 旋转后，parent就成为了这部分二叉树的根节点了，
        // 所以要更新parent.parent为grand,parent
        parent.parent = grand.parent;
        // 连接parent.parent和parent
        // grand是左子节点，所以grand.parent.left = parent
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) { // grand是右子节点，所以grand.parent.right
            // = parent
            grand.parent.right = parent;
        } else {// grand不是左子节点也不是右子节点，那就是根节点
            root = parent;
        }

        // 更新child
        if (child != null) {
            child.parent = grand;
        }
        // 更新grand
        grand.parent = parent;
    }
}
