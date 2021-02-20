package org.solutions.leetcode.utils;

import java.util.List;

public class ArrayUtils {

    public int[][] convert2DListTo2DArray(List<List<Integer>> input) {
        int[][] result = new int[input.size()][];

        for(int i=0; i<input.size(); i++) {
            result[i] = input.get(i)
                    .stream()
                    .mapToInt(n -> n)
                    .toArray();
        }

        return result;
    }
}
