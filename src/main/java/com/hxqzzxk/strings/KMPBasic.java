package com.hxqzzxk.strings;

/**
 * KMP算法基础实现
 * 使用KMP算法进行字符串匹配，通过next数组避免重复比较提高效率
 */
public class KMPBasic implements Match {
    /**
     * 使用KMP算法查找模式字符串在文本字符串中的首次出现位置
     *
     * @param text    被搜索的文本字符串
     * @param pattern 需要查找的模式字符串
     * @return 如果找到模式字符串，则返回其在文本字符串中首次出现的索引，如果未找到，则返回-1
     */
    @Override
    public int indexOf(String text, String pattern) {
        // 检查输入字符串是否为null，如果是，则返回-1
        if (text == null || pattern == null) {
            return -1;
        }
        // 获取文本和模式字符串的长度
        int tlen = text.length();
        int plen = pattern.length();
        // 如果模式字符串为空或者文本字符串长度小于模式字符串长度，则返回-1
        if (plen == 0 || plen == 0 || tlen < plen) {
            return -1;
        }
        // 初始化文本和模式字符串的索引
        int ti = 0;
        int pi = 0;
        // 计算文本字符串中可能的匹配开始位置的最大值
        int tiMax = tlen - plen;
        // 生成模式字符串的next数组，用于KMP算法
        int[] next = next(pattern);
        // 遍历文本字符串和模式字符串，使用KMP算法进行匹配
        while (pi < plen && ti - pi <= tiMax) {
            // 如果模式字符串索引为-1或者当前字符匹配成功，则移动到下一个字符
            if (pi < 0 || text.charAt(ti) == pattern.charAt(pi)) {
                ti++;
                pi++;
            } else {
                // 如果当前字符匹配失败，则根据next数组移动模式字符串的索引
                pi = next[pi];
            }
        }
        // 如果模式字符串完全匹配，则返回匹配开始的索引；否则返回-1
        return pi == plen ? ti - pi : -1;
    }

    /**
     * 计算KMP算法中的next数组
     * next数组记录了模式串中每个位置之前的子串的最长公共前后缀的长度
     *
     * @param pattern 模式串
     * @return 返回pattern对应的next数组
     */
    private int[] next(String pattern) {
        // 模式串的长度
        int len = pattern.length();
        // 初始化next数组，长度与模式串相同
        int[] next = new int[len];
        // 初始化变量i（当前考察的字符位置）和n（当前字符之前的子串的最长公共前后缀的长度）
        int i = 0;
        int n = -1;
        // 设置初始条件，next数组的第一个元素为-1
        next[i] = n;
        // 模式串最后一个字符的位置
        int iMax = len - 1;
        // 遍历模式串，计算next数组的值
        while (i < iMax) {
            // 当n为-1（初始状态）或当前字符与n位置的字符匹配时
            if (n < 0 || pattern.charAt(i) == pattern.charAt(n)) {
                // 向前移动到下一个字符
                i++;
                n++;
                // 设置当前字符的next值
                next[i] = n;
            } else {
                // 如果当前字符与n位置的字符不匹配，则回退n到next[n]位置
                n = next[n];
            }
        }
        // 返回计算完成的next数组
        return next;
    }
}
