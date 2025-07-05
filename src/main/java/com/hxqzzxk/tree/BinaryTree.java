package com.hxqzzxk.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树基类
 *
 * @param <E> 二叉树中存储的元素类型
 */
public class BinaryTree<E> {
    /**
     * 二叉树的节点总数
     */
    protected int size;
    /**
     * 二叉树的根节点
     */
    protected Node<E> root;

    /**
     * 二叉树节点内部类
     *
     * @param <E> 节点中存储的元素类型
     */
    protected static class Node<E> {
        /**
         * 二叉树的节点的元素
         */
        E element;
        /**
         * 二叉树的节点的左子节点
         */
        Node<E> left;
        /**
         * 二叉树的节点的右子节点
         */
        Node<E> right;
        /**
         * 二叉树的节点的父节点
         */
        Node<E> parent;

        /**
         * 构造一个新的节点
         *
         * @param element 节点中存储的元素
         * @param parent  父节点
         */
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        /**
         * 判断该节点是否是叶子节点
         *
         * @return 如果是叶子节点返回 true，否则返回 false
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 判断该节点是否有两个子节点
         *
         * @return 如果有左右两个子节点返回 true，否则返回 false
         */
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        /**
         * 判断该节点是否是左子节点
         *
         * @return 如果是左子节点返回 true，否则返回 false
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 判断该节点是否是右子节点
         *
         * @return 如果是右子节点返回 true，否则返回 false
         */
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         * 获取该节点的兄弟节点
         *
         * @return 如果存在兄弟节点则返回兄弟节点，否则返回 null
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }

    /**
     * 抽象访问者类，用于遍历二叉树元素
     * 
     * @param <E> 二叉树节点中存储的元素类型
     */
    public static abstract class Visitor<E> {
        /**
         * 停止遍历标志
         * 当该属性为 true 时，遍历操作应当停止
         */
        boolean stop;

        /**
         * 访问指定元素
         * 
         * @param element 要访问的元素
         * @return 返回 true 停止遍历，返回 false 则继续遍历
         */
        public abstract boolean visit(E element);
    }

    /**
     * 前序遍历的非递归实现
     * 整个流程：
     * 1. 创建一个栈，将根节点入栈
     * 2. 当栈非空时，将栈顶节点出栈，并访问该节点
     * 3. 如果该节点有右子节点，则将右子节点入栈
     * 4. 如果该节点有左子节点，则将左子节点入栈
     * 5. 如果栈不为空，回到步骤2
     * 为什么这样就可以前序遍历二叉树了？
     * 首先，前序遍历是先访问根节点，然后访问一路访问左子结点，之后访问右子结点
     * 首先，先入栈根节点，之后，弹出一个节点node，访问该节点，第一次循环时，
     * 这个节点是根节点，符合前序遍历的要求，然后，入栈该节点的右子节点，左子节点，
     * 先入栈右子节点，再入栈左子节点，这样，栈顶的节点就是左子节点，
     * 进入下一轮循环的时候，弹出的节点node就是之前压入栈的左子节点，符合要求，
     * 然后，继续压入右子节点和左子结点，这样，栈顶的节点就是左子节点，
     * 重复这个循环，直到达到了树的最左边，也就是中序遍历的第一个节点时，压入栈，
     * 此时，弹出栈的时候，弹出的节点就是中序遍历的第一个节点，符合要求，
     * 然后，继续弹出节点node，这个节点时中序遍历的第一个节点的兄弟节点，如果
     * 这个兄弟节点还有左右子节点的话，那就一起入栈，重复循环，没有的话，那就是
     * 一路返回，持续访问根节点的左子树的右侧的节点。一路返回到了根节点的右子节点时，
     * 压入左子结点和右子节点，重复循环
     * 从上面可以看出来，这是一个重复的操作
     * 
     * @param visitor 访问器
     */
    public void preOrderNR(Visitor<E> visitor) {
        // 根节点是null，树没有节点，直接返回
        // visitor是null，没有处理逻辑，直接返回
        if (root == null || visitor == null) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        Node<E> node = null;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (visitor.visit(node.element) || visitor.stop) {
                return;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 前序遍历的第2种非递归实现
     * 这个的思路与上一个方法的preOrderNR的思路类似
     * 
     * @param visitor 访问器
     */
    public void preOrderNR2(Visitor<E> visitor) {
        // 根节点是null，树没有节点，直接返回
        // visitor是null，没有处理逻辑，直接返回
        if (root == null || visitor == null) {
            return;
        }

        // 使用栈来辅助实现前序遍历
        Stack<Node<E>> stack = new Stack<>();
        // 从根节点开始遍历
        Node<E> node = root;
        // 无限循环，直到遍历完所有节点
        while (true) {
            // 访问当前节点，并将其右子节点入栈（如果存在）
            if (node != null) {
                if (visitor.visit(node.element) || visitor.stop) {
                    return;
                }
                // 右子节点入栈，以便后续访问
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 转向左子节点
                node = node.left;
            }
            // 栈为空且当前节点为空，遍历完成
            else if (stack.isEmpty()) {
                break;
            }
            // 当前节点为空且栈不为空，从栈中弹出节点继续遍历
            else {
                node = stack.pop();
            }
        }
    }

    /**
     * 前序遍历
     * 
     * @param visitor 访问器
     */
    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        preOrder(root, visitor);
    }

    /**
     * 前序遍历的递归方法
     * 首先要搞清楚什么是递归？
     * 递归，就是方法自己调用自己，直到满足条件，不再调用自己，返回结果。
     * 但是一般而言，每次递归，都要解决比上次的问题更小的问题，直到最后，
     * 可以直接求出问题的解。
     * 从这里可以看出来，递归的关键是要逐步地分解问题成小问题，上一层的
     * 问题的解依赖下一层问题的解，并在最后一次分解中直接求出问题的解。
     * 为什么这样能够实现树的前序遍历？
     * 首先：树是一个递归结构，对于一棵树来说，可以分为根节点，左子树和右子树，
     * 对于左子树和右子树，也可以分为根节点，左子树和右子树，那这样，树就是
     * 一个嵌套的结构，也可以理解为一个递归结构。
     * 当我们在写递归方法时，要从宏观的语义出发，不要陷入递归的微观语义和细节，
     * 递归的微观语义和细节是让我们验证递归的，一开始就想到这些反而会阻碍我们
     * 写出递归代码。
     * 对于以下递归代码，首先，递归的终止条件是node == null，如果一开始
     * 的node节点就是null，那么就直接结束。
     * 如果是递归左子树到中序遍历的第一个节点，那么也递归终止，如果递归
     * 右子树到中序遍历的最后的一个节点，那么递归也终止。
     * 从递归的宏观语义出发，我们只需要关注递归的终止条件，以及递归的调用，
     * 首先，我们先访问node节点，第一次调用的时候，访问的是根节点，然后
     * 然后访问根节点的左子树，右子树，由于树是一个嵌套的递归结构，所以
     * 访问左子树和右子树的代码设计为递归调用，就可以访问完整棵树了。
     * 可以看到，这个递归代码，每次递归，都是在分解问题，从根节点的左子树
     * 和右子树，到根节点的左子树的左子树和右子树，这是一个不断分解问题成
     * 小问题的过程，知道最后一个小问题，也就是node == null，这时，可以
     * 直接解决，也就是返回
     * 因为递归是上一层问题的解是依赖下一层的问题的解的，所以，递归回溯的
     * 过程中，问题从小到大，依次得到了解决
     *
     * @param node    要遍历的起始节点
     * @param visitor 访问器
     */
    protected void preOrder(Node<E> node, Visitor<E> visitor) {
        // 如果节点为空，则返回，不再继续遍历
        if (node == null || visitor.stop) {
            return;
        }
        // 先访问根节点
        visitor.stop = visitor.visit(node.element);
        // 然后递归地前序遍历左子树
        preOrder(node.left, visitor);
        // 最后递归地前序遍历右子树
        preOrder(node.right, visitor);
    }

    /**
     * 中序遍历的非递归实现
     * 中序遍历：先访问左子树，然后访问根节点，最后访问右子树
     * 这是一个循环
     * 首先，入栈根节点node，然后一直入栈node.left，然后，弹出，访问，
     * 就是访问的中序遍历的第一个节点，之后，node = node.right，
     * 看右子树还有没有，有的话，重复循环操作，没有的话，弹出栈顶节点node，‘
     * 这个节点就是中序遍历的第二个节点，然后node = node.right，此时，
     * 会入栈这个节点，在下一轮节点访问，这样，句访问完了中序遍历的第一个，
     * 第二个和第三个节点，之后，就是循环操作了
     * 
     * @param visitor 访问器
     */
    public void inOrderNR(Visitor<E> visitor) {
        // 如果根节点为空，则直接返回
        // visitor是null，没有处理逻辑，直接返回
        if (root == null || visitor == null) {
            return;
        }

        // 使用栈来辅助进行中序遍历
        Stack<Node<E>> stack = new Stack<>();
        // 从根节点开始遍历
        Node<E> node = root;
        // 持续遍历直到栈为空且当前节点也为null
        while (true) {
            // 如果当前节点不为空，说明还有左子节点未访问，
            // 将当前节点入栈后继续访问左子节点
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                // 如果栈为空，说明所有节点都已访问完毕，结束循环
                break;
            } else {
                // 如果当前节点为空且栈不为空，说明左子节点已访问完毕，
                // 开始访问栈顶节点，也就是中序遍历的中间节点并转向右子节点
                node = stack.pop();
                if (visitor.visit(node.element) || visitor.stop) {
                    return;
                }
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历
     * 
     * @param visitor 访问器
     */
    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        inOrder(root, visitor);
    }

    /**
     * 中序遍历的递归方法
     * 同前序遍历preOrder，不再赘述
     *
     * @param node    要遍历的起始节点
     * @param visitor 访问器
     */
    protected void inOrder(Node<E> node, Visitor<E> visitor) {
        // 如果根节点为空，则直接返回
        if (node == null || visitor.stop) {
            return;
        }

        inOrder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    /**
     * 后序遍历的非递归实现
     * 使用一个栈来辅助遍历，从根节点开始入栈。
     * 利用指针 prev 记录上一个访问的节点，用于判断当前节点是否可以访问。
     * 当栈顶节点是叶子节点或其子节点已被访问过时，才访问该节点。
     * 否则，先将右子节点入栈，再将左子节点入栈，以保证左子节点先被处理。
     * 
     * @param visitor 访问器
     */
    public void postOrderNR(Visitor<E> visitor) {
        // 如果根节点为空，则直接返回
        // visitor是null，没有处理逻辑，直接返回
        if (root == null || visitor == null) {
            return;
        }

        // 使用栈来辅助进行非递归遍历
        Stack<Node<E>> stack = new Stack<>();
        // 从根节点开始遍历
        stack.push(root);
        // 定义一个指针用于记录上一个访问的节点
        Node<E> prev = null;
        // 定义一个指针用于指向栈顶的节点
        Node<E> top = null;
        // 当栈不为空时，继续遍历
        while (!stack.isEmpty()) {
            // 获取栈顶的节点
            top = stack.peek();
            // 如果栈顶节点是叶子节点，或者上一个访问的节点是当前节点的子节点
            if (top.isLeaf() || (prev != null && prev.parent == top)) {
                // 访问当前节点，并将其设为上一个访问的节点
                prev = stack.pop();
                if (visitor.visit(prev.element) || visitor.stop) {
                    return;
                }
            } else {
                // 如果栈顶节点有右子节点，将右子节点入栈
                if (top.right != null) {
                    stack.push(top.right);
                }
                // 如果栈顶节点有左子节点，将左子节点入栈
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
    }

    /**
     * 后序遍历
     * 
     * @param visitor 访问器
     */
    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        postOrder(root, visitor);
    }

    /**
     * 后序遍历的递归方法
     * 同前序遍历preOrder，不再赘述
     *
     * @param node    要遍历的起始节点
     * @param visitor 访问器
     */
    protected void postOrder(Node<E> node, Visitor<E> visitor) {
        // 根节点是null，树没有节点，直接返回
        if (node == null || visitor.stop) {
            return;
        }

        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     * 
     * @param visitor 访问器
     */
    public void levelOrder(Visitor<E> visitor) {
        // 根节点是null，树没有节点，直接返回
        // visitor是null，没有处理逻辑，直接返回
        if (root == null || visitor == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element) || visitor.stop) {
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

    /**
     * 判断树是否为完全二叉树
     * 除了最后一层之外，完全二叉树的每一层都被完全填满。
     * 最后一层的节点都集中在该层的最左边，且该层的节点数量可以从左至右排列，
     * 允许不满，但要求节点位置紧凑，即所有的空缺出现在最右侧。
     *
     * @return 如果是完全二叉树返回 true，否则返回 false
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        // 使用层序遍历来完成判断是否是完全二叉树
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        // 标记之后的所有节点都应该是叶子节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // node应该是叶子结点，但是node不是，不符合完全二叉树的定义，返回false
            if (leaf && !node.isLeaf()) {
                return false;
            }
            // 正常的层序遍历入队
            if (node.left != null) {
                queue.offer(node.left);
            } else {// 左子节点为空，这时候，就要标记之后的所有节点都是叶子节点，这样才符合完全二叉树的定义
                // 1
                // 2 3
                // 4 5 6 7
                // 8 9
                // 标记之后的节点都是叶子节点
                leaf = true;
                // 左子节点为空，右子节点也要为空，这样才符合完全二叉树的定义
                if (node.right != null) {
                    return false;
                }
            }
            // 正常的层序遍历入队
            if (node.right != null) {
                queue.offer(node.right);
            } else {// 右子节点为空，此后的所有节点都是叶子节点，这样才符合完全二叉树的定义
                leaf = true;
            }
        }

        return true;
    }

    /**
     * 获取树的高度（使用层序遍历）
     *
     * @return 返回树的高度
     */
    public int height() {
        // 根节点为空，也就是树为空，高度为0
        if (root == null) {
            return 0;
        }

        // 初始化层数
        int height = 0;
        // 每层的节点的数量，第一层是根节点所在的层，所以数量是1
        int levelSize = 1;
        // 层序遍历的队列
        Queue<Node<E>> queue = new LinkedList<>();
        // 入队根节点
        queue.offer(root);
        // 层序遍历
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 出队一个元素，就levelSize--，因为levelSize表示的是上一层所有的
            // 节点数量，所以，levelSize == 0时，需要height++
            levelSize--;

            // 层序遍历的入队操作
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 层序遍历的入队操作
            if (node.right != null) {
                queue.offer(node.right);
            }
            // levelSize == 0时，说明上一层的所有节点都出队了，
            // 此时，队列里面存储的都是当前层的节点数量，需要使用
            // 这个数量更新levelSize，同时，height++
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 获取树的高度（使用递归）
     *
     * @return 返回树的高度
     */
    public int heightR() {
        return levelHeight(root);
    }

    /**
     * 获取树的高度（使用递归）
     * 这个递归操作为什么成立？
     * 首先，只有一个根节点，高度是1
     * 如果节点为空，返回高度为0
     * 所以，递归获取左子树的高度的时候，是分解大问题为小问题，
     * 大问题：左子树的高度是多少？
     * 小问题：左子树的更小的左子树的高度是多少？
     * 通过递归不断分解小问题，会遇到递归终止条件，当节点为空时，返回高度为0
     * 所以，可以由小问题反推回大问题，也就是左子树的高度
     * 同理，获取右子树的高度，
     * 两个高度取最大值，加上根节点的高度，就是树的高度了
     *
     * @param node 要计算高度的起始节点
     * @return 返回指定节点为根的子树的高度
     */
    protected int levelHeight(Node<E> node) {
        // 如果节点为空，返回高度为0
        if (node == null) {
            return 0;
        }
        // 计算左右子树的最大高度，并加上根节点的高度1
        return 1 + Math.max(levelHeight(node.left), levelHeight(node.right));
    }

    /**
     * 清空二叉搜索树
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * 获取二叉搜索树的节点个数
     *
     * @return 返回二叉树的节点个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断二叉搜索树是否为空
     *
     * @return 如果为空返回 true，否则返回 false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 创建节点
     *
     * @param element 节点要存储的元素
     * @param parent  父节点
     * @return 返回创建的新节点
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }

    /**
     * 查找节点的前驱节点
     * 1. 首先，先看该节点有没有左子节点，有，看有没有右子节点，没有，那左子节点
     * 就是该节点的前驱节点，有右子节点，那一路往右子节点右边下探，
     * 直到遇到null，此时，最后一个非null的节点就是该节点的前驱节点
     * 2. 如果没有左子节点，获取该节点的父节点，如果该节点的父节点为null，
     * 则该节点没有前驱节点，如果该节点的父节点不为null，则判断该节点是不是
     * 父节点的左子节点，是，那就一路往上找，直到找到一个节点node，
     * node是父节点的右子节点，此时，node.parent就是该节点的前驱节点
     *
     * @param node 要查找前驱的节点
     * @return 返回指定节点的前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        // 1
        // 2 3
        // 4 5 6 7
        // 1的前驱是5
        // 获取左子节点
        Node<E> prev = node.left;
        // 左子节点不为空
        if (prev != null) {
            // 一直往右子节点的右边下探，直到遇到null，此时返回最后
            // 一个不为null的节点
            while (prev.right != null) {
                prev = prev.right;
            }
            return prev;
        }
        // 6的前驱是1
        // 4的前驱是null
        // 父节点不为null，且该节点是父节点的左子节点，则已知往上找，直到
        // 遇到一个节点node，node.parent == null或者node是父节点的右子节点，
        // 此时node.parent就是前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 查找节点的后继节点
     * 1. 首先，先看该节点有没有右子节点，有，看有没有左子节点，没有，那右子节点
     * 就是该节点的后继节点，有左子节点，那一路往左子节点左边下探，
     * 直到遇到null，此时，最后一个非null的节点就是该节点的后继节点
     * 2. 如果没有右子节点，获取该节点的父节点，如果该节点的父节点为null，
     * 则该节点没有后继节点，如果该节点的父节点不为null，则判断该节点是不是
     * 父节点的右子节点，是，那就一路往上找，直到找到一个节点node，
     * node是父节点的左子节点，此时，node.parent就是该节点的后继节点
     *
     * @param node 要查找后继的节点
     * @return 返回指定节点的后继节点
     */
    protected Node<E> successor(Node<E> node) {
        // 1
        // 2 3
        // 4 5 6 7
        // 1的后继是6
        Node<E> next = node.right;
        if (next != null) {
            while (next.left != null) {
                next = next.left;
            }
            return next;
        }
        // 5的后继是1
        // 7的后继是null
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 返回动态数组的字符串表示
     *
     * @return 表示动态数组内容的字符串
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int treeHeight = height();
        stringBuilder.append("size: ").append(size).append(", ")
                .append("height: ").append(treeHeight).append("\n");
        if (root == null) {
            return stringBuilder.toString();
        }
        // 使用层序遍历获取到二叉搜索树的输出，相应的子节点如果为null，则输出null
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 1;
        int levelSize = 1;
        stringBuilder.append(height).append(": ");
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            stringBuilder.append(node.element == null ? "null" : node.element.toString()).append(" ");
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            } else {
                queue.offer(new Node<E>(null, null));
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                queue.offer(new Node<E>(null, null));
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
                if (height > treeHeight) {
                    break;
                }
                if (levelSize > 0) {
                    stringBuilder.append("\n");
                    stringBuilder.append(height).append(": ");
                }
            }
        }
        return stringBuilder.toString();
    }
}
