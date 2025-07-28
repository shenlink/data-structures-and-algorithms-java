package com.hxqzzxk.graph;

/**
 * 有向图环检测类 - 用于检测有向图中是否存在环的类。
 */
public class DirectedCycleDetection {
    /**
     * 被检测的有向图对象。
     */
    private IDirectedGraph graph;

    /**
     * 记录各个顶点是否被访问过的数组。
     */
    private boolean[] visisted;

    /**
     * 记录当前 DFS 路径上已访问的顶点，用于检测环。
     */
    private boolean[] onPath;

    /**
     * 标记图中是否存在环的结果。
     */
    private boolean hasCycle;

    /**
     * 构造函数，初始化图并进行深度优先遍历以检测环。
     *
     * @param graph 给定的有向图
     */
    public DirectedCycleDetection(IDirectedGraph graph) {
        this.graph = graph;
        visisted = new boolean[graph.getVertexes()];
        onPath = new boolean[graph.getVertexes()];
        for (int v = 0; v < graph.getVertexes(); v++) {
            if (!visisted[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 深度优先搜索，用于检测当前路径上是否存在环。
     *
     * @param vertex 当前访问的顶点
     * @return 如果发现环则返回 true，否则返回 false
     */
    private boolean dfs(int vertex) {
        visisted[vertex] = true;
        onPath[vertex] = true;
        for (int v : graph.edges(vertex)) {
            if (!visisted[v]) {
                if (dfs(v)) {
                    return true;
                }
            } else if (onPath[v]) {
                return true;
            }
        }
        onPath[vertex] = false;
        return false;
    }

    /**
     * 判断图中是否存在环。
     *
     * @return 如果存在环则返回 true，否则返回 false
     */
    public boolean hasCycle() {
        return hasCycle;
    }
}
