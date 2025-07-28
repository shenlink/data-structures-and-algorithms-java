package com.hxqzzxk.graph;

/**
 * 无向图测试基类
 */
public class IUnDirectedGraphTest extends AbstractGraphTest {
    /**
     * 创建无向图实例
     */
    @Override
    public void setUp() {
        graph = new UnDirectedGraph(pairs);
    }
}
