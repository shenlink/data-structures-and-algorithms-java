package com.hxqzzxk.graph;

/**
 * 图的基本接口，定义了图的基本操作方法
 */
public interface IGraph {
    /**
     * 获取图中的顶点数量
     *
     * @return 返回图中的顶点数量
     */
    int getVertexes();

    /**
     * 获取图中的边数量
     *
     * @return 返回图中的边数量
     */
    int getEdges();

    /**
     * 判断两个顶点之间是否存在边
     *
     * @param vertex1 第一个顶点
     * @param vertex2 第二个顶点
     * @return 如果存在边则返回true，否则返回false
     */
    boolean hasEdge(int vertex1, int vertex2);

    /**
     * 获取与指定顶点相连的所有边
     *
     * @param vertex 指定顶点
     * @return 返回与指定顶点相连的所有边的可迭代对象
     */
    Iterable<Integer> edges(int vertex);

    /**
     * 验证给定的顶点是否在图的有效范围内
     *
     * @param vertex 要验证的顶点
     * @throws IllegalArgumentException 如果顶点不在有效范围内，则抛出异常
     */
    void validateVertex(int vertex);
}
