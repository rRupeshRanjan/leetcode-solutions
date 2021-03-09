package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathProblemsTest {

    static MathProblems mathProblems;

    @BeforeEach
    void setUp() {
        mathProblems = new MathProblems();
    }

    @Test
    void testNumberOfSteps() {
        assertEquals(6, mathProblems.numberOfSteps(14));
        assertEquals(4, mathProblems.numberOfSteps(8));
        assertEquals(12, mathProblems.numberOfSteps(123));
    }

    @Test
    void testDistributeNumberOfCandies() {
        assertEquals(3, mathProblems.distributeCandies(new int[]{1,1,2,2,3,3}));
        assertEquals(2, mathProblems.distributeCandies(new int[]{1,1,2,3}));
        assertEquals(1, mathProblems.distributeCandies(new int[]{6,6,6,6}));
    }

    @Test
    void testFindErrorNums() {
        assertArrayEquals(new int[]{2,3}, mathProblems.findErrorNums(new int[]{1,2,2,4}));
        assertArrayEquals(new int[]{1,2}, mathProblems.findErrorNums(new int[]{1,1}));
    }

    @Test
    void testMissingNumber() {
        assertEquals(2, mathProblems.missingNumber(new int[]{3,0,1}));
        assertEquals(2, mathProblems.missingNumber(new int[]{0,1}));
        assertEquals(8, mathProblems.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
        assertEquals(1, mathProblems.missingNumber(new int[]{0}));
    }
}