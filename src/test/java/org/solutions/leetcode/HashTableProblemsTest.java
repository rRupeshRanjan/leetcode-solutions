package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTableProblemsTest {
    private static HashTableProblems htProblems;

    @BeforeAll
    static void setUp() {
        htProblems = new HashTableProblems();
    }

    @Test
    void testLeastBricks() {
        Map<List<List<Integer>>, Integer> scenarios = new HashMap<>();
        scenarios.put(Arrays.asList(
                        Arrays.asList(1, 2, 2, 1),
                        Arrays.asList(3, 1, 2),
                        Arrays.asList(1, 3, 2),
                        Arrays.asList(2, 4),
                        Arrays.asList(3, 1, 2),
                        Arrays.asList(1, 3, 1, 4)),
                2);
        scenarios.put(Arrays.asList(List.of(1), List.of(1), List.of(1)), 3);
        scenarios.put(List.of(), 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, htProblems.leastBricks(input)));
    }

    @Test
    void testSubarraySum() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 1, 1}, 2), 2);
        scenarios.put(Pair.of(new int[]{1, 2, 3}, 3), 2);
        scenarios.put(Pair.of(new int[]{}, 3), 0);
        scenarios.put(Pair.of(null, 3), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, htProblems.subarraySum(input.getLeft(), input.getRight())));
    }

    @Test
    void testSubarraysDivByK() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{4, 5, 0, -2, -3, 1}, 5), 7);
        scenarios.put(Pair.of(new int[]{4, 5, 0, -2, -3, 1}, 0), 0);
        scenarios.put(Pair.of(new int[]{}, 5), 0);
        scenarios.put(Pair.of(null, 5), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, htProblems.subarraysDivByK(input.getLeft(), input.getRight())));
    }

    @Test
    void testTopKFrequent() {
        Map<Pair<int[], Integer>, int[]> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 2), new int[]{1, 3});
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 3), new int[]{1, 3, 2});
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 1), new int[]{1});
        scenarios.put(Pair.of(new int[]{}, 0), new int[]{});
        scenarios.put(Pair.of(null, 0), null);

        scenarios.forEach((input, expected) -> assertArrayEquals(expected,
                htProblems.topKFrequent(input.getLeft(), input.getRight())));
    }

    @Test
    void testArrayRankTransform() {
        Map<int[], int[]> scenarios = new HashMap<>();
        scenarios.put(new int[]{40, 10, 20, 30}, new int[]{4, 1, 2, 3});
        scenarios.put(new int[]{100, 100, 100}, new int[]{1, 1, 1});
        scenarios.put(new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12}, new int[]{5, 3, 4, 2, 8, 6, 7, 1, 3});
        scenarios.put(new int[]{}, new int[]{});
        scenarios.put(null, null);

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, htProblems.arrayRankTransform(input)));
    }

    @Test
    void testCanReorderDoubled() {
        Map<int[], Boolean> scenarios = new HashMap<>();
        scenarios.put(new int[]{}, true);
        scenarios.put(null, true);
        scenarios.put(new int[]{3, 1, 3, 6}, false);
        scenarios.put(new int[]{2, 1, 3, 6}, true);
        scenarios.put(new int[]{2, 1, 2, 6}, false);
        scenarios.put(new int[]{4, -2, 2, -4}, true);
        scenarios.put(new int[]{1, 2, 4, 16, 8, 4}, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, htProblems.canReorderDoubled(input)));
    }

    @Test
    void testIsValidSudoku() {
        Map<char[][], Boolean> scenarios = new HashMap<>();
        scenarios.put(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}, true);
        scenarios.put(new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}, false);
        scenarios.put(new char[][]{
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}}, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, htProblems.isValidSudoku(input)));
    }

    @Test
    void testCheckSubarraySum() {
        Map<Pair<int[], Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{23, 2, 4, 6, 7}, 6), true);
        scenarios.put(Pair.of(new int[]{23, 2, 4, 6, 6}, 7), true);
        scenarios.put(Pair.of(new int[]{23, 2, 6, 6, 7}, 6), true);
        scenarios.put(Pair.of(new int[]{23, 2, 6, 4, 7}, 13), false);
        scenarios.put(Pair.of(new int[]{23, 2, 4, 6, 7}, 13), true);
        scenarios.put(Pair.of(new int[]{0}, 1), false);
        scenarios.put(Pair.of(new int[]{5, 0, 0, 0}, 3), true);
        scenarios.put(Pair.of(new int[]{0, 0}, 1), true);
        scenarios.put(Pair.of(new int[]{0, 1, 0, 3, 0, 4, 0, 4, 0}, 5), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, htProblems.checkSubArraySum(input.getLeft(), input.getRight())));
    }

}