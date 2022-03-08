package com.tzppp.greedy;

/**
 * 1.钱币找零问题
 * 这个问题在我们的日常生活中就更加普遍了。
 * 用贪心算法的思想，很显然，每一步尽可能用面值大的纸币即可。
 * 假设纸币金额为1元、5元、10元、20元、50元、100元，123元应该尽可能兑换少的纸币。
 * 按尝试应该兑换1张100、1张20元和3张1元的。
 */
public class QianBi {
    static int[] prices = {100, 50, 20, 10, 5, 1};
    static void splitChange(int money) {
        if (money <= 0) {
            System.out.println("无法转换");
        }
        int[] res = new int[prices.length];
        while (money > 0) {
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] <= money) {
                    res[i] = money / prices[i];
                    money = money % prices[i];
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] > 0) {
                System.out.println(prices[i]+"面值"+res[i]+"张");
            }
        }
    }

    public static void main(String[] args) {
        splitChange(123);
    }
}