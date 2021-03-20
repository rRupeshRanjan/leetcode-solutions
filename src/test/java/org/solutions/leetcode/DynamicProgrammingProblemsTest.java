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

    @Test
    void testMaxProfit1() {
        assertEquals(5, dpProblems.maxProfit1(new int[]{7, 1, 5, 6, 3, 4}));
        assertEquals(0, dpProblems.maxProfit1(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void testMaxProfit3() {
        assertEquals(6, dpProblems.maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        assertEquals(4, dpProblems.maxProfit3(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, dpProblems.maxProfit3(new int[]{7, 6, 4, 3, 1}));
        assertEquals(0, dpProblems.maxProfit3(new int[]{1}));
    }

    @Test
    void testMaxProfit4() {
        assertEquals(2, dpProblems.maxProfit4(2, new int[]{2, 4, 1}));
        assertEquals(0, dpProblems.maxProfit4(0, new int[]{2, 4, 1}));
        assertEquals(0, dpProblems.maxProfit4(10, new int[]{2}));
        assertEquals(7, dpProblems.maxProfit4(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    void testMaxProfit6() {
        assertEquals(3, dpProblems.maxProfit6(new int[]{1, 2, 3, 0, 2}));
        assertEquals(0, dpProblems.maxProfit6(new int[]{1}));
    }

    @Test
    void testWiggleMaxLength() {
        assertEquals(6, dpProblems.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        assertEquals(7, dpProblems.wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        assertEquals(2, dpProblems.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        assertEquals(1, dpProblems.wiggleMaxLength(new int[]{1, 1, 1, 1, 1}));
        assertEquals(4, dpProblems.wiggleMaxLength(new int[]{1, 1, 0, 2, 1}));
    }
}
