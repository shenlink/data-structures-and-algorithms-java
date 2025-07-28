package com.hxqzzxk.graph;

/**
 * 图通用测试接口
 * 包含针对图的基本操作测试
 */
public interface IGraphTest {
    /**
     * 测试获取顶点数量功能
     */
    public void testGetVertex();

    /**
     * 测试获取边数量功能
     */
    public void testGetEdges();

    /**
     * 测试边存在性检测功能
     */
    public void testHasEdge();

    /**
     * 测试获取邻接边功能
     */
    public void testEdges();

    /**
     * 测试顶点度数功能
     */
    public void tesValidateVertex();
}
