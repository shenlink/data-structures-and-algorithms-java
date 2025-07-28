package com.hxqzzxk.graph;

import java.util.*;

/**
 * 图的 DFS 深度优先搜索遍历的非递归实现类，适用于所有图
 */
public class GraphDFSNR {
    /**
     * 被遍历的图结构
     */
    private IGraph graph;

    /**
     * 记录每个顶点是否已被访问
     */
    private boolean[] visited;

    /**
     * 存储遍历的整体顺序
     */
    private ArrayList<Integer> order = new ArrayList<>();

    /**
     * 存储前序遍历结果
     */
    private ArrayList<Integer> prev = new ArrayList<>();

    /**
     * 存储后序遍历结果
     */
    private ArrayList<Integer> post = new ArrayList<>();

    /**
     * 构造函数，初始化图并执行深度优先搜索遍历
     *
     * @param graph 给定的图结构
     */
    public GraphDFSNR(IGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        for (int vertex = 0; vertex < graph.getVertexes(); vertex++) {
            if (!visited[vertex]) {
                dfs(vertex);
            }
        }
        visited = new boolean[graph.getVertexes()];
        for (int vertex = 0; vertex < graph.getVertexes(); vertex++) {
            if (!visited[vertex]) {
                dfs2(vertex);
            }
        }
    }

    /**
     * 非递归方式实现前序 DFS 遍历
     *
     * @param vertex 起始顶点
     */
    public void dfs(int vertex) {
        visited[vertex] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            prev.add(current);
            order.add(current);
            for (int w : graph.edges(current)) {
                if (!visited[w]) {
                    visited[w] = true;
                    stack.push(w);
                }
            }
        }
    }

    /**
     * 非递归方式实现后序 DFS 遍历
     *
     * @param vertex 起始顶点
     */
    private void dfs2(int vertex) {
        visited[vertex] = true;
        Stack<Integer> stack = new Stack<>();
        Stack<Boolean> isProcessed = new Stack<>(); // 标记是否是第二次入栈
        stack.push(vertex);
        isProcessed.push(false);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            boolean processed = isProcessed.pop();
            if (processed) {
                post.add(current); // 第二次访问时加入 post
                continue;
            }
            // 先压入当前节点用于后续处理
            stack.push(current);
            isProcessed.push(true);
            // 逆序处理边，保证顺序正确
            ArrayList<Integer> list = new ArrayList<>();
            graph.edges(current).forEach(list::add);
            Collections.reverse(list); // 逆序压栈以保证小编号先访问
            for (int w : list) {
                if (!visited[w]) {
                    visited[w] = true;
                    stack.push(w);
                    isProcessed.push(false);
                }
            }
        }
    }

    /**
     * 获取整体遍历顺序
     *
     * @return 整体遍历顺序的迭代器
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * 获取前序遍历顺序
     *
     * @return 前序遍历结果的迭代器
     */
    public Iterable<Integer> prev() {
        return prev;
    }

    /**
     * 获取后序遍历顺序
     *
     * @return 后序遍历结果的迭代器
     */
    public Iterable<Integer> post() {
        return post;
    }
}
