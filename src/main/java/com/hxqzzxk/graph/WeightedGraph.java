package com.hxqzzxk.graph;

import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class WeightedGraph implements IWeightedGraph {
    /**
     * 顶点数量
     */
    private int vertexes;

    /**
     * 边数量
     */
    private int edges;

    /**
     * 邻接表数组，每个顶点对应一个 TreeMap 存储相邻顶点及其边的权重
     */
    private Map<Integer, Integer>[] maps;

    /**
     * 构造函数，使用顶点数、边数和边列表创建图
     *
     * @param vertexes 顶点数
     * @param edges    边数
     * @param pairs    边列表
     */
    public WeightedGraph(int vertexes, int edges, int[][] pairs) {
        if (vertexes <= 0 || edges <= 0) {
            throw new IllegalArgumentException("顶点数和边数必须大于0");
        }
        if (pairs.length != edges) {
            throw new IllegalArgumentException("边数和边数组长度不匹配");
        }

        this.vertexes = vertexes;
        this.edges = edges;
        initMaps(pairs);
    }

    /**
     * 构造函数，使用边列表自动推导顶点数
     *
     * @param pairs 边列表
     */
    public WeightedGraph(int[][] pairs) {
        int vertexes = 0;
        int edges = pairs.length;
        for (int[] pair : pairs) {
            vertexes = Math.max(vertexes, Math.max(pair[0], pair[1]));
        }
        this.vertexes = vertexes + 1;
        this.edges = edges;
        initMaps(pairs);
    }

    /**
     * 初始化邻接表
     *
     * @param pairs 边列表
     */
    private void initMaps(int[][] pairs) {
        this.maps = new TreeMap[vertexes];
        for (int i = 0; i < vertexes; i++) {
            maps[i] = new TreeMap<>();
        }
        for (int[] pair : pairs) {
            int vertex1 = pair[0];
            int vertex2 = pair[1];
            int weight = pair[2];
            validateVertex(vertex1);
            validateVertex(vertex2);
            if (vertex1 == vertex2) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (maps[vertex1].containsKey(vertex2)) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            maps[vertex1].put(vertex2, weight);
            maps[vertex2].put(vertex1, weight);
        }
    }

    /**
     * 获取指定边的权重
     *
     * @param from 起始顶点
     * @param to   结束顶点
     * @return 边的权重
     */
    @Override
    public int getWeight(int from, int to) {
        if (!hasEdge(from, to)) {
            throw new IllegalArgumentException(String.format("No edge %d-%d",
                    from, to));
        }
        return maps[from].get(to);
    }

    /**
     * 获取图中的顶点数
     *
     * @return 顶点数
     */
    @Override
    public int getVertexes() {
        return vertexes;
    }

    /**
     * 获取图中的边数
     *
     * @return 边数
     */
    @Override
    public int getEdges() {
        return edges;
    }

    /**
     * 判断两个顶点之间是否存在边
     *
     * @param vertex1 第一个顶点
     * @param vertex2 第二个顶点
     * @return 如果存在边返回true，否则返回false
     */
    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        return maps[vertex1].containsKey(vertex2);
    }

    /**
     * 获取与指定顶点相邻的所有顶点
     *
     * @param vertex 指定的顶点
     * @return 相邻顶点的迭代器
     */
    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        return maps[vertex].keySet();
    }

    /**
     * 验证顶点是否合法
     *
     * @param vertex 要验证的顶点
     */
    @Override
    public void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexes) {
            throw new IllegalArgumentException("顶点" + vertex + "不合法");
        }
    }

    /**
     * 返回图的字符串表示
     *
     * @return 图的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点数: %d, 边数: %d\n", vertexes,
                edges));
        for (int i = 0; i < vertexes; i++) {
            stringBuilder.append(i).append(": ");
            for (Map.Entry<Integer, Integer> entry : maps[i].entrySet()) {
                stringBuilder.append(String.format("(%d: %d) ",
                        entry.getKey(), entry.getValue()));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
