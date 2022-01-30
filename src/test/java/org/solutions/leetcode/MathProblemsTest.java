package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MathProblemsTest {

    static MathProblems mathProblems;

    @BeforeAll
    static void setUp() {
        mathProblems = new MathProblems();
    }

    @Test
    void testNumberOfSteps() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(14, 6);
        scenarios.put(0, 0);
        scenarios.put(8, 4);
        scenarios.put(123, 12);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.numberOfSteps(input)));
    }

    @Test
    void testDistributeNumberOfCandies() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 1, 2, 2, 3, 3}, 3);
        scenarios.put(new int[]{1, 1, 2, 3}, 2);
        scenarios.put(new int[]{6, 6, 6, 6}, 1);
        scenarios.put(new int[]{}, 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.distributeCandies(input)));
    }

    @Test
    void testFindErrorNums() {
        Map<int[], int[]> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 2, 2, 4}, new int[]{2, 3});
        scenarios.put(new int[]{1, 1}, new int[]{1, 2});
        scenarios.put(new int[]{}, new int[]{});
        scenarios.put(new int[]{1}, new int[]{});
        scenarios.put(null, new int[]{});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, mathProblems.findErrorNums(input)));
    }

    @Test
    void testMissingNumber() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{3, 0, 1}, 2);
        scenarios.put(new int[]{0, 1}, 2);
        scenarios.put(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}, 8);
        scenarios.put(new int[]{0}, 1);
        scenarios.put(new int[]{}, -1);
        scenarios.put(null, -1);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.missingNumber(input)));
    }

    @Test
    void testReorderedPowerOf2() {
        Map<Integer, Boolean> scenarios = new HashMap<>();
        scenarios.put(1, true);
        scenarios.put(46, true);
        scenarios.put(16, true);
        scenarios.put(10, false);
        scenarios.put(24, false);
        scenarios.put(0, false);
        scenarios.put(1000000000, false);
        scenarios.put(99999999, false);

        scenarios.forEach((input, expectation) -> assertEquals(expectation, mathProblems.reorderedPowerOf2(input)));
    }

    @Test
    void testMinOperations() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(3, 2);
        scenarios.put(6, 9);
        scenarios.put(100, 2500);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.minOperations(input)));
    }

    @Test
    void testIsPowerOfThree() {
        Map<Integer, Boolean> scenarios = new HashMap<>();
        scenarios.put(243, true);
        scenarios.put(0, false);
        scenarios.put(3, true);
        scenarios.put(123987, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.isPowerOfThree(input)));
    }

    @Test
    void testPowerfulIntegers() {
        Map<Triple<Integer, Integer, Integer>, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(Triple.of(2, 3, 10), Arrays.asList(2, 3, 4, 5, 7, 9, 10));
        scenarios.put(Triple.of(3, 5, 15), Arrays.asList(2, 4, 6, 8, 10, 14));
        scenarios.put(Triple.of(1, 1, 15), Collections.singletonList(2));

        scenarios.forEach((input, expected) -> assertTrue(expected.containsAll(
                mathProblems.powerfulIntegers(input.getLeft(), input.getMiddle(), input.getRight()))));
    }

    @Test
    void testBulbSwitcher() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(11, 3);
        scenarios.put(16, 4);
        scenarios.put(10000000, 3162);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.bulbSwitcher(input)));
    }

    @Test
    void testCountPrimes() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(1, 0);
        scenarios.put(2, 0);
        scenarios.put(3, 1);
        scenarios.put(10, 4);
        scenarios.put(123456, 11601);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.countPrimes(input)));
    }

    @Test
    void testNumberOf1Bits() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(00000000000000000000000000001011, 3);
        scenarios.put(00000000000000000000000010000000, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.numberOf1Bits(input))
        );
    }

    @Test
    void testCountArrangement() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(2, 2);
        scenarios.put(3, 5);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.countArrangement(input)));
    }

    @Test
    void testKClosest() {
        Map<Pair<int[][], Integer>, int[][]> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[][]{{1, 3}, {-2, 2}}, 1), new int[][]{{-2, 2}});
        scenarios.put(Pair.of(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2), new int[][]{{-2, 4}, {3, 3}});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, mathProblems.kClosest(input.getLeft(), input.getRight())));
    }

    @Test
    void testMaximumSwap() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(2736, 7236);
        scenarios.put(7236, 7632);
        scenarios.put(9973, 9973);
        scenarios.put(7, 7);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.maximumSwap(input)));
    }

    @Test
    void testIsPalindrome() {
        Map<Integer, Boolean> scenarios = new HashMap<>();
        scenarios.put(121, true);
        scenarios.put(10, false);
        scenarios.put(0, true);
        scenarios.put(-121, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.isPalindrome(input)));
    }

    @Test
    void testClimbStairs() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(1, 1);
        scenarios.put(2, 2);
        scenarios.put(5, 8);
        scenarios.put(10, 89);
        scenarios.put(20, 10946);
        scenarios.put(45, 1836311903);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.climbStairs(input)));
    }

    @Test
    void testMyPow() {
        Map<Pair<Double, Integer>, Double> scenarios = new HashMap<>();
        scenarios.put(Pair.of(2d, 10), 1024d);
        scenarios.put(Pair.of(2.1, 3), 9.261000000000001);
        scenarios.put(Pair.of(2d, -2), 0.25);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                mathProblems.myPow(input.getLeft(), input.getRight())));
    }

    @Test
    void testBasicCalculatorII() {
        Map<String, Integer> scenarios = new HashMap<>();
        scenarios.put("3+2*2", 7);
        scenarios.put(" 3/2 ", 1);
        scenarios.put("3 / 4 ", 0);
        scenarios.put("3 + 5 / 2", 5);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.basicCalculatorII(input)));
    }

    @Test
    void testSingleNumberII() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{2, 2, 3, 2}, 3);
        scenarios.put(new int[]{0, 1, 0, 1, 0, 1, 99}, 99);

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.singleNumberII(input)));
    }

    @Test
    void testMinMoves() {
        Map<Pair<Integer, Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(5, 0), 4);
        scenarios.put(Pair.of(19, 2), 7);
        scenarios.put(Pair.of(10, 4), 4);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, mathProblems.minMoves(input.getLeft(), input.getRight())));
    }
}
