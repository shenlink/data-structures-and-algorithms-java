package com.hxqzzxk.graph;

import java.util.ArrayList;

/**
 * 图的 DFS 深度优先搜索遍历的递归实现类，适用于所有图
 */
public class GraphDFSR {
    /**
     * 图对象，用于访问图的基本操作
     */
    private IGraph graph;

    /**
     * 记录顶点是否已访问的数组
     */
    private boolean[] visited;

    /**
     * 保存遍历顺序的列表
     */
    private ArrayList<Integer> order = new ArrayList<>();

    /**
     * 保存前序遍历结果的列表
     */
    private ArrayList<Integer> prev = new ArrayList<>();

    /**
     * 保存后序遍历结果的列表
     */
    private ArrayList<Integer> post = new ArrayList<>();

    /**
     * 构造函数，初始化深度优先搜索
     *
     * @param graph 需要进行遍历的图对象
     */
    public GraphDFSR(IGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        for (int vertex = 0; vertex < graph.getVertexes(); vertex++) {
            if (!visited[vertex]) {
                dfs(vertex);
            }
        }
    }

    /**
     * 深度优先搜索递归方法
     *
     * @param vertex 当前访问的顶点
     */
    private void dfs(int vertex) {
        visited[vertex] = true;
        order.add(vertex);
        prev.add(vertex);
        for (int w : graph.edges(vertex)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(vertex);
    }

    /**
     * 获取完整的遍历顺序
     *
     * @return 可迭代的整数集合表示遍历顺序
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * 获取前序遍历的结果
     *
     * @return 可迭代的整数集合表示前序遍历结果
     */
    public Iterable<Integer> prev() {
        return prev;
    }

    /**
     * 获取后序遍历的结果
     *
     * @return 可迭代的整数集合表示后序遍历结果
     */
    public Iterable<Integer> post() {
        return post;
    }
}
