package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingProblemsTest {

    static DynamicProgrammingProblems dpProblems;

    @BeforeAll
    static void setUp() {
        dpProblems = new DynamicProgrammingProblems();
    }

    @Test
    void coinChange() {
        assertEquals(3, dpProblems.coinChange(new int[]{1, 2, 5}, 11));
        assertEquals(-1, dpProblems.coinChange(new int[]{2}, 3));
        assertEquals(0, dpProblems.coinChange(new int[]{1}, 0));
        assertEquals(1, dpProblems.coinChange(new int[]{1}, 1));
        assertEquals(2, dpProblems.coinChange(new int[]{1}, 2));
        assertEquals(8, dpProblems.coinChange(new int[]{474, 83, 404, 3}, 264));
    }

    @Test
    void testNumFactoredBinaryTrees() {
        assertEquals(7, dpProblems.numFactoredBinaryTrees(new int[]{2, 4, 5, 10}));
        assertEquals(3, dpProblems.numFactoredBinaryTrees(new int[]{2, 4}));
    }
}