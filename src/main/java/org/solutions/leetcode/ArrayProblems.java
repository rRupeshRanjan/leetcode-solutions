package org.solutions.leetcode;

import java.util.*;

public class ArrayProblems {

    private final static int MOD = (int) 1e9 + 7;
    private int subsetXORSum;

    /**
     * Q. 594 Longest Harmonious Subsequence
     * <p>
     * We define a harmonious array as an array where the difference between its maximum value and its minimum value is
     * exactly 1. Given an integer array nums, return the length of its longest harmonious subsequence among all its
     * possible subsequences. A subsequence of array is a sequence that can be derived from the array by deleting some
     * or no elements without changing the order of the remaining elements.
     * <p>
     * tags:: sorting, hashMap
     */
    public int findLHS(int[] nums, String way) {
        int maxLen = 0;

        switch (way) {
            case "brute-force":
                for (int k : nums) {
                    int currLen = 0;
                    boolean flag = false;
                    for (int num : nums) {
                        if (k == num)
                            currLen++;
                        else if (num == k + 1) {
                            currLen++;
                            flag = true;
                        }
                    }

                    if (flag)
                        maxLen = Math.max(maxLen, currLen);
                }
                return maxLen;
            case "sorting":
                Arrays.sort(nums);
                int prevLen = 0;

                for (int i = 0; i < nums.length; i++) {
                    int currLen = 1;
                    if (i > 0 && nums[i] - nums[i - 1] == 1) {
                        while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                            currLen++;
                            i++;
                        }
                        maxLen = Math.max(maxLen, currLen + prevLen);
                        prevLen = currLen;
                    } else {
                        while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                            currLen++;
                            i++;
                        }
                        prevLen = currLen;
                    }
                }
                return maxLen;
            case "hashmap":
                Map<Integer, Integer> map = new HashMap<>();
                for (int num : nums) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }

                for (int key : map.keySet()) {
                    if (map.containsKey(key + 1)) {
                        maxLen = Math.max(maxLen, map.get(key) + map.get(key + 1));
                    }
                }
                return maxLen;
            default:
                return -1;
        }
    }

    /**
     * Q.1640 Check Array Formation Through Concatenation
     * <p>
     * You are given an array of distinct integers arr and an array of integer arrays pieces,
     * where the integers in pieces are distinct.
     * Your goal is to form arr by concatenating the arrays in pieces in any order.
     * However, you are not allowed to reorder the integers in each array pieces[i].
     * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
     * <p>
     * Tags:: HashMap
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] piece : pieces) {
            map.put(piece[0], new ArrayList<>());
            for (int j = 1; j < piece.length; j++) {
                map.get(piece[0]).add(piece[j]);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                return false;
            } else {
                for (Integer value : map.get(arr[i])) {
                    if (arr[++i] != value)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Q. 11 Container With Most Water
     * <p>
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
     * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
     * <p>
     * Tags:: twoPointerApproach
     */
    public int maxWaterContainerArea(int[] height) {
        int maxArea = 0, start = 0, end = height.length - 1;

        while (start < end) {
            maxArea = Math.max(maxArea, Math.min(height[start], height[end]) * (end - start));
            if (height[start] < height[end]) {
                int temp = height[start];
                while (height[start] <= temp && start < end)
                    start++;
            } else {
                int temp = height[end];
                while (height[end] <= temp && start < end)
                    end--;
            }
        }

        return maxArea;
    }

    /**
     * Q. 413 Arithmetic Slices
     * <p>
     * A sequence of numbers is called arithmetic if
     * it consists of at least three elements and if the difference between any two consecutive elements is the same.
     * Write a function that should return the number of arithmetic slices in the array A.
     * <p>
     * Tags:: dynamicProgramming
     */
    public int numberOfArithmeticSlices(int[] A) {
        int sum = 0, dp = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }

    /**
     * Q. 122 Best Time to Buy and Sell Stock II
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete as many transactions as you like
     * (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * tags:: array, greedy
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }

        /*
        Alternate approach with state machine
        int buy = -prices[0], sell = 0;
        for(int i=1; i<prices.length; i++) {
            buy = Math.max(buy, sell + prices[i]);
            sell = Math.max(sell, buy - prices[i]);
        }
        return buy;
        */
        return profit;
    }

    /**
     * Q. 714 Best Time to Buy and Sell Stock with Transaction Fee
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day,
     * and an integer fee representing a transaction fee. Find the maximum profit you can achieve.
     * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
     * <p>
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * <p>
     * tags:: dp, array, greedy
     */
    public int maxProfit5(int fee, int[] prices) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }

        return cash;
    }

    /**
     * Q. 923 3Sum With Multiplicity
     * <p>
     * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and
     * arr[i] + arr[j] + arr[k] == target. As the answer can be very large, return it modulo 109 + 7.
     * <p>
     * tags::math, array, sum
     */
    public int threeSumMulti(int[] arr, int target) {
        int MOD = 1_000_000_007;
        long[] count = new long[101];

        for (int a : arr)
            count[a]++;

        int[] keys = Arrays.stream(arr).distinct().sorted().toArray();
        long result = 0;

        for (int i = 0; i < keys.length; i++) {
            int x = keys[i], remTarget = target - x;
            int j = i, k = keys.length - 1;

            while (j <= k) {
                int y = keys[j], z = keys[k];
                if (y + z > remTarget) {
                    k--;
                } else if (y + z < remTarget) {
                    j++;
                } else {
                    if (i < j && j < k) {
                        result += count[x] * count[y] * count[z];
                    } else if (i == j && j < k) {
                        result += count[x] * (count[x] - 1) / 2 * count[z];
                    } else if (i < j && j == k) {
                        result += count[x] * count[y] * (count[y] - 1) / 2;
                    } else {
                        result += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                    }

                    result %= MOD;
                    j++;
                    k--;
                }
            }
        }

        return (int) result;
    }

    /**
     * Q. 870 Advantage Shuffle
     * <p>
     * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of
     * indices i for which A[i] > B[i]. Return any permutation of A that maximizes its advantage with respect to B.
     * <p>
     * tags:: array, greedy
     */
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        TreeMap<Integer, Integer> countMap = new TreeMap<>();

        for (int a : A)
            countMap.put(a, countMap.getOrDefault(a, 0) + 1);

        for (int i = 0; i < n; i++) {
            Integer curr = countMap.higherKey(B[i]);
            if (curr == null)
                curr = countMap.firstKey();
            result[i] = curr;

            if (countMap.get(curr) == 1)
                countMap.remove(curr);
            else
                countMap.put(curr, countMap.get(curr) - 1);
        }

        return result;
    }

    /**
     * Q. 775 Global and Local Inversions
     * <p>
     * You are given an integer array nums of length n which represents a permutation of all the integers in
     * the range [0, n - 1].
     * The number of global inversions is the number of the different pairs (i, j) where:
     * 0 <= i < j < n
     * nums[i] > nums[j]
     * The number of local inversions is the number of indices i where:
     * 0 <= i < n - 1
     * nums[i] > nums[i + 1]
     * <p>
     * Return true if the number of global inversions is equal to the number of local inversions.
     * <p>
     * tags:: array,
     */
    public boolean isIdealPermutation(int[] nums) {
        int cmax = -1;
        for (int i = 0; i < nums.length - 2; i++) {
            cmax = Math.max(cmax, nums[i]);
            if (cmax > nums[i + 2])
                return false;
        }

        return true;
    }

    /**
     * Q. 84 Largest Rectangle in Histogram
     * <p>
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
     * return the area of the largest rectangle in the histogram.
     * <p>
     * tags:: array
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i <= heights.length; i++) {
            int cur = (i == heights.length) ? -1 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= cur) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, h * w);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * Q. 1642 Furthest Building You Can Reach
     * <p>
     * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
     * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
     * While moving from building i to building i+1 (0-indexed),
     * If the current building's height >= next building's height, you don't need ladder or bricks.
     * If the current building's height < next building's height, you can use one ladder or (h[i+1] - h[i]) bricks.
     * <p>
     * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
     * <p>
     * tags:: array, priorityQueue
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int d = heights[i + 1] - heights[i];
            if (d > 0)
                pq.add(d);
            if (pq.size() > ladders)
                bricks -= pq.poll();
            if (bricks < 0)
                return i;
        }

        return heights.length - 1;
    }

    /**
     * Q. 34 Find First and Last Position of Element in Sorted Array
     * <p>
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a
     * given target value. If target is not found in the array, return [-1, -1].
     * <p>
     * return:: array, binarySearch
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];

        ans[0] = getFirstPosition(nums, target);
        ans[1] = getLastPosition(nums, target);

        return ans;
    }

    private int getFirstPosition(int[] nums, int target) {
        int index = -1, start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] >= target) end = mid - 1;
            else start = mid + 1;

            if (nums[mid] == target)
                index = mid;
        }

        return index;
    }

    private int getLastPosition(int[] nums, int target) {
        int index = -1, start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] <= target) start = mid + 1;
            else end = mid - 1;

            if (nums[mid] == target)
                index = mid;
        }

        return index;
    }

    /**
     * Q. 1480 Running Sum of 1d Array
     * <p>
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * Return the running sum of nums.
     * <p>
     * tags:: array
     */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        return nums;
    }

    /**
     * Q. 665 Non-decreasing Array
     * <p>
     * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most
     * one element. We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based)
     * such that (0 <= i <= n - 2).
     * <p>
     * tags:: array
     */
    public boolean checkPossibility(int[] nums) {
        int err = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                err++;
                if (err > 1 || (i > 1 && i < nums.length - 1 && nums[i - 2] > nums[i] && nums[i + 1] < nums[i - 1]))
                    return false;
            }
        }

        return true;
    }

    /**
     * Q. 55 Jump Game
     * <p>
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * <p>
     * tags:: array, greedy
     */
    public boolean jumpGame(int[] nums) {
        if (nums == null || nums.length <= 1)
            return true;

        int lastIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= lastIndex)
                lastIndex = i;
        }

        return lastIndex == 0;
    }

    /**
     * Q. 45 Jump Game II
     * <p>
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position. Your goal is to reach the last
     * index in the minimum number of jumps. You can assume that you can always reach the last index.
     * <p>
     * tags:: array, greedy
     */
    public int jumpGameII(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;

        int reachable = 0, count = 0, curr = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            reachable = Math.max(reachable, nums[i] + i);
            if (curr == i) {
                curr = reachable;
                count++;
            }
        }

        return count;
    }

    /**
     * Q. 1306 Jump Game III
     * <p>
     * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
     * When you are at index i, you can jump to i+arr[i] or i-arr[i], check if you can reach to any index with value 0.
     * <p>
     * Notice that you can not jump outside of the array at any time.
     * <p>
     * tags::bfs, dfs
     */
    public boolean jumpGameIII(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0)
            return false;
        return (arr[start] = -arr[start]) == 0 ||
                jumpGameIII(arr, start + arr[start]) ||
                jumpGameIII(arr, start - arr[start]);

        /*
        // bfs approach
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            for(int i=q.size(); i>0; i--) {
                int poll = q.poll();

                if(arr[poll] == 0)
                    return true;

                int left = poll - arr[poll];
                int right = poll + arr[poll];

                arr[poll] = -arr[poll];
                if(left>=0 && arr[left] >= 0) q.offer(left);
                if(right<arr.length && arr[right] >= 0) q.offer(right);
            }
        }

        return false;
        */
    }

    /**
     * Q. 1345 Jump Game IV
     * <p>
     * Given an array of integers arr, you are initially positioned at the first index of the array.
     * <p>
     * In one step you can jump from index i to index:
     * i + 1 where: i + 1 < arr.length.
     * i - 1 where: i - 1 >= 0.
     * j where: arr[i] == arr[j] and i != j.
     * Return the minimum number of steps to reach the last index of the array.
     * Notice that you can not jump outside of the array at any time.
     * <p>
     * tags:: bfs
     */
    public int jumpGameIV(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        boolean[] visited = new boolean[arr.length];
        int step = 0;

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        q.add(0);
        visited[0] = true;

        while ((!q.isEmpty())) {
            for (int i = q.size(); i > 0; i--) {
                int node = q.poll();

                if (node == arr.length - 1)
                    return step;

                List<Integer> indices = map.getOrDefault(arr[node], new ArrayList<>());
                indices.add(node + 1);
                indices.add(node - 1);
                for (int index : indices) {
                    if (index >= 0 && index < arr.length && !visited[index]) {
                        visited[index] = true;
                        q.offer(index);
                    }
                }
                map.remove(arr[node]);
            }
            step++;
        }

        return -1;
    }

    /**
     * 1354. Construct Target Array With Multiple Sums
     * <p>
     * You are given an array target of n integers. From a starting array arr consisting of n 1's,
     * you may perform the following procedure :
     * let x be the sum of all elements currently in your array.
     * choose index i, such that 0 <= i < n and set the value of arr at index i to x.
     * You may repeat this procedure as many times as needed.
     * <p>
     * Return true if it is possible to construct the target array from arr, otherwise, return false.
     * <p>
     * tags:: array, greedy, priorityQueue
     */
    public boolean isPossibleToConstructTargetArray(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        int sum = 0;

        for (int t : target) {
            pq.add(t);
            sum += t;
        }

        while (!pq.isEmpty() && pq.peek() != 1) {
            int num = pq.poll();
            sum -= num;
            if (num < sum || sum <= 0)
                return false;

            num %= sum;
            sum += num;

            pq.add(num > 0 ? num : sum);
        }

        return true;
    }

    /**
     * Q. 1423 Maximum Points You Can Obtain from Cards
     * <p>
     * There are several cards arranged in a row, and each card has an associated number of points. The points are given
     * in the integer array cardPoints. In one step, you can take one card from the beginning or from the end of the row.
     * You have to take exactly k cards. Your score is the sum of the points of the cards you have taken.
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     * <p>
     * tags:: array, sliding-window
     * hint:: either pick from last or start, but overall always k elements. find max of those.
     */
    public int maxScore(int[] cardPoints, int k) {
        int maxScore, currScore = 0;
        for (int i = 0; i < k; i++) {
            currScore += cardPoints[i];
        }

        maxScore = currScore;
        for (int i = 0; i < k; i++) {
            currScore = currScore - cardPoints[k - i - 1] + cardPoints[cardPoints.length - i - 1];
            maxScore = Math.max(maxScore, currScore);
        }

        return maxScore;
    }

    /**
     * Q. 453 Minimum Moves to Equal Array Elements
     * <p>
     * Given an integer array nums of size n, return minimum number of moves required to make all array elements equal.
     * In one move, you can increment n - 1 elements of the array by 1.
     * <p>
     * tags:: array
     */
    public int minMoves(int[] nums) {
        int count = 0;
        int min = Integer.MAX_VALUE;

        for (int num : nums)
            min = Math.min(min, num);

        for (int num : nums)
            count += (num - min);

        return count;
    }

    /**
     * Q. 462 Minimum Moves to Equal Array Elements II
     * <p>
     * Given an integer array nums of size n, return minimum number of moves required to make all array elements equal.
     * In one move, you can increment or decrement an element of the array by 1.
     * <p>
     * tags:: array, sorting
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int count = 0, mid = nums.length / 2;

        for (int num : nums) {
            count += Math.abs(num - nums[mid]);
        }

        return count;
    }

    /**
     * Q. 1863. Sum of All Subset XOR Totals
     * <p>
     * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
     * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
     * Given an array nums, return the sum of all XOR totals for every subset of nums.
     * <p>
     * Note: Subsets with the same elements should be counted multiple times.
     * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
     * <p>
     * tags:: array, backtracking
     */
    public int subsetXORSum(int[] nums) {
        subsetXORSum = 0;
        backtrackSubsetXORSum(nums, 0, 0);
        return subsetXORSum;
    }

    private void backtrackSubsetXORSum(int[] nums, int index, int curr) {
        subsetXORSum += curr;

        for (int i = index; i < nums.length; i++) {
            curr ^= nums[i];
            backtrackSubsetXORSum(nums, i + 1, curr);
            curr ^= nums[i];
        }
    }

    /**
     * Q. 1695 Maximum Erasure Value
     * <p>
     * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
     * The score you get by erasing the subarray is equal to the sum of its elements.
     * Return the maximum score you can get by erasing exactly one subarray.
     * <p>
     * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is,
     * if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
     * <p>
     * tags:: array, twoPointer
     */
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length, start = 0, end = 0, maxSum = 0, sum = 0;
        Set<Integer> set = new HashSet<>();

        while (end < n) {
            if (set.add(nums[end])) {
                sum += nums[end++];
                maxSum = Math.max(maxSum, sum);
            } else {
                sum -= nums[start];
                set.remove(nums[start++]);
            }
        }
        return maxSum;
    }

    /**
     * Q. 318 Maximum Product of Word Lengths
     * <p>
     * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words \
     * do not share common letters. If no such two words exist, return 0.
     * <p>
     * tags:: array, bitManipulation
     */
    public int maxProduct(String[] words) {
        int max = 0, n = words.length;
        int[] bits = new int[n];

        for (int i = 0; i < n; i++) {
            int num = 0;
            for (char ch : words[i].toCharArray()) {
                num |= 1 << (ch - 'a');
            }
            bits[i] = num;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }

        return max;
    }

    /**
     * Q. 164 Maximum Gap
     * Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
     * If the array contains less than two elements, return 0.
     * <p>
     * You must write an algorithm that runs in linear time and uses linear extra space.
     * <p>
     * tags::array, bucketSort
     */
    // TODO: Implement this
    public int maximumGap(int[] nums) {
        return -1;
    }

    /**
     * Q. 1465 Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
     * <p>
     * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts
     * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and
     * similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
     * <p>
     * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in
     * the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
     * <p>
     * tags:: array
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long hcutsMax = getMaxDiff(horizontalCuts, h);
        long vcutsMax = getMaxDiff(verticalCuts, w);

        return ((int) ((hcutsMax * vcutsMax) % 1000000007));
    }

    private int getMaxDiff(int[] arr, int n) {
        Arrays.sort(arr);
        int l = arr.length, maxDiff = Math.max(arr[0], n - arr[l - 1]);
        for (int i = 1; i < arr.length; i++) {
            maxDiff = Math.max(maxDiff, arr[i] - arr[i - 1]);
        }

        return maxDiff;
    }

    /**
     * Q. 849 Maximize Distance to Closest Person
     * <p>
     * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
     * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
     * There is at least one empty seat, and at least one person sitting.
     * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
     * <p>
     * Return that maximum distance to the closest person.
     * <p>
     * tags:: array
     */
    public int maxDistToClosest(int[] seats) {
        int prevSeat = -1, maxDis = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                maxDis = (prevSeat < 0) ? i : Math.max(maxDis, (i - prevSeat) / 2);
                prevSeat = i;
            }
        }

        return Math.max(maxDis, seats.length - 1 - prevSeat);
    }

    /**
     * Q. 1589 Maximum Sum Obtained of Any Permutation
     * <p>
     * We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi]. The ith request
     * asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi
     * are 0-indexed.
     * <p>
     * Return the maximum total sum of all requests among all permutations of nums.
     * Since the answer may be too large, return it modulo 109 + 7.
     * <p>
     * tags:: sweepLine, array, greedy
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        long sum = 0;
        int n = nums.length;
        int[] count = new int[n];
        for (int[] r : requests) {
            count[r[0]]++;
            if (r[1] + 1 < n) {
                count[r[1] + 1]--;
            }
        }

        for (int i = 1; i < n; i++)
            count[i] += count[i - 1];

        Arrays.sort(count);
        Arrays.sort(nums);
        int i = n - 1;
        while (i >= 0 && count[i] > 0) {
            sum += (long) nums[i] * count[i--];
        }

        return (int) (sum % MOD);
    }

    /**
     * Q. 1094 Car Pooling
     * You are driving a vehicle that has capacity empty seats initially available for passengers. The vehicle only
     * drives east (ie. it cannot turn around and drive west.)
     * <p>
     * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the
     * i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.
     * The locations are given as the number of kilometers due east from your vehicle's initial location.
     * <p>
     * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
     * <p>
     * trips.length <= 1000
     * tags:: sweepLine, greedy, array
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] count = new int[1001];
        for (int[] trip : trips) {
            count[trip[1]] += trip[0];
            count[trip[2]] -= trip[0];
        }

        for (int i = 1; i < 1001; i++) {
            count[i] += count[i - 1];
            if (count[i] > capacity)
                return false;
        }

        return true;
    }

    /**
     * Q. 1383 Maximum Performance of a Team
     * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n
     * engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer
     * respectively.
     * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
     * <p>
     * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their
     * engineers. Return the maximum performance of this team.
     * <p>
     * Since the answer can be a huge number, return it modulo 109 + 7.
     * <p>
     * tags:: array, greedy, priorityQueue, sorting
     */
    public int maxPerformance(int n, int k, int[] speed, int[] efficiency) {
        int[][] candidates = new int[n][2];
        for (int i = 0; i < n; i++) {
            candidates[i] = new int[]{efficiency[i], speed[i]};
        }

        Arrays.sort(candidates, (a, b) -> (b[0] - a[0]));
        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(Comparator.comparingInt((i -> i)));

        long maxPerf = 0, speedSum = 0;
        for (int[] candidate : candidates) {
            int currSpeed = candidate[1];
            speedSum += currSpeed;
            speedHeap.add(currSpeed);

            if (speedHeap.size() > k)
                speedSum -= speedHeap.remove();

            maxPerf = Math.max(maxPerf, candidate[0] * speedSum);
        }

        return (int) (maxPerf % MOD);
    }

    /**
     * Q. 128 Longest Consecutive Sequence
     * <p>
     * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
     * You must write an algorithm that runs in O(n) time.
     * <p>
     * tags:: array, unionFind
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int currStreak = 0, maxStreak = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                currStreak = 1;
                int currNum = num;

                while (set.contains(currNum + 1)) {
                    currNum++;
                    currStreak++;
                }

                maxStreak = Math.max(maxStreak, currStreak);
            }
        }

        return maxStreak;
    }

    /**
     * Q. 33 Search in Rotated Sorted Array
     * There is an integer array nums sorted in ascending order (with distinct values).
     * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
     * such that resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
     * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * <p>
     * Given the array nums after the rotation and an integer target,
     * return the index of target if it is in nums, or -1 if it is not in nums.
     * <p>
     * You must write an algorithm with O(log n) runtime complexity.
     * <p>
     * tags:: binarySearch
     */
    public int rotatedBinarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Q. 134 Gas Station
     * <p>
     * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its
     * next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
     * <p>
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the
     * circuit once in the clockwise direction, otherwise return -1.
     * If there exists a solution, it is guaranteed to be unique
     * <p>
     * tags:: array, greedy
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int accumulate = 0, total = 0, start = 0;

        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (accumulate + gas[i] < cost[i]) {
                start = i + 1;
                accumulate = 0;
            } else {
                accumulate += gas[i] - cost[i];
            }
        }

        return (total >= 0) ? start : -1;
    }

    /**
     * Q. 456 132 Pattern
     * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
     * such that i < j < k and nums[i] < nums[k] < nums[j].
     * <p>
     * Return true if there is a 132 pattern in nums, otherwise, return false.
     * <p>
     * tags:: array,
     * <p>
     */
    public boolean find132pattern(int[] nums) {
        if (nums.length <= 2)
            return false;

        Stack<Integer> stack = new Stack<>();
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            min[i] = Math.min(min[i - 1], nums[i]);

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= min[i])
                stack.pop();
            if (!stack.isEmpty() && stack.peek() < nums[i])
                return true;
            stack.push(nums[i]);
        }

        return false;
    }

    /**
     * Q. 473 Matchsticks to Square
     * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to
     * use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each
     * matchstick must be used exactly one time.
     * <p>
     * Return true if you can make this square and false otherwise.
     * <p>
     * tags:: subsetSum, array dfs
     */
    public boolean makeSquare(int[] nums) {
        if (nums == null || nums.length < 4)
            return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0)
            return false;

        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return dfsSubSetSum(nums, visited, nums.length - 1, 0, sum / 4, 4);
    }

    /**
     * Q. 698 Partition to K Equal Sum Subsets
     * Given an integer array nums and an integer k, return true if it is possible to divide this array into
     * k non-empty subsets whose sums are all equal.
     * <p>
     * tags:: subsetSum, array, dfs
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k)
            return false;

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0)
            return false;

        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return dfsSubSetSum(nums, visited, nums.length - 1, 0, sum / k, k);
    }

    /**
     * helper dfs method fo k subsets with equal sum
     */
    private boolean dfsSubSetSum(int[] nums, boolean[] visited, int index, int sum, int target, int round) {
        if (round == 0)
            return true;

        if (sum == target && dfsSubSetSum(nums, visited, nums.length - 1, 0, target, round - 1))
            return true;

        for (int i = index; i >= 0; i--) {
            if (!visited[i] && sum + nums[i] <= target) {
                visited[i] = true;
                if (dfsSubSetSum(nums, visited, i - 1, sum + nums[i], target, round))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    /**
     * Q. 795 Number of Subarrays with Bounded Maximum
     * <p>
     * We are given an array nums of positive integers, and two positive integers left and right (left <= right).
     * <p>
     * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element
     * in that subarray is at least left and at most right.
     * <p>
     * tags:: array, twoPointer
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int start = -1, end = -1, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                start = i;
                end = i;
            } else if (nums[i] < left) {
                count += end - start;
            } else {
                end = i;
                count += end - start;
            }
        }

        return count;
    }

    /**
     * Q. 948. Bag of Tokens
     * <p>
     * You have an initial power of power, an initial score of 0, and a bag of tokens
     * where tokens[i] is the value of the ith token (0-indexed).
     * Your goal is to maximize your total score by potentially playing each token in one of two ways:
     * If your current power is >= tokens[i], you may play ith token face up, losing tokens[i] power & gaining 1 score.
     * If your current score is >= 1, you may play ith token face down, gaining tokens[i] power & losing 1 score.
     * <p>
     * Each token may be played at most once and in any order. You do not have to play all the tokens.
     * Return the largest possible score you can achieve after playing any number of tokens.
     * <p>
     * tags:: array, sorting, twoPointer, greedy
     */
    public int bagOfTokensScore(int[] tokens, int power) {
        int maxScore = 0, score = 0;
        int up = 0, down = tokens.length - 1;

        Arrays.sort(tokens);
        while (down >= up) {
            if (tokens[up] <= power) {
                power -= tokens[up++];
                maxScore = Math.max(maxScore, ++score);
            } else if (score >= 1) {
                score--;
                power += tokens[down--];
            } else {
                break;
            }

        }

        return maxScore;
    }

    /**
     * Q. 15 3Sum
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * <p>
     * Notice that the solution set must not contain duplicate triplets.
     * <p>
     * tags:: array, twoPointer, threeSum
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> answer = new ArrayList<>();
        if (n < 3)
            return answer;

        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int start = i + 1, end = n - 1, target = -nums[i];
                while (start < end) {
                    if (nums[start] + nums[end] == target) {
                        answer.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[++start] && nums[end] == nums[--end]) {
                        }
                    } else if (nums[start] + nums[end] > target)
                        end--;
                    else
                        start++;
                }
            }
        }

        return answer;
    }

    /**
     * Q. 16. 3Sum Closest
     * <p>
     * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is
     * closest to target.
     * <p>
     * Return the sum of the three integers. You may assume that each input would have exactly one sol.
     * <p>
     * tags:: array, twoPointer, threeSum
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int answer = nums[0] + nums[1] + nums[nums.length - 1], n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int start = i + 1, end = n - 1;
                while (start < end) {
                    int currSum = nums[i] + nums[start] + nums[end];

                    if (currSum > target)
                        end--;
                    else if (currSum < target)
                        start++;
                    else
                        return target;

                    if (Math.abs(target - currSum) < Math.abs(target - answer))
                        answer = currSum;
                }
            }
        }

        return answer;
    }

    /**
     * Q. 57 Insert Interval
     * <p>
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * <p>
     * tags:: array
     */
    public int[][] insertMergeInterval(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();

        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) {
                ans.add(newInterval);
                newInterval = interval;
            } else if (interval[1] >= newInterval[0]) {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                ans.add(interval);
            }
        }

        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][2]);
    }

    /**
     * Q. 56. Merge Intervals
     * <p>
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
     * and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * <p>
     * tags:: array
     */
    public int[][] mergeInterval(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> ans = new ArrayList<>();
        int[] newInterval = intervals[0];

        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) {
                ans.add(newInterval);
                newInterval = interval;
            } else if (newInterval[0] <= interval[1]) {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                ans.add(interval);
            }
        }

        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][2]);
    }

    /**
     * Q. 315. Count of Smaller Numbers After Self
     * <p>
     * You are given an integer array nums and you have to return a new counts array.
     * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
     * <p>
     * tags:: segment-tree
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sum = new ArrayList<>();
        int max = 20_001, offset = 10_000;
        int[] arr = new int[2 * max];

        for (int j = nums.length - 1; j >= 0; j--) {
            int index = nums[j] + max + offset;
            sum.add(getSegmentTreeSum(arr, max, index));

            arr[index] += 1;
            for (index /= 2; index > 1; index /= 2) {
                arr[index] = arr[index * 2] + arr[index * 2 + 1];
            }
        }

        Collections.reverse(sum);
        return sum;
    }

    private int getSegmentTreeSum(int[] arr, int l, int r) {
        int sum = 0;
        while (l < r) {
            if (l % 2 == 1)
                sum += arr[l++];
            if (r % 2 == 1)
                sum += arr[--r];

            l >>= 1;
            r >>= 1;
        }

        return sum;
    }
}
