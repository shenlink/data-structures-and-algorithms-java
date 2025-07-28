package com.hxqzzxk.graph;

/**
 * 实现 Floyd 算法，获取带权图所有顶点对之间的最短路径。
 */
public class Floyed {
    /**
     * 图对象，表示当前处理的带权图。
     */
    private IWeightedGraph graph;

    /**
     * 存储每对顶点之间的最短路径距离。
     */
    private int[][] distances;

    /**
     * 表示图中是否存在负权环。
     */
    private boolean hasNegativeCycle;

    /**
     * 构造函数，使用 Floyd 算法初始化最短路径信息。
     *
     * @param graph 带权图对象
     */
    public Floyed(IWeightedGraph graph) {
        this.graph = graph;
        distances = new int[graph.getVertexes()][graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            for (int j = 0; j < graph.getVertexes(); j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int v = 0; v < graph.getVertexes(); v++) {
            distances[v][v] = 0;
            for (int w : graph.edges(v)) {
                distances[v][w] = graph.getWeight(v, w);
            }
        }
        for (int t = 0; t < graph.getVertexes(); t++) {
            for (int v = 0; v < graph.getVertexes(); v++) {
                for (int w = 0; w < graph.getVertexes(); w++) {
                    if (distances[v][t] != Integer.MAX_VALUE
                            && distances[t][w] != Integer.MAX_VALUE
                            && distances[v][t] + distances[t][w] < distances[v][w]) {
                        distances[v][w] = distances[v][t] + distances[t][w];
                    }
                }
            }
        }
        for (int v = 0; v < graph.getVertexes(); v++) {
            if (distances[v][v] < 0) {
                hasNegativeCycle = true;
            }
        }
    }

    /**
     * 判断图中是否存在负权环。
     *
     * @return 如果存在负权环则返回 true，否则返回 false
     */
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    /**
     * 判断指定的两个顶点之间是否连通。
     *
     * @param source 起始顶点编号
     * @param target 目标顶点编号
     * @return 如果 source 可达 target 返回 true，否则返回 false
     */
    public boolean isConnected(int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        return distances[source][target] != Integer.MAX_VALUE;
    }

    /**
     * 获取从起始顶点到目标顶点的最短路径距离。
     *
     * @param source 起始顶点编号
     * @param target 目标顶点编号
     * @return 最短路径距离
     */
    public int distanceTo(int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        return distances[source][target];
    }
}
