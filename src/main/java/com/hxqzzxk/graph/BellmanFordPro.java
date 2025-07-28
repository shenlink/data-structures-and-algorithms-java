package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Bellman-Ford 算法的改进版，获取带权图中的最短路径以及检测负权环。
 */
public class BellmanFordPro {
    /**
     * 图接口，表示当前的带权有向图。
     */
    private IWeightedGraph graph;

    /**
     * 源点（起点）的索引。
     */
    private int source;

    /**
     * 存储从源点到各个顶点的最短路径距离。
     */
    private int[] distances;

    /**
     * 标记是否存在从源点可达的负权环。
     */
    private boolean hasNegativeCycle;

    /**
     * 存储最短路径中每个顶点的前驱节点。
     */
    private int[] parents;

    /**
     * 构造函数，执行Bellman-Ford算法以计算最短路径并检测负权环。
     *
     * @param graph  带权有向图
     * @param source 起始顶点
     */
    public BellmanFordPro(IWeightedGraph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        distances = new int[graph.getVertexes()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        parents = new int[graph.getVertexes()];
        for (int pass = 1; pass <= graph.getVertexes() - 1; pass++) {
            for (int v = 0; v < graph.getVertexes(); v++) {
                for (int w : graph.edges(v)) {
                    if (distances[v] != Integer.MAX_VALUE
                            && distances[v] + graph.getWeight(v, w) < distances[w]) {
                        distances[w] = distances[v] + graph.getWeight(v, w);
                        parents[w] = v;
                    }
                }
            }
        }
        for (int v = 0; v < graph.getVertexes(); v++) {
            for (int w : graph.edges(v)) {
                if (distances[v] != Integer.MAX_VALUE && distances[v] + graph.getWeight(v, w) < distances[w]) {
                    hasNegativeCycle = true;
                }
            }
        }
    }

    /**
     * 判断图中是否存在从源点可达的负权环。
     *
     * @return 如果存在负权环返回true，否则返回false
     */
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    /**
     * 判断目标顶点是否与源点连通。
     *
     * @param target 目标顶点
     * @return 如果目标顶点与源点连通返回true，否则返回false
     */
    public boolean isConnectedTo(int target) {
        graph.validateVertex(target);
        return distances[target] != Integer.MAX_VALUE;
    }

    /**
     * 获取从源点到目标顶点的最短路径长度。
     *
     * @param target 目标顶点
     * @return 最短路径的长度
     * @throws RuntimeException 如果图中存在负权环则抛出异常
     */
    public int distanceTo(int target) {
        graph.validateVertex(target);
        if (hasNegativeCycle) {
            throw new RuntimeException("存在负权环");
        }
        return distances[target];
    }

    /**
     * 获取从源点到目标顶点的最短路径。
     *
     * @param target 目标顶点
     * @return 包含路径顶点的可迭代对象
     * @throws RuntimeException 如果图中存在负权环则抛出异常
     */
    public Iterable<Integer> path(int target) {
        graph.validateVertex(target);
        if (hasNegativeCycle) {
            throw new RuntimeException("存在负权环");
        }
        ArrayList<Integer> path = new ArrayList<>();
        int current = target;
        while (current != source) {
            path.add(current);
            current = parents[current];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
