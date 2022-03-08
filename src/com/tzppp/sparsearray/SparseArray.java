package com.tzppp.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        int[][] sourceArray = {
                {0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,2,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };
//        int[][] sparseArray = getSparseArray(sourceArray);
//        printArray(sparseArray);
//
//        int[][] source2 = getSourceArray(sparseArray);
//        printArray(source2);
        String path = "src/com/tzppp/sparsearray/sparseArray.data";
        saveToSrc(sourceArray, path);
        printArray(readFromSrc(path));
    }

    public static void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 普通矩阵数组转换为稀疏数组
     * @param sourceArray 普通数组
     * @return
     */
    public static int[][] getSparseArray(int[][] sourceArray) {
        int x = sourceArray.length;
        int y = sourceArray[0].length;
        int countNoZero = 0;
        for (int[] ints : sourceArray) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    ++countNoZero;
                }
            }
        }
        int[][] sparseArray = new int[countNoZero + 1][3];
        sparseArray[0] = new int[] {x, y, countNoZero};
        int count = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = 0; j < sourceArray[i].length; j++) {
                if (sourceArray[i][j] > 0) {
                    sparseArray[++count] = new int[] {i, j, sourceArray[i][j]};
                }
            }
        }
        return sparseArray;
    }

    public static void saveToSrc(int[][] sourceArray, String path) throws IOException {
        int[][] sparseArray = getSparseArray(sourceArray);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        for (int[] ints : sparseArray) {
            bufferedWriter.write(ints[0] + "," + ints[1] +"," + ints[2]);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static int[][] readFromSrc(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = bufferedReader.readLine();
        final String[] split = line.split(",");
        int[][] sourceArray = new int[Integer.parseInt(split[0])][Integer.parseInt(split[1])];
        while ((line = bufferedReader.readLine()) != null) {
            final String[] split1 = line.split(",");
            sourceArray[Integer.parseInt(split1[0])][Integer.parseInt(split1[1])] = Integer.parseInt(split1[2]);
        }
        return sourceArray;
    }

    /**
     * 稀疏数组转换为普通数据
     * @param sparseArray 稀疏数组
     * @return
     */
    public static int[][] getSourceArray(int[][] sparseArray) {
        int[][] sourceArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            sourceArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return sourceArray;
    }
}