package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingProblemsTest {

    static DynamicProgrammingProblems dpProblems;

    @BeforeAll
    static void setUp() {
        dpProblems = new DynamicProgrammingProblems();
    }

    @Test
    void coinChange() {
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
}
