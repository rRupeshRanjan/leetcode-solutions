package org.solutions.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamicProgrammingProblems {

    /*
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * You may assume that you have an infinite number of each kind of coin.
     *
     * Tags: dp, coinChange, array
     * */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /*
     * Q.823
     *
     * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
     * We make a binary tree using these integers, and each number may be used for any number of times.
     * Each non-leaf node's value should be equal to the product of the values of its children.
     * Return the number of binary trees we can make.
     *
     * The answer may be too large so return the answer modulo 109 + 7.
     *
     * Tags:: dp, array
     * */
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Long> dp = new HashMap<>();
        long count = 0;
        int mod = 1_000_000_007;

        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            dp.put(a, 1L);
            for (int j = 0; j < i; j++) {
                if (a % arr[j] == 0)
                    dp.put(a, (dp.get(a) + dp.get(arr[j]) * dp.getOrDefault(a / arr[j], 0L)) % mod);
            }
            count = (count + dp.get(arr[i])) % mod;
        }

        return (int) count;
    }

    /*
     * Q. 121
     *
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in
     * the future to sell that stock.
     *
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     *
     * tags:: dp, array
     * */
    public int maxProfit1(int[] prices) {
        int maxProfit = 0, buy = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy)
                maxProfit = Math.max(maxProfit, prices[i] - buy);
            else
                buy = prices[i];
        }

        return maxProfit;
    }

    /*
     * Q. 188
     *
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and integer k.
     * Find the maximum profit you can achieve. You may complete at most k transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     * tags:: dp,
     * This is a generalized version of maxProfit3
     * */
    public int maxProfit4(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int[] dp = new int[n];
        for (int i = 1; i <= k; i++) {
            int[] temp = new int[n];
            int maxDiff = -prices[0];
            for (int j = 1; j < n; j++) {
                temp[j] = Math.max(temp[j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[j] - prices[j]);
            }
            dp = temp;
        }

        return dp[n - 1];
    }

    /*
     * Q.123
     *
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     * tags:: dp,
     * */
    public int maxProfit3(int[] prices) {
        return maxProfit4(2, prices);
    }

    /*
     * Q. 309
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     *   After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *
     * tags:: dp,
     * Think like state transition problem
     * */
    public int maxProfit6(int[] prices) {
        int buy = -prices[0], sell = 0, cooldown = 0;
        for (int i = 1; i < prices.length; i++) {
            int tempBuy = Math.max(buy, cooldown - prices[i]);
            cooldown = sell;
            sell = Math.max(sell, buy + prices[i]);

            buy = tempBuy;
        }

        return sell;
    }

    /*
     * Q. 376
     *
     * Given an integer array nums, return the length of the longest wiggle sequence.
     * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate
     * between positive and negative. The first difference (if one exists) may be either positive or negative.
     * A sequence with fewer than two elements is trivially a wiggle sequence.
     *
     * Tags:: dp, greedy, array
     * */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;

        int up = 1, down = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }

        return Math.max(up, down);
    }

    /*
     * Q. 354
     *
     * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the
     * height of an envelope. One envelope can fit into another if and only if both the width and height of one envelope
     * are greater than the other envelope's width and height.
     * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
     *
     * Note: You cannot rotate an envelope.
     *
     * tags:: dp, array, grid
     * */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0]) ? (b[1] - a[1]) : (a[0] - b[0]));
        int[] dp = new int[envelopes.length];
        int ans = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int left = Arrays.binarySearch(dp, 0, ans, height);
            if (left < 0) left = -left - 1;
            if (left == ans) ans++;
            dp[left] = height;
        }
        return ans;
    }

    /*
     * Q. 300
     *
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements without
     * changing the order of the remaining elements.
     * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
     *
     * tags:: dp, array, LIS
     * */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int maxLen = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLen = Math.max(longestIncreasingPathDfs(matrix, i, j, m, n, cache), maxLen);
            }
        }

        return maxLen;
    }

    private int longestIncreasingPathDfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || x == m || y < 0 || y == n || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + longestIncreasingPathDfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
