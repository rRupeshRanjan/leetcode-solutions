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
     * tags:: dp
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
     * tags:: dp
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
     * tags:: dp
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
}
