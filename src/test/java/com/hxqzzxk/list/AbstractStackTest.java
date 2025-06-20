package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * 抽象栈数据结构测试类，实现了 StackTest 接口定义的基础测试方法
 */
public abstract class AbstractStackTest implements StackTest {

    /**
     * 栈实例
     */
    protected Stack<Integer> stack;

    /**
     * 创建具体的栈实例
     */
    public abstract void createStack();

    /**
     * 测试入栈操作的方法
     * 验证栈在添加元素后是否正确维护其内部状态
     */
    @Test
    @Override
    public void testPush() {
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(2, stack.size());
        Assert.assertEquals(new Integer(2), stack.top());
    }

    /**
     * 测试出栈操作的方法
     * 验证栈在移除元素后是否正确更新栈顶和大小
     */
    @Test
    @Override
    public void testPop() {
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(new Integer(2), stack.pop());
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(new Integer(1), stack.top());
    }

    /**
     * 测试获取栈顶元素的方法
     * 验证 top 方法返回当前栈顶的元素而不移除它
     */
    @Test
    @Override
    public void testTop() {
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(new Integer(2), stack.top());
        Assert.assertEquals(2, stack.size()); // 确保 top 不改变栈大小
    }

    /**
     * 测试清空栈的方法
     * 验证 clear 方法调用后栈是否被正确重置为空
     */
    @Test
    @Override
    public void testClear() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        stack.clear();
        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());
    }

    /**
     * 测试获取栈中元素数量的方法
     * 验证 size 方法能否准确反映栈当前的大小
     */
    @Test
    @Override
    public void testSize() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            Assert.assertEquals(i + 1, stack.size());
        }
        stack.clear();
        Assert.assertEquals(0, stack.size());
    }

    /**
     * 测试判断栈是否为空的方法
     * 验证 isEmpty 方法能否正确识别空栈与非空栈
     */
    @Test
    @Override
    public void testIsEmpty() {
        Assert.assertTrue(stack.isEmpty());
        stack.push(0);
        Assert.assertFalse(stack.isEmpty());
        stack.pop();
        Assert.assertTrue(stack.isEmpty());
    }
}