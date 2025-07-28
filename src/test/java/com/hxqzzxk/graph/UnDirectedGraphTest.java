package com.hxqzzxk.graph;

/**
 * 无向图测试类
 */
public class UnDirectedGraphTest extends IUnDirectedGraphTest {
    /**
     * 设置无向图实例
     */
    @Override
    public void setUp() {
        graph = new UnDirectedGraph(pairs);
    }
}
