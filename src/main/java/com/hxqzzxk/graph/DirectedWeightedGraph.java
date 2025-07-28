package com.hxqzzxk.graph;

import java.util.Map;
import java.util.TreeMap;

/**
 * 有向带权图类，使用邻接表存储结构
 * 支持获取顶点数、边数、度数统计、边权重等基本操作
 */
@SuppressWarnings("unchecked")
public class DirectedWeightedGraph implements IWeightedGraph, IDirectedGraph {
    /**
     * 顶点数量
     */
    private int vertexes;

    /**
     * 边的数量
     */
    private int edges;

    /**
     * 邻接表数组，每个顶点对应一个TreeMap存储相邻顶点及其权重
     * key: 目标顶点，value: 边的权重
     */
    private Map<Integer, Integer>[] maps;

    /**
     * 入度数组，inDegree[v]表示顶点v的入度
     */
    private int[] inDegree;

    /**
     * 出度数组，outDegree[v]表示顶点v的出度
     */
    private int[] outDegree;

    /**
     * 构造指定顶点数和边数的有向带权图
     *
     * @param vertexes 图的顶点数
     * @param edges    图的边数
     * @param pairs    边数组，每个元素包含三个值：起点、终点、权重
     */
    public DirectedWeightedGraph(int vertexes, int edges, int[][] pairs) {
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
     * 根据边数组构造有向带权图
     *
     * @param pairs 边数组，每个元素包含三个值：起点、终点、权重
     */
    public DirectedWeightedGraph(int[][] pairs) {
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
     * 初始化邻接表并构建图结构
     *
     * @param pairs 边数组，每个元素包含三个值：起点、终点、权重
     */
    private void initMaps(int[][] pairs) {
        this.maps = new TreeMap[vertexes];
        inDegree = new int[vertexes];
        outDegree = new int[vertexes];
        for (int i = 0; i < vertexes; i++) {
            maps[i] = new TreeMap<>();
        }
        for (int[] pair : pairs) {
            int from = pair[0];
            int to = pair[1];
            int weight = pair[2];
            validateVertex(from);
            validateVertex(to);
            if (from == to) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (maps[from].containsKey(to)) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            maps[from].put(to, weight);
            inDegree[to]++;
            outDegree[from]++;
        }
    }

    /**
     * 获取两个顶点之间的边的权重
     *
     * @param vertex1 起点
     * @param vertex2 终点
     * @return 边的权重
     */
    @Override
    public int getWeight(int vertex1, int vertex2) {
        return maps[vertex1].get(vertex2);
    }

    /**
     * 获取图的顶点数
     *
     * @return 顶点数量
     */
    @Override
    public int getVertexes() {
        return vertexes;
    }

    /**
     * 获取图的边数
     *
     * @return 边的数量
     */
    @Override
    public int getEdges() {
        return edges;
    }

    /**
     * 检查两个顶点之间是否存在边
     *
     * @param vertex1 起点
     * @param vertex2 终点
     * @return 如果存在边则返回true，否则返回false
     */
    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        return maps[vertex1].containsKey(vertex2);
    }

    /**
     * 获取指定顶点的所有出边
     *
     * @param vertex 指定顶点
     * @return 可迭代的出边集合
     */
    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        return maps[vertex].keySet();
    }

    /**
     * 获取指定顶点的入度
     *
     * @param vertex 指定顶点
     * @return 顶点的入度
     */
    @Override
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        return inDegree[vertex];
    }

    /**
     * 获取指定顶点的出度
     *
     * @param vertex 指定顶点
     * @return 顶点的出度
     */
    @Override
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        return outDegree[vertex];
    }

    /**
     * 验证顶点索引是否合法
     *
     * @param vertex 待验证的顶点
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
        stringBuilder.append(String.format("顶点数：%d，边数：%d\n", vertexes, edges));
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
