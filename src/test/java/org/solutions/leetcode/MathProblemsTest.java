package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MathProblemsTest {

    static MathProblems mathProblems;

    @BeforeEach
    void setUp() {
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

        scenarios.forEach((input, expected) -> assertEquals(expected, mathProblems.distributeCandies(input)));
    }

    @Test
    void testFindErrorNums() {
        Map<int[], int[]> scenarios = new HashMap<>();
        scenarios.put(new int[]{1, 2, 2, 4}, new int[]{2, 3});
        scenarios.put(new int[]{1, 1}, new int[]{1, 2});

        scenarios.forEach((inptut, expected) -> assertArrayEquals(expected, mathProblems.findErrorNums(inptut)));
    }

    @Test
    void testMissingNumber() {
        Map<int[], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[]{3, 0, 1}, 2);
        scenarios.put(new int[]{0, 1}, 2);
        scenarios.put(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}, 8);
        scenarios.put(new int[]{0}, 1);

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
}
