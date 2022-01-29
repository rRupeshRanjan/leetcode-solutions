package org.solutions.leetcode;

import java.util.*;

public class HashTableProblems {

    /**
     * Q. 36 Valid Sudoku
     * <p>
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to following rules:
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * <p>
     * tags:: grid, hashTable
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> seenRow = new HashSet<>();
        Set<Character> seenCol = new HashSet<>();
        Set<Character> seenSqr = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !seenRow.add(board[i][j]))
                    return false;

                if (board[j][i] != '.' && !seenCol.add(board[j][i]))
                    return false;

                if (i % 3 == 0 && j % 3 == 0) {
                    for (int ii = i; ii < i + 3; ii++) {
                        for (int jj = j; jj < j + 3; jj++) {
                            if (board[ii][jj] != '.' && !seenSqr.add(board[ii][jj]))
                                return false;
                        }
                    }
                    seenSqr.clear();
                }
            }

            seenRow.clear();
            seenCol.clear();
        }

        return true;
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
        if (nums == null || nums.length == 0)
            return nums;

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

    /**
     * Q. 523 Continuous Subarray Sum
     * <p>
     * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least
     * two whose elements sum up to a multiple of k, or false otherwise.
     * <p>
     * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
     * <p>
     * tags:: array, hashTable
     */
    public boolean checkSubArraySum(int[] nums, int k) {
        Map<Integer, Integer> remainders = new HashMap<>();
        int sum = 0;

        remainders.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;

            if (!remainders.containsKey(sum)) {
                remainders.put(sum, i);
            } else {
                if (i - remainders.get(sum) > 1)
                    return true;
            }
        }
        return false;
    }

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
        if (wall == null || wall.size() == 0)
            return 0;

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
     * Given array of integers nums and integer k, return total number of continuous subarrays whose sum equals to k.
     * <p>
     * tags:: array, hashTable, hashMap
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

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
     * Q. 954 Array of Doubled Pairs
     * <p>
     * Given an integer array of even length arr, return true if it is possible to reorder arr such that
     * arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2,
     * or false otherwise.
     * <p>
     * tags:: hashTable, array
     */
    public boolean canReorderDoubled(int[] arr) {
        if (arr == null || arr.length == 0)
            return true;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int a : arr) {
            freqMap.put(a, freqMap.getOrDefault(a, 0) + 1);
        }

        Integer[] arr2 = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++)
            arr2[i] = arr[i];
        Arrays.sort(arr2, Comparator.comparing(Math::abs));

        for (int a : arr2) {
            if (freqMap.get(a) == 0)
                continue;

            if (freqMap.getOrDefault(2 * a, 0) <= 0)
                return false;

            freqMap.put(a, freqMap.get(a) - 1);
            freqMap.put(2 * a, freqMap.get(2 * a) - 1);
        }

        return true;
    }

    /**
     * Q. 974 Subarray Sums Divisible by K
     * <p>
     * Given an array A of integers, return number of (contiguous, non-empty) subarrays that have a sum divisible by K.
     * <p>
     * tags:: array, hashTable
     */
    public int subarraysDivByK(int[] nums, int K) {
        if (nums == null || nums.length == 0 || K == 0)
            return 0;

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
     * Q. 1331 Rank Transform of an Array
     * <p>
     * Given an array of integers arr, replace each element with its rank. The rank represents how large the element is.
     * The rank has the following rules:
     * Rank is an integer starting from 1.
     * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
     * Rank should be as small as possible.
     * <p>
     * tags:: hashMap, array, sorting
     */
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0)
            return arr;

        int[] copy = arr.clone();
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        Arrays.sort(copy);
        for (int c : copy) {
            if (!rankMap.containsKey(c))
                rankMap.put(c, rank++);
        }

        for (int i = 0; i < arr.length; i++)
            arr[i] = rankMap.get(arr[i]);

        return arr;
    }
}
