package com.hxqzzxk.graph;

/**
 * 实现 Bellman-Ford 算法，获取带权图的最短路径
 * 可以检测带权图中是否存在从源点可达的负权环
 */
public class BellmanFord {
    /**
     * 带权图
     */
    private IWeightedGraph graph;

    /**
     * 存储从源点到各顶点的最短路径距离
     */
    private int[] distances;

    /**
     * 指示图中是否存在负权环
     */
    private boolean hasNegativeCycle;

    /**
     * 构造函数，执行Bellman-Ford算法
     *
     * @param graph  带权图
     * @param source 源点
     */
    public BellmanFord(IWeightedGraph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;
        distances = new int[graph.getVertexes()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        for (int pass = 1; pass <= graph.getVertexes() - 1; pass++) {
            for (int v = 0; v < graph.getVertexes(); v++) {
                for (int w : graph.edges(v)) {
                    if (distances[v] != Integer.MAX_VALUE
                            && distances[v] + graph.getWeight(v, w) < distances[w]) {
                        distances[w] = distances[v] + graph.getWeight(v, w);
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
     * 判断图中是否存在负权环
     *
     * @return 如果存在负权环返回true，否则返回false
     */
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    /**
     * 判断目标顶点是否可到达
     *
     * @param target 目标顶点
     * @return 如果可到达返回true，否则返回false
     */
    public boolean isConnectedTo(int target) {
        graph.validateVertex(target);
        return distances[target] != Integer.MAX_VALUE;
    }

    /**
     * 获取从源点到目标顶点的最短路径长度
     *
     * @param target 目标顶点
     * @return 最短路径长度
     * @throws RuntimeException 如果图中存在负权环
     */
    public int distanceTo(int target) {
        graph.validateVertex(target);
        if (hasNegativeCycle) {
            throw new RuntimeException("存在负权环");
        }
        return distances[target];
    }
}
