package com.hxqzzxk.strings;

/**
 * 蛮力算法的另一种实现
 * 该实现采用固定文本位置逐个比较的方式进行字符串匹配
 */
public class BruteForceMatchAnother implements Match {
    /**
     * 使用蛮力算法查找模式字符串在文本字符串中的首次出现位置
     *
     * @param text    被搜索的文本字符串
     * @param pattern 需要查找的模式字符串
     * @return 如果找到模式字符串，则返回其在文本字符串中首次出现的索引，如果未找到，则返回-1
     */
    @Override
    public int indexOf(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        int tlen = text.length();
        int plen = pattern.length();
        if (plen == 0 || plen == 0 || tlen < plen) {
            return -1;
        }
        int tiMax = tlen - plen;
        // ti固定，pi自增
        for (int ti = 0; ti <= tiMax; ti++) {
            int pi = 0;
            for (; pi < plen; pi++) {
                if (text.charAt(ti + pi) != pattern.charAt(pi)) {
                    break;
                }
            }
            if (pi == plen) {
                return ti;
            }
        }
        return -1;
    }
}
