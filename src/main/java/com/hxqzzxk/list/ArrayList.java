package com.hxqzzxk.list;

/**
 * 动态数组实现，基于数组的数据结构，支持自动扩容和缩容
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
    /**
     * 实际存储元素的数组
     */
    private E[] elements;

    /**
     * 数组的默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造函数
     *
     * @param capacity 初始容量
     */
    public ArrayList(int capacity) {
        // 当容量小于等于默认容量，使用默认容量
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    /**
     * 默认构造函数，使用默认的容量
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清空数组
     */
    @Override
    public void clear() {
        // 不使用Arrays.fill()方法，专注于数据结构和算法的思路
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 获取指定索引的元素
     *
     * @param index 要获取的元素索引，必须在 [0, size()) 范围内
     * @return 位于指定索引处的元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * 修改指定索引的元素，返回索引之前的元素
     *
     * @param index   要替换的索引，必须在 [0, size()) 范围内
     * @param element 新元素
     * @return 被替换的旧元素
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 添加元素到指定索引
     *
     * @param index   插入索引，必须在 [0, size()] 范围内
     * @param element 要插入的元素
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        // 扩容
        expansion(index + 1);
        // 添加时有4种情况
        // 1. 添加第一个元素，index = 0，直接添加
        // 2. 添加的索引是0，那就要把0到size - 1的元素往后挪动
        // 3. 添加的索引是size，index = size，直接添加
        // 4. 添加的索引是1到size - 1，需要从index开始把元素往后挪动，然后在index出插入新的元素
        // 这4中情况可以进一步归纳成2种情况：
        // 1. index == size，直接添加
        // 2. index < size，从index开始把元素往后挪动，然后在index出插入新的元素
        // 由于 index == size时，for (int i = size; i > index; i--)中，i >
        // index的条件没有成立，
        // 所以这两种情况的代码可以统一
        // 从index + 1开始往后挪动元素，index 的元素覆盖index + 1的元素
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除指定索引的元素，返回索引之前的元素
     *
     * @param index 要删除的索引，必须在 [0, size()) 范围内
     * @return 被删除的元素
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        // 保存索引之前的元素，用于返回
        E oldElement = elements[index];
        // 删除时有4种情况
        // 1. 只有一个元素，直接删除
        // 2. 删除的索引是0，那就要把0到size - 1的元素往前挪动
        // 3. 删除的索引是size，index = size，直接删除
        // 4. 删除的索引是1到size - 1，需要从index开始把元素往前挪动
        // 这4中情况可以进一步归纳成2种情况：
        // 1. index == size，直接删除
        // 2. index < size，从index开始把元素往前挪动
        // 由于 index == size时，for (int i = index; i < size - 1; i++)中，i <
        // size的条件没有成立，
        // 所以这两种情况的代码可以统一
        // 从index + 1开始往前挪动元素，index 的元素覆盖index - 1的元素
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        shrinking();
        return oldElement;
    }

    /**
     * 获取指定元素的索引，如果是null，返回第一个是null的元素的索引
     *
     * @param element 要查找的元素
     * @return 元素首次出现的索引索引，如果未找到则返回 -1
     */
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 确认是数组容量是否满足
     *
     * @param capacity 需要确认的容量
     * @return 如果当前容量大于等于需要的容量返回 true
     */
    private boolean checkCapacity(int capacity) {
        return elements.length >= capacity;
    }

    /**
     * 扩容，数组容量不够，扩容加大容量到原始容量的1.5倍
     *
     * @param capacity 需要的最小容量
     */
    private void expansion(int capacity) {
        if (checkCapacity(capacity)) {
            return;
        }
        int oldCapacity = elements.length;
        // 扩容后的容量是旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 缩容，元素数量小于等于数组容量的一半时，缩容到一半
     */
    private void shrinking() {
        int oldCapacity = elements.length;
        // 缩容后的容量是旧容量的一半
        int newCapacity = oldCapacity >> 1;
        // 如果当前的元素数量大于新容量或者新容量小于默认容量，不需要缩容
        if (size > newCapacity || newCapacity < DEFAULT_CAPACITY) {
            return;
        }

        E[] newElements = (E[]) new Object[newCapacity];
        // 注意：不使用Arrays.fill()方法，专注于数据结构和算法的思路
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * 返回动态数组的字符串表示
     *
     * @return 表示动态数组内容的字符串
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size: ").append(size).append(", elements: [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i].toString());
        }
        return stringBuilder.append("]").toString();
    }
}
