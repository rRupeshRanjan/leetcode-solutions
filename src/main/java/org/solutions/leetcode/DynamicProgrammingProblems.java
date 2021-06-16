package org.solutions.leetcode;

import java.util.*;

public class DynamicProgrammingProblems {

    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * You may assume that you have an infinite number of each kind of coin.
     * <p>
     * Tags: dp, coinChange, array
     */
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

    /**
     * Q.823
     * <p>
     * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
     * We make a binary tree using these integers, and each number may be used for any number of times.
     * Each non-leaf node's value should be equal to the product of the values of its children.
     * Return the number of binary trees we can make.
     * <p>
     * The answer may be too large so return the answer modulo 109 + 7.
     * <p>
     * Tags:: dp, array
     */
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

    /**
     * Q. 121
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in
     * the future to sell that stock.
     * <p>
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * <p>
     * tags:: dp, array
     */
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

    /**
     * Q. 188
     * <p>
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and integer k.
     * Find the maximum profit you can achieve. You may complete at most k transactions.
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * tags:: dp,
     * This is a generalized version of maxProfit3
     */
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

    /**
     * Q.123
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * tags:: dp,
     */
    public int maxProfit3(int[] prices) {
        return maxProfit4(2, prices);
    }

    /**
     * Q. 309
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * tags:: dp,
     * Think like state transition problem
     */
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

    /**
     * Q. 376
     * <p>
     * Given an integer array nums, return the length of the longest wiggle sequence.
     * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate
     * between positive and negative. The first difference (if one exists) may be either positive or negative.
     * A sequence with fewer than two elements is trivially a wiggle sequence.
     * <p>
     * Tags:: dp, greedy, array
     */
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

    /**
     * Q. 354
     * <p>
     * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the
     * height of an envelope. One envelope can fit into another if and only if both the width and height of one envelope
     * are greater than the other envelope's width and height.
     * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
     * <p>
     * Note: You cannot rotate an envelope.
     * <p>
     * tags:: dp, array, grid
     */
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

    /**
     * Q. 300
     * <p>
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements without
     * changing the order of the remaining elements.
     * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
     * <p>
     * tags:: dp, array, LIS
     */
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

    /**
     * Q. 329
     * <p>
     * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
     * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or
     * move outside the boundary (i.e., wrap-around is not allowed).
     * <p>
     * tags:: dp
     */
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

    /**
     * Q. 377
     * <p>
     * Given an array of distinct integers nums and a target integer target, return the number of possible combinations
     * that add up to target. The answer is guaranteed to fit in a 32-bit integer.
     * <p>
     * tags:: dp
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num)
                    dp[i] += dp[i - num];
            }
        }

        return dp[target];
    }

    /**
     * Q. 120
     * <p>
     * Given a triangle array, return the minimum path sum from top to bottom. For each step,
     * you may move to an adjacent number of the row below. More formally, if you are on index i on the current row,
     * you may move to either index i or index i + 1 on the next row.
     * <p>
     * tags:: dp
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int temp = triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                triangle.get(i).set(j, temp);
            }
        }

        return triangle.get(0).get(0);
    }

    /**
     * Q. 1074
     * <p>
     * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
     * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
     * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different
     * if they have some coordinate that is different: for example, if x1 != x1'.
     * <p>
     * tags:: array, dynamicProgramming, dp
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        Map<Integer, Integer> counter = new HashMap<>();
        int sum, ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                sum = 0;

                for (int k = 0; k < m; k++) {
                    sum += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    ans += counter.getOrDefault(sum - target, 0);
                    counter.put(sum, counter.getOrDefault(sum, 0) + 1);
                }
            }
        }

        return ans;
    }

    /**
     * Q. 62
     * <p>
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid. How many possible unique paths are there?
     * <p>
     * tags:: dp, dynamicProgramming
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n], prev = new int[n];
        for (int i = 0; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = prev[j] + dp[j - 1];
            }
            prev = dp;
        }

        return dp[n - 1];
    }

    /**
     * Q. 63
     * <p>
     * A robot is located at the top-left corner of a m x n grid. The robot can only move either down or right at any
     * point in time. The robot is trying to reach the bottom-right corner of the grid. Now consider if some obstacles
     * are added to the grids. How many unique paths would there be?
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     * <p>
     * tags:: dp, dynamicProgramming
     */
    public int uniquePathsWithObstacles(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1)
                    A[i][j] = 0;
                else {
                    if (i == 0 && j == 0) A[i][j] = 1;

                    if (i > 0) A[i][j] = A[i - 1][j];
                    if (j > 0) A[i][j] += A[i][j - 1];
                }
            }
        }

        return A[m - 1][n - 1];
    }

    /**
     * Q. 583. Delete Operation for Two Strings
     * <p>
     * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
     * In one step, you can delete exactly one character in either string.
     * <p>
     * tags:: string, dp
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] prev = new int[n + 1];

        for (int i = 0; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    curr[j] = i + j;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    curr[j] = prev[j - 1];
                else
                    curr[j] = Math.min(prev[j], curr[j - 1]) + 1;
            }
            prev = curr;
        }

        return prev[n];
    }

    /**
     * Q. 1653. Minimum Deletions to Make String Balanced
     * <p>
     * You are given a string s consisting only of characters 'a' and 'b'. You can delete any number of characters in s
     * to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' & s[j]= 'a'.
     * Return the minimum number of deletions needed to make s balanced.
     * <p>
     * tags:: dp, string
     */
    public int minimumDeletions(String s) {
        int l = s.length(), bCount = 0;
        int curr = 0, prev = 0;

        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 'a') {
                curr = Math.min(prev + 1, bCount);
            } else {
                curr = prev;
                bCount++;
            }
            prev = curr;
        }

        return curr;
    }

    /**
     * Q. 53 Maximum Subarray
     * <p>
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
     * sum and return its sum.
     * <p>
     * tags:: array, dp
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    /**
     * Q. 1749 Maximum Absolute Sum of Any Subarray
     * <p>
     * You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is
     * abs(numsl + numsl+1 + ... + numsr-1 + numsr). Return max absolute sum of any (possibly empty) subarray of nums.
     * <p>
     * tags:: array, dp
     */
    public int maxAbsoluteSum(int[] nums) {
        int max = Math.abs(nums[0]), maxSum = nums[0], minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxSum = Math.max(nums[i], maxSum + nums[i]);
            minSum = Math.min(nums[i], minSum + nums[i]);

            max = Math.max(max, Math.max(Math.abs(maxSum), Math.abs(minSum)));
        }

        return max;
    }

    /**
     * Q. 1856 Maximum Subarray Min-Product
     * <p>
     * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
     * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
     * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
     * Since the answer may be large, return it modulo 109 + 7.
     * <p>
     * Note that the min-product should be maximized before performing the modulo operation.
     * A subarray is a contiguous part of an array.
     * <p>
     * tags:: dp, maximumSubArray
     *
     * @see org.solutions.leetcode.ArrayProblems#largestRectangleArea(int[])
     */
    public int maxSumMinProduct(int[] nums) {
        long[] dp = new long[nums.length + 1];
        long maxSum = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++)
            dp[i + 1] = dp[i] + nums[i];

        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && (i == nums.length || nums[stack.peek()] > nums[i])) {
                int j = stack.pop();
                maxSum = Math.max(maxSum, (dp[i] - dp[stack.isEmpty() ? 0 : stack.peek() + 1]) * nums[j]);
            }
            stack.push(i);
        }
        return (int) (maxSum % 1000000007);
    }

    /**
     * Q. 746 Min Cost Climbing Stairs
     * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost,
     * you can either climb one or two steps. You can either start from the step with index 0, or the step with index 1.
     * <p>
     * Return the minimum cost to reach the top of the floor.
     * <p>
     * tags:: dp, dynamicProgramming
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length < 2)
            return 0;

        int len = cost.length;
        int oneStepBack = cost[1], twoStepBack = cost[0];

        for (int i = 2; i < len; i++) {
            int temp = oneStepBack;
            oneStepBack = cost[i] + Math.min(oneStepBack, twoStepBack);
            twoStepBack = temp;
        }

        return Math.min(oneStepBack, twoStepBack);
    }
}
