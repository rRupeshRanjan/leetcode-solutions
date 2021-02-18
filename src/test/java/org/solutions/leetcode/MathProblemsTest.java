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
}