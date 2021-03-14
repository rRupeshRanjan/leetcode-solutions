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
}
