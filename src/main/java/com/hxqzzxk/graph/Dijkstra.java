package com.hxqzzxk.graph;

/**
 * 实现 Dijkstra 算法，获取带权图中的单源最短路径
 */
public class Dijkstra {
    /**
     * 图对象，表示当前处理的带权图。
     */
    private IWeightedGraph graph;

    /**
     * 存储从源点到各顶点的最短路径距离。
     */
    private int[] distances;

    /**
     * 标记顶点是否已处理过。
     */
    private boolean[] visited;

    /**
     * 构造函数，使用 Dijkstra 算法初始化最短路径信息。
     *
     * @param graph  带权图对象
     * @param source 源点顶点编号
     */
    public Dijkstra(IWeightedGraph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;

        distances = new int[graph.getVertexes()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        visited = new boolean[graph.getVertexes()];
        while (true) {
            int current = -1;
            int currentDistance = Integer.MAX_VALUE;
            for (int i = 0; i < distances.length; i++) {
                if (!visited[i] && distances[i] < currentDistance) {
                    current = i;
                    currentDistance = distances[i];
                }
            }
            if (current == -1) {
                break;
            }
            visited[current] = true;
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    if (distances[current] + graph.getWeight(current, v) < distances[v]) {
                        distances[v] =
                                distances[current] + graph.getWeight(current,
                                        v);
                    }
                }
            }
        }
    }

    /**
     * 判断目标顶点是否与源点连通。
     *
     * @param target 目标顶点编号
     * @return 如果目标顶点可达则返回 true，否则返回 false
     */
    public boolean isConnectedTo(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 获取从源点到目标顶点的最短路径距离。
     *
     * @param target 目标顶点编号
     * @return 最短路径距离
     */
    public int distanceTo(int target) {
        graph.validateVertex(target);
        return distances[target];
    }
}
