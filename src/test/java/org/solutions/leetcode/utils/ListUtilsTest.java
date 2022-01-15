package org.solutions.leetcode.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListUtilsTest {
    static ListUtils listUtils;

    @BeforeAll
    static void setup() {
        listUtils = new ListUtils();
    }

    @Test
    void shouldReturnFalseIfOneListNull() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        assertFalse(listUtils.areNestedListsEqual(null, a));
    }

    @Test
    void shouldReturnFalseIfNonEqualSize() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<List<Integer>> b = Collections.singletonList(Arrays.asList(1, 2));
        assertFalse(listUtils.areNestedListsEqual(a, b));
    }

    @Test
    void shouldReturnFalseIfNestedListOfNonEqualSize() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        assertFalse(listUtils.areNestedListsEqual(a, b));
    }

    @Test
    void shouldReturnTrueIfListsEqual() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
        assertTrue(listUtils.areNestedListsEqual(a, b));
    }
}