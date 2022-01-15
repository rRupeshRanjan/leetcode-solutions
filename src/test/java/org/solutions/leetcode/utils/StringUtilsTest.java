package org.solutions.leetcode.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.exceptions.BadInputException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringUtilsTest {

    static StringUtils stringUtils;

    @BeforeAll
    static void startup() {
        stringUtils = new StringUtils();
    }

    @Test
    void shouldGetExpectedLowercaseLetterCount() throws BadInputException {
        int[] expected = new int[26];
        expected[0] = 1;
        assertArrayEquals(expected, stringUtils.getLowercaseCharCount("a"));

        expected[0] = 1;
        expected[1] = 2;
        assertArrayEquals(expected, stringUtils.getLowercaseCharCount("bab"));

        expected[0] = 4;
        expected[1] = 0;
        expected[25] = 1;
        assertArrayEquals(expected, stringUtils.getLowercaseCharCount("aazaa"));
    }

    @Test
    void shouldThrowExceptionForUppercaseCharacters() {
        assertThrows(BadInputException.class, () -> stringUtils.getLowercaseCharCount("ABC"));
    }

    @Test
    void shouldGetExpectedUppercaseLetterCount() throws BadInputException {
        int[] expected = new int[26];
        expected[0] = 1;
        assertArrayEquals(expected, stringUtils.getUppercaseCharCount("A"));

        expected[0] = 1;
        expected[1] = 2;
        assertArrayEquals(expected, stringUtils.getUppercaseCharCount("BAB"));

        expected[0] = 4;
        expected[1] = 0;
        expected[25] = 1;
        assertArrayEquals(expected, stringUtils.getUppercaseCharCount("AAZAA"));
    }

    @Test
    void shouldThrowExceptionForLowercaseCharacters() {
        assertThrows(BadInputException.class, () -> stringUtils.getUppercaseCharCount("abc"));
    }
}
