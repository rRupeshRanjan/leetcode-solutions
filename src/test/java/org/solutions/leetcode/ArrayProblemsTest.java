package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        tests.put(new int[]{-3,-1,-1,-1,-3,-2}, 4);
        tests.put(new int[]{1,2,2,3,4,5,1,1,1,1}, 7);
        tests.put(new int[]{2,2,2,2,2,2,2,3,1,0,0,0,3,1,-1,0,1,1,0,0,1,1,2,2,2,0,1,2,2,3,2}, 20);
        tests.put(new int[]{1,3,2,2,5,2,3,7}, 5);
        tests.put(new int[]{1,2,3,4}, 2);
        tests.put(new int[]{1,1,1,1}, 0);

        String[] ways = new String[]{"brute-force", "sorting", "hashmap"};

        tests.forEach((input, length) ->
                assertEquals(length, arrayProblems.findLHS(input, ways[new Random().nextInt(ways.length)]))
        );
    }

    @Test
    void testCanFormArray() {
        assertTrue(arrayProblems.canFormArray(new int[]{85}, new int[][]{{85}}));
        assertTrue(arrayProblems.canFormArray(new int[]{15,88}, new int[][]{{88},{15}}));
        assertFalse(arrayProblems.canFormArray(new int[]{49,18,16}, new int[][]{{16,18,49}}));
        assertTrue(arrayProblems.canFormArray(new int[]{91,4,64,78}, new int[][]{{78},{4,64},{91}}));
        assertFalse(arrayProblems.canFormArray(new int[]{1,3,5,7}, new int[][]{{2,4,6,8}}));
    }
}