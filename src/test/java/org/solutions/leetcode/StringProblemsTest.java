package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringProblemsTest {

    static StringProblems stringProblems;

    @BeforeAll
    static void setUp() {
        stringProblems = new StringProblems();
    }

    @Test
    void testShortestToChar() {
        assertArrayEquals(new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0}, stringProblems.shortestToChar("loveleetcode", 'e'));
        assertArrayEquals(new int[]{3, 2, 1, 0}, stringProblems.shortestToChar("aaab", 'b'));
        assertArrayEquals(new int[]{0, 0, 0, 1}, stringProblems.shortestToChar("aaab", 'a'));
        assertArrayEquals(new int[]{0}, stringProblems.shortestToChar("a", 'a'));
    }

    @Test
    void testIsAnagram() {
        assertTrue(stringProblems.isAnagram("anagram", "nagamar"));
        assertFalse(stringProblems.isAnagram("ram", "hello"));
    }

    @Test
    void testMinimumLengthEncoding() {
        assertEquals(10, stringProblems.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        assertEquals(2, stringProblems.minimumLengthEncoding(new String[]{"t"}));
    }

    @Test
    void testHasAllCodes() {
        assertTrue(stringProblems.hasAllCodes("00110110", 2));
        assertTrue(stringProblems.hasAllCodes("00110", 2));
        assertTrue(stringProblems.hasAllCodes("00110", 1));
        assertFalse(stringProblems.hasAllCodes("0110", 2));
        assertFalse(stringProblems.hasAllCodes("0000000001011100", 4));
    }
}