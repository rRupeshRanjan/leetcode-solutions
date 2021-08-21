package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrieProblemsTest {

    static TrieProblems trieProblems;

    @BeforeAll
    static void setUp() {
        trieProblems = new TrieProblems();
    }

    @Test
    void minimumLengthEncoding() {
        Map<String[], Integer> scenarios = new HashMap<>();
        scenarios.put(new String[]{"time", "me", "bell"}, 10);
        scenarios.put(new String[]{"t"}, 2);
        scenarios.put(new String[]{}, 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, trieProblems.minimumLengthEncoding(input)));
    }
}