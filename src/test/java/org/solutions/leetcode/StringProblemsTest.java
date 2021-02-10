package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringProblemsTest {

    static StringProblems stringProblems;

    @BeforeAll
    static void setUp() {
        stringProblems = new StringProblems();
    }

    @Test
    void shortestToChar() {
        assertArrayEquals(new int[]{3,2,1,0,1,0,0,1,2,2,1,0}, stringProblems.shortestToChar("loveleetcode", 'e'));
        assertArrayEquals(new int[]{3,2,1,0}, stringProblems.shortestToChar("aaab", 'b'));
        assertArrayEquals(new int[]{0,0,0,1}, stringProblems.shortestToChar("aaab", 'a'));
        assertArrayEquals(new int[]{0}, stringProblems.shortestToChar("a", 'a'));
    }
}