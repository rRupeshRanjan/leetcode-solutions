package org.solutions.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTableProblems {

    /**
     * Q. 554 Brick Wall
     * <p>
     * There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks
     * each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the
     * same. Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the
     * edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two
     * vertical edges of the wall, in which case the line will obviously cross no bricks.
     * <p>
     * Given the 2D array wall that contains the information about the wall,
     * return the minimum number of crossed bricks after drawing such a vertical line.
     * <p>
     * tags:: hashMap, hashTable
     */
    public int leastBricks(List<List<Integer>> wall) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (List<Integer> list : wall) {
            int pos = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                pos += list.get(i);
                count.put(pos, count.getOrDefault(pos, 0) + 1);
                ans = Math.max(ans, count.get(pos));
            }
        }

        return wall.size() - ans;
    }

    /**
     * Q. 560 Subarray Sum Equals K
     * <p>
     * Given an array of integers nums and an integer k,
     * return the total number of continuous subarrays whose sum equals to k.
     * <p>
     * tags:: array, hashTable, hashMap
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;

        for (int n : nums) {
            sum += n;
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }

    /**
     * Q. 974 Subarray Sums Divisible by K
     * <p>
     * Given an array A of integers, return number of (contiguous, non-empty) subarrays that have a sum divisible by K.
     * <p>
     * tags:: array, hashTable
     */
    public int subarraysDivByK(int[] nums, int K) {
        // hashmap can be used in place of array as well
        int[] counter = new int[K];
        counter[0] = 1;
        int res = 0, curr = 0;

        for (int n : nums) {
            curr = Math.floorMod(curr + n, K);
            res += counter[curr];
            counter[curr]++;
        }

        return res;
    }

    /**
     * Q. 347 Top K Frequent Elements
     * <p>
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     * <p>
     * tags::heap, array, hashTable
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = Integer.MIN_VALUE;
        for (int num : nums) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
            maxFreq = Math.max(maxFreq, freq);
        }

        ArrayList<Integer>[] count = new ArrayList[maxFreq + 1];
        for (int key : freqMap.keySet()) {
            int value = freqMap.get(key);
            if (count[value] == null)
                count[value] = new ArrayList<>();
            count[value].add(key);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] != null)
                answer.addAll(count[i]);
            if (answer.size() == k)
                break;
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
