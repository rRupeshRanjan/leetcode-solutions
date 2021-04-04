package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

    static ArrayProblems arrayProblems;

    @BeforeAll
    static void setup() {
        arrayProblems = new ArrayProblems();
    }

    @Test
    void findLHS() {
        Map<int[], Integer> tests = new HashMap<>();
        tests.put(new int[]{-3, -1, -1, -1, -3, -2}, 4);
        tests.put(new int[]{1, 2, 2, 3, 4, 5, 1, 1, 1, 1}, 7);
        tests.put(new int[]{2, 2, 2, 2, 2, 2, 2, 3, 1, 0, 0, 0, 3, 1, -1, 0, 1, 1, 0, 0, 1, 1, 2, 2, 2, 0, 1, 2, 2, 3, 2}, 20);
        tests.put(new int[]{1, 3, 2, 2, 5, 2, 3, 7}, 5);
        tests.put(new int[]{1, 2, 3, 4}, 2);
        tests.put(new int[]{1, 1, 1, 1}, 0);

        String[] ways = new String[]{"brute-force", "sorting", "hashmap"};

        tests.forEach((input, length) ->
                assertEquals(length, arrayProblems.findLHS(input, ways[new Random().nextInt(ways.length)]))
        );
    }

    @Test
    void testCanFormArray() {
        assertTrue(arrayProblems.canFormArray(new int[]{85}, new int[][]{{85}}));
        assertTrue(arrayProblems.canFormArray(new int[]{15, 88}, new int[][]{{88}, {15}}));
        assertFalse(arrayProblems.canFormArray(new int[]{49, 18, 16}, new int[][]{{16, 18, 49}}));
        assertTrue(arrayProblems.canFormArray(new int[]{91, 4, 64, 78}, new int[][]{{78}, {4, 64}, {91}}));
        assertFalse(arrayProblems.canFormArray(new int[]{1, 3, 5, 7}, new int[][]{{2, 4, 6, 8}}));
    }

    @Test
    void testMaxWaterContainerArea() {
        assertEquals(49, arrayProblems.maxWaterContainerArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, arrayProblems.maxWaterContainerArea(new int[]{1, 1}));
        assertEquals(16, arrayProblems.maxWaterContainerArea(new int[]{4, 3, 2, 1, 4}));
        assertEquals(2, arrayProblems.maxWaterContainerArea(new int[]{1, 2, 1}));
    }

    @Test
    void testNumberOfArithmeticSlices() {
        assertEquals(3, arrayProblems.numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        assertEquals(2, arrayProblems.numberOfArithmeticSlices(new int[]{1, 2, 3, 8, 9, 10}));
    }

    @Test
    void testMaxProfit2() {
        assertEquals(7, arrayProblems.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(4, arrayProblems.maxProfit2(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, arrayProblems.maxProfit2(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testMaxProfit5() {
        assertEquals(8, arrayProblems.maxProfit5(2, new int[]{1, 3, 2, 8, 4, 9}));
        assertEquals(6, arrayProblems.maxProfit5(3, new int[]{1, 3, 7, 5, 10, 3}));
    }

    @Test
    void testThreeSumMulti() {
        assertEquals(20, arrayProblems.threeSumMulti(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8));
        assertEquals(12, arrayProblems.threeSumMulti(new int[]{1, 1, 2, 2, 2, 2}, 5));
        assertEquals(3, arrayProblems.threeSumMulti(new int[]{1, 0, 1, 0, 2, 1, 2}, 1));
    }

    @Test
    void testAdvantageCount() {
        assertArrayEquals(new int[]{2, 11, 7, 15}, arrayProblems.advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}));
        assertArrayEquals(new int[]{24, 32, 8, 12}, arrayProblems.advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}));
    }
}
