package com.tzppp.BitOperation;

/**
 * 位运算
 * 乘除法
 */
public class MD {
    public static void main(String[] args) {
        System.out.println(getBits(-2));
    }

    /**
     * 获取二进制位数
     * @return
     */
    public static int getBits(int nums) {
        int bits = 0;
        while (nums != -1 && nums != 0) {
            nums >>= 1;
            ++bits;
        }
        return bits;
    }

//    public static int getIndexBit(int num,int pos){//获取从右到左的第pos位置的值1/0
////        int index = 1 << pos;
////        if((num&index) >> pos)
////            return 1;
////        else
////            return 0;
//    }
//    int getBit(int num,int pos){//获取从右到左的第pos位置的值1/0
//        pos = pos-1;
//        return getIndexBit(num,pos);
//    }
}