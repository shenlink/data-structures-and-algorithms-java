package com.hxqzzxk.strings;

/**
 * 字符串匹配接口，定义了字符串查找的基本操作
 */
public interface Match {
    /**
     * 查找模式字符串在文本字符串中的首次出现位置
     *
     * @param text    被搜索的文本字符串
     * @param pattern 需要查找的模式字符串
     * @return 如果找到模式字符串，则返回其在文本字符串中首次出现的索引；
     *         如果未找到，则返回-1
     */
    int indexOf(String text, String pattern);
}
