package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Dijkstra 算法的优化实现，获取带权图中从指定顶点出发的最短路径。
 * 支持获取目标顶点的最短路径距离以及完整路径。
 */
public class DijkstraPro {
    /**
     * 图对象，提供图的基本操作接口
     */
    private IWeightedGraph graph;

    /**
     * 存储从源点到各顶点的最短距离
     */
    private int[] distances;

    /**
     * 标记顶点是否已被访问
     */
    private boolean[] visited;

    /**
     * 源顶点编号
     */
    private int source;

    /**
     * 存储每个顶点的前驱顶点，用于构建最短路径
     */
    private int[] parents;

    /**
     * 表示优先队列中的节点，包含顶点编号和当前累计距离
     */
    private class Node implements Comparable<Node> {

        /**
         * 顶点编号
         */
        public int vertex;

        /**
         * 当前路径下的累计距离
         */
        public int distance;

        /**
         * 构造一个表示顶点及其距离的节点
         *
         * @param vertex   顶点编号
         * @param distance 当前累计距离
         */
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * 按照累计距离对节点进行比较
         *
         * @param another 另一个节点
         * @return 距离差值，用于排序
         */
        @Override
        public int compareTo(Node another) {
            return distance - another.distance;
        }
    }

    /**
     * 构造方法，初始化 Dijkstra 最短路径算法
     *
     * @param graph  带权图对象
     * @param source 源顶点编号
     */
    public DijkstraPro(IWeightedGraph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        distances = new int[graph.getVertexes()];
        parents = new int[graph.getVertexes()];
        visited = new boolean[graph.getVertexes()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }
        distances[source] = 0;
        parents[source] = source;
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(source, 0));
        while (!queue.isEmpty()) {
            int current = queue.poll().vertex;
            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    if (distances[current] != Integer.MAX_VALUE
                            && distances[current] + graph.getWeight(current,
                            v) < distances[v]) {
                        distances[v] =
                                distances[current] + graph.getWeight(current,
                                        v);
                        parents[v] = current;
                        queue.offer(new Node(v, distances[v]));
                    }
                }
            }
        }
    }

    /**
     * 判断目标顶点是否与源顶点连通
     *
     * @param target 目标顶点编号
     * @return 是否可达
     */
    public boolean isConnectedTo(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 获取源顶点到目标顶点的最短路径长度
     *
     * @param target 目标顶点编号
     * @return 最短路径长度，若不可达则返回 Integer.MAX_VALUE
     */
    public int distanceTo(int target) {
        graph.validateVertex(target);
        return distances[target];
    }

    /**
     * 获取从源顶点到目标顶点的最短路径序列
     *
     * @param target 目标顶点编号
     * @return 路径顶点列表，若不连通则返回空列表
     */
    public Iterable<Integer> path(int target) {
        graph.validateVertex(target);
        ArrayList<Integer> path = new ArrayList<>();
        if (!isConnectedTo(target)) {
            return path;
        }

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
