package com.hxqzzxk.graph;

/**
 * 表示无向带权边的类，实现了 Comparable 接口以便于比较
 */
public class UnDirectedWeightedEdge implements Comparable<UnDirectedWeightedEdge> {
    /**
     * 边的一个端点
     */
    private int from;

    /**
     * 边的另一个端点
     */
    private int to;

    /**
     * 边的权重值
     */
    private int weight;

    /**
     * 构造函数，创建一个新的无向带权边
     *
     * @param from   边的第一个端点
     * @param to     边的第二个端点
     * @param weight 边的权重值
     */
    public UnDirectedWeightedEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * 获取边的第一个端点
     *
     * @return 边的第一个端点
     */
    public int getFrom() {
        return from;
    }

    /**
     * 获取边的第二个端点
     *
     * @return 边的第二个端点
     */
    public int getTo() {
        return to;
    }

    /**
     * 获取边的权重值
     *
     * @return 边的权重值
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 比较当前边与另一条边的权重值
     *
     * @param another 另一条需要比较的边
     * @return 当前边的权重减去另一条边的权重
     */
    @Override
    public int compareTo(UnDirectedWeightedEdge another) {
        return this.weight - another.weight;
    }

    /**
     * 返回边的字符串表示形式
     *
     * @return 格式为"(from-to: weight)"的字符串
     */
    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", from, to, weight);
    }
}
