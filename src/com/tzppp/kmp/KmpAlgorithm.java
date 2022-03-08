package com.tzppp.kmp;

import java.util.Arrays;

/**
 * 字符串kmp匹配
 * https://blog.csdn.net/v_july_v/article/details/7041827
 * 扩展1：BM算法
 * 扩展2：Sunday算法
 *
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(violentMatch("hello", "ll"));
        int[] next = kmpNext(str2.toCharArray());
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1.toCharArray(),str2.toCharArray()));
    }

    /**
     * kmp算法匹配
     * 笔记：
     * 1、字符匹配或者模版next下标=-1则s和p均左移动1位
     * 2、若字符失配则模版p右移j - next[j] 位
     *
     * @param s 匹配字符串
     * @param p 模版字符串
     * @return 匹配到则返回下标，没有匹配到则返回-1
     */
    public static int kmpSearch(char[] s, char[] p) {
        int sLen = s.length, pLen = p.length;
        int i = 0, j = 0;
        int[] next = kmpNext(p);
        while (i < sLen && j < pLen) {
            if (j == -1 || s[i] == p[j]) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == pLen) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * 这里获取的是next数组，意味着只计算下标k前面的最大匹配前缀、后缀长度，next[0] = -1
     * 笔记：
     * 1、next[j]记录的是前0->j-1字符的最大匹配值
     * 2、所以chars[k] == chars[j]满足之后需要把最大长度设置在next[j+1]上，因为前缀指针k也后移了一位，所以最大匹配值为k-1+1= k
     *
     * @param p 模版字符串
     * @return 返回next数组，就是把最大匹配表整体右移一位，然后下标0设置为-1
     */
    public static int[] kmpNext(char[] p) {
        int length = p.length;
        int[] next = new int[length];
        next[0] = -1;
        int k = -1; //前缀指针
        int j = 0; //后缀指针
        while (j < length - 1) {
            if (k == -1 || p[k] == p[j]) {
                ++k;
                ++j;
                /**
                 * 1、优化前代码,未优化之前的代码先注释了
                 */
//                next[j] = k; // 最大匹配就是前缀长度-1 = K
                /**
                 * 2、优化后代码
                 * 即p[j] != p[next[j]]
                 * 原因：根据匹配算法我们得知，当s[i]和p[j]字符失配后我们将j指针修改为next[j]继续匹配，即匹配s[i] ?= p[next[j]]
                 * 所以：如果p[j] == p[next[j]] 则s[i] 必然不等于p[next[j]],移位没有意义
                 * 所以优化 当p[j] == p[next[j]] = p[k] 时 让next[j] = next[next[j]] = next[k]
                 */
                if (p[j] == p[k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 获取字符串的最大匹配表
     *
     * @param dest 模版字符串
     * @return 返回最大匹配表
     */
    public static int[] kmpMax(String dest) {
        // 创建一个next数组存储部分匹配表
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串长度是1则部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 1、当 dest.charAt(i) ！= dest.charAt(j) 需要从next[j-1]获取新的j
            // 2、直到发现有dest.charAt(i) == dest.charAt(j)成立时才退出
            // 算法核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 3、满足dest.charAt(i) == dest.charAt(j)时，部分匹配值就是要+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    // 暴力匹配
    public static int violentMatch(String str1, String str2) {
        int l1 = str1.length(), l2 = str2.length();
        int i = 0, j = 0;
        while (i < l1 && j < l2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
                continue;
            }
            i = i - j + 1;
            j = 0;
        }
        if (j == l2) {
            return i - j;
        } else {
            return -1;
        }
    }
}