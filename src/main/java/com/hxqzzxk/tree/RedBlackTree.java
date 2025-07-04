package com.hxqzzxk.tree;

/**
 * 红黑树实现类
 * 红黑树是一种自平衡的二叉搜索树，保证了树的高度大约在 2log(n) 以内，
 * 提供了高效的插入、删除和查找操作。
 *
 * 特性：
 * 1. 每个节点要么是红色，要么是黑色
 * 2. 根节点是黑色
 * 3. 每个叶子节点（NIL 节点）是黑色
 * 4. 如果一个节点是红色，则它的两个子节点都是黑色
 * 红色节点的父节点都是黑色节点
 * 从根节点到叶子节点的所有路径上不能有2个连续的红色节点
 * 5. 从任一节点到其每个叶子的所有路径都包含相同的黑色节点
 *
 * @param <E> 元素类型，必须是可比较的
 */
@SuppressWarnings("rawtypes")
public class RedBlackTree<E extends Comparable> extends BalancedBinarySearchTree<E> {
    /**
     * 节点的颜色：红色
     */
    private static final boolean RED = false;

    /**
     * 节点的颜色：黑色
     */
    private static final boolean BLACK = true;

    /**
     * 红黑树节点类
     */
    private static class RedBlackNode<E> extends Node<E> {
        /**
         * 默认是红色
         */
        boolean color = RED;

        /**
         * 构造函数
         *
         * @param element 节点元素
         * @param parent  父节点
         */
        public RedBlackNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }

    /**
     * 添加之后的操作，这个添加操作是二叉搜索树的添加操作
     * 首先，排除添加的节点已经在树中的情况，添加的节点都是
     * 添加在树的叶子节点下面
     * 添加有12种情况：
     * 1. 其中4中情况是添加的节点的父节点是黑色节点，
     * 这种情况，直接不添加，不用修复红黑树的性质
     * 2. 还有4种情况，添加的节点导致了红黑树对于的4阶B树上溢
     * 就是添加的节点node的父节点是红色节点，而且，父节点的兄弟
     * 节点也是红色节点，这种情况需要修复红黑树的性质，判断条件是
     * uncle节点是红色节点，修复方法是把parent和uncle都变黑，
     * grand染红，并向上合并，grand向上合并可以看成是新添加的
     * 节点的afterAdd操作
     * 3. 最后4种情况，uncle不是红色，需要进行相应的旋转操作
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // 先获取node的祖父节点
        Node<E> parent = node.parent;

        // 如果node是根节点，把根节点染成黑色，然后直接返回
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回，对应上面提到的前4种情况
        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        // uncle是红色节点，是中间的4种情况
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 这里grand向上合并后，可能导致一直上溢，
            // 所以，要写成递归的形式，如果上溢到根节点，
            // 把根节点染黑即可
            afterAdd(grand);
            return;
        }
        // 最后4种情况，uncle是黑色节点
        // 注意：这里需要把grand染成红色，
        // 但是，这个操作在之前已经完成了
        // 注意：这里，接替parent成为这段
        // 二叉树的根节点的节点需要染成黑色
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                black(parent);
            } else {
                // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            // RL
            if (node.isLeftChild()) {
                // RL
                black(node);
                rotateRight(grand);
            } else {
                // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 删除后的操作，这里的删除是二叉搜索树的删除操作
     * 首先，实际被删除的节点只有度为0的节点和度为1的节点
     * 注意：这里讨论的是红黑树，在红黑树中，最后被删除的节点
     * 都是对于的4阶B树的叶子节点
     * 删除有2种情况：
     * 一. 删除的节点是红色节点，直接删除或染成黑色
     * 1. 删除的是红色节点是叶子节点，直接删除
     * 2. 删除的红色节点是接替的度为1的节点，需要染成黑色
     * 二. 删除的节点是黑色节点，分为3种情况
     * 1. 拥有两个红色节点的黑色节点，不用管，
     * 因为会找它的子节点来替换删除，也就是红色节点，
     * 就是情况一，所以不用管
     * 2. 拥有一个红色子节点的黑色节点，判断条件是：
     * 用以替换的子节点是，这个替换操作在二叉搜索树
     * 中已经完成了，所以把替换的节点染成黑色即可
     * 3. 删除的是黑色叶子节点，这里又分为3种情况：
     * ①：sibling是黑色节点，如果sibling又至少一个
     * 红色子节点，进行旋转操作，旋转之后的中心节点(新的parent)
     * 继承parent的颜色，旋转之后的左右节点染为
     * ②：sibling是红色节点，sibling染成黑色，
     * parent染成红色，进行旋转，就会变成sibling是黑色的情况
     * ③：sibling没有红色子节点，将parent染成黑色，
     * sibling染成红色，即可保持红黑树的性质，这里还有
     * 一种情况，如果parent也是黑色，那就会导致parent下溢，
     * 这时只需要把parent当做被删除的节点处理即可
     *
     * @param node 被删除的节点
     */
    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的是红色叶子节点，或者用以取代被删除节点
        // 的子节点是红色，染黑该节点，然后直接返回，不做任何调整
        if (isRed(node)) {
            black(node);
            return;
        }

        // 获取节点的父节点
        Node<E> parent = node.parent;
        // 父节点为null，也就是删除的节点是根节点，直接返回
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点，下溢
        // 判断被删除的node是左子节点还是右子节点
        // 注意：parent.left == null是给二叉搜索树的afterRemove用的，
        // node.isLeftChild()是给下面的递归删除的afterRemove()用的
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        // 如果被删除的节点是左子节点
        if (left) {
            // 如果sibling是红色节点，就是对应的第二种情况中的第三种情况的第②种，
            // 需要把这种情况变成sibling是黑色节点的情况
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            // sibling没有一个红色子节点，那会产生下溢
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 注意：这里需要判断父节点颜色，因为父节点是黑色的话，会造成下溢
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    // 父节点是黑色节点，当成是删除父节点，重新调用afterRemove方法
                    afterRemove(parent);
                }
            } else {
                // sibling的右子节点是黑色，旋转之后，变成sibling的左子节点是红色
                if (isBlack(sibling.right)) {
                    rotateLeft(sibling);
                    sibling = sibling.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else {
            // sibling是红色，需要变成sibling是黑色的情况
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            // 与上面情况类似，是左右对称的
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else {
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = sibling.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     * 创建新节点
     *
     * @param element 节点元素
     * @param parent  父节点
     * @return 新创建的节点
     */
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RedBlackNode<>(element, parent);
    }

    /**
     * 设置节点的颜色
     *
     * @param node  要设置颜色的节点
     * @param color 颜色（RED 或 BLACK）
     * @return 设置颜色后的节点
     */
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null)
            return node;
        ((RedBlackNode<E>) node).color = color;
        return node;
    }

    /**
     * 将节点设为红色
     *
     * @param node 要设为红色的节点
     * @return 设为红色后的节点
     */
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    /**
     * 将节点设为黑色
     *
     * @param node 要设为黑色的节点
     * @return 设为黑色后的节点
     */
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * 获取节点的颜色
     *
     * @param node 要获取颜色的节点
     * @return 节点颜色（BLACK 或 RED）
     */
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RedBlackNode<E>) node).color;
    }

    /**
     * 判断节点是否为黑色
     *
     * @param node 要判断颜色的节点
     * @return 如果节点是黑色返回 true，否则返回 false
     */
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    /**
     * 判断节点是否为红色
     *
     * @param node 要判断颜色的节点
     * @return 如果节点是红色返回 true，否则返回 false
     */
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }
}
