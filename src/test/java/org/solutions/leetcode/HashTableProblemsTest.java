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
    private static HashTableProblems hashTableProblems;

    @BeforeAll
    static void setUp() {
        hashTableProblems = new HashTableProblems();
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
        scenarios.put(Arrays.asList(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1)), 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, hashTableProblems.leastBricks(input)));
    }

    @Test
    void testSubarraySum() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 1, 1}, 2), 2);
        scenarios.put(Pair.of(new int[]{1, 2, 3}, 3), 2);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, hashTableProblems.subarraySum(input.getLeft(), input.getRight())));
    }

    @Test
    void testSubarraysDivByK() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{4, 5, 0, -2, -3, 1}, 5), 7);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, hashTableProblems.subarraysDivByK(input.getLeft(), input.getRight())));
    }

    @Test
    void testTopKFrequent() {
        Map<Pair<int[], Integer>, int[]> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 2), new int[]{1, 3});
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 3), new int[]{1, 3, 2});
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 2, 3, 3, 1, 1}, 1), new int[]{1});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected,
                hashTableProblems.topKFrequent(input.getLeft(), input.getRight())));
    }
}