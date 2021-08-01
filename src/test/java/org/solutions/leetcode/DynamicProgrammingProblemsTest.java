package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingProblemsTest {

    static private DynamicProgrammingProblems dpProblems;

    @BeforeAll
    static void setUp() {
        dpProblems = new DynamicProgrammingProblems();
    }

    @Test
    void testCoinChange() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 2, 5}, 11), 3);
        scenarios.put(Pair.of(new int[]{2}, 3), -1);
        scenarios.put(Pair.of(new int[]{1}, 0), 0);
        scenarios.put(Pair.of(new int[]{1}, 1), 1);
        scenarios.put(Pair.of(new int[]{1}, 2), 2);
        scenarios.put(Pair.of(new int[]{474, 83, 404, 3}, 264), 8);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.coinChange(input.getLeft(), input.getRight())));
    }

    @Test
    void testNumFactoredBinaryTrees() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{2, 4, 5, 10}, 7);
        scenarios.put(new int[]{2, 4}, 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.numFactoredBinaryTrees(input)));
    }

    @Test
    void testMaxProfit1() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{7, 1, 5, 6, 3, 4}, 5);
        scenarios.put(new int[]{7, 6, 4, 3, 1}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxProfit1(input)));
    }

    @Test
    void testMaxProfit3() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{3, 3, 5, 0, 0, 3, 1, 4}, 6);
        scenarios.put(new int[]{1, 2, 3, 4, 5}, 4);
        scenarios.put(new int[]{7, 6, 4, 3, 1}, 0);
        scenarios.put(new int[]{1}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxProfit3(input)));
    }

    @Test
    void testMaxProfit4() {
        Map<Pair<Integer, int[]>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(2, new int[]{2, 4, 1}), 2);
        scenarios.put(Pair.of(0, new int[]{2, 4, 1}), 0);
        scenarios.put(Pair.of(10, new int[]{2}), 0);
        scenarios.put(Pair.of(10, new int[]{}), 0);
        scenarios.put(Pair.of(2, new int[]{3, 2, 6, 5, 0, 3}), 7);
        scenarios.put(Pair.of(2, null), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.maxProfit4(input.getLeft(), input.getRight())));
    }

    @Test
    void testMaxProfit6() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 2, 3, 0, 2}, 3);
        scenarios.put(new int[]{1}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxProfit6(input)));
    }

    @Test
    void testWiggleMaxLength() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 7, 4, 9, 2, 5}, 6);
        scenarios.put(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}, 7);
        scenarios.put(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 2);
        scenarios.put(new int[]{1, 1, 1, 1, 1}, 1);
        scenarios.put(new int[]{1, 1, 0, 2, 1}, 4);
        scenarios.put(new int[]{1}, 1);
        scenarios.put(new int[]{}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.wiggleMaxLength(input)));
    }

    @Test
    void testMaxEnvelopes() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}, 3);
        scenarios.put(new int[][]{{1, 1}, {1, 1}, {1, 1}}, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxEnvelopes(input)));
    }

    @Test
    void testLengthOfLIS() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4);
        scenarios.put(new int[]{0, 1, 0, 3, 2, 3}, 4);
        scenarios.put(new int[]{7, 7, 7, 7, 7}, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.lengthOfLIS(input)));
    }

    @Test
    void testLongestIncreasingPath() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{7, 8, 9}, {9, 7, 6}, {7, 2, 3}}, 6);
        scenarios.put(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}, 4);
        scenarios.put(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}, 4);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.longestIncreasingPath(input)));
    }

    @Test
    void testCombinationSum4() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 2, 3}, 4), 7);
        scenarios.put(Pair.of(new int[]{9}, 3), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.combinationSum4(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinimumTotal() {
        Map<List<List<Integer>>, Integer> scenarios = new HashMap<>();
        scenarios.put(Arrays.asList(
                Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3)
        ), 11);
        scenarios.put(Arrays.asList(Arrays.asList(-10)), -10);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.minimumTotal(input)));
    }

    @Test
    void testNumSubmatrixSumTarget() {
        Map<Pair<int[][], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0), 4);
        scenarios.put(Pair.of(new int[][]{{1, -1}, {-1, 1}}, 0), 5);
        scenarios.put(Pair.of(new int[][]{{904}}, 0), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.numSubmatrixSumTarget(input.getLeft(), input.getRight())));
    }

    @Test
    void testUniquePaths() {
        Map<Pair<Integer, Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(7, 3), 28);
        scenarios.put(Pair.of(3, 7), 28);
        scenarios.put(Pair.of(3, 2), 3);
        scenarios.put(Pair.of(3, 3), 6);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.uniquePaths(input.getLeft(), input.getRight())));
    }

    @Test
    void testUniquePathsWithObstacles() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, 2);
        scenarios.put(new int[][]{{0, 1}, {0, 0}}, 1);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.uniquePathsWithObstacles(input)));
    }

    @Test
    void testMinDistance() {
        Map<Pair<String, String>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of("sea", "eat"), 2);
        scenarios.put(Pair.of("leetcode", "etco"), 4);
        scenarios.put(Pair.of("aaaaa", "bbbaa"), 6);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, dpProblems.minDistance(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinimumDeletions() {
        Map<String, Integer> scenarios = new HashMap<>();
        scenarios.put("aaababbbab", 2);
        scenarios.put("bbaaaaabb", 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.minimumDeletions(input)));
    }

    @Test
    void testMaxSubArray() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6);
        scenarios.put(new int[]{5, 4, -1, 7, 8}, 23);
        scenarios.put(new int[]{1}, 1);
        scenarios.put(new int[]{-1}, -1);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxSubArray(input)));
    }

    @Test
    void testMaxAbsoluteSum() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, -3, 2, 3, -4}, 5);
        scenarios.put(new int[]{2, -5, 1, -4, 3, -2}, 8);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxAbsoluteSum(input)));
    }

    @Test
    void testMaxSumMinProduct() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 2, 3, 2}, 14);
        scenarios.put(new int[]{2, 3, 3, 1, 2}, 18);
        scenarios.put(new int[]{3, 1, 5, 6, 4, 2}, 60);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.maxSumMinProduct(input)));
    }

    @Test
    void testMinCostClimbingStairs() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{10, 15, 20}, 15);
        scenarios.put(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6);
        scenarios.put(new int[]{100, 1}, 1);
        scenarios.put(new int[]{1, 1}, 1);
        scenarios.put(new int[]{1}, 0);
        scenarios.put(new int[]{}, 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.minCostClimbingStairs(input)));
    }

    @Test
    void testLongestLine() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}}, 3);
        scenarios.put(new int[][]{{1, 1, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 1}}, 4);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.longestLine(input)));
    }

    @Test
    void testOddEvenJump() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{10, 13, 12, 14, 15}, 2);
        scenarios.put(new int[]{2, 3, 1, 1, 4}, 3);
        scenarios.put(new int[]{5, 1, 3, 4, 2}, 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.oddEvenJump(input)));
    }

    @Test
    void testHouseRobber() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 2, 3, 1}, 4);
        scenarios.put(new int[]{2, 7, 9, 3, 1}, 12);
        scenarios.put(new int[]{1}, 1);
        scenarios.put(new int[]{1, 2}, 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, dpProblems.houseRobber(input)));
    }
}
