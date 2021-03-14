package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrieProblemsTest {

    static TrieProblems trieProblems;

    @BeforeAll
    static void setUp() {
        trieProblems = new TrieProblems();
    }

    @Test
    void minimumLengthEncoding() {
        assertEquals(10, trieProblems.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        assertEquals(2, trieProblems.minimumLengthEncoding(new String[]{"t"}));
    }
}