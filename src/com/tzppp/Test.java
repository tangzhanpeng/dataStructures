package com.tzppp;

import java.util.*;

public class Test {


    public static void main(String[] args) {
        Math.sqrt(2);

    }

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (n == ((int)Math.pow(2, i) | n)) {
                ++res;
            }
        }
        if (n < 1) {
            ++res;
        }
        return res;
    }
}


