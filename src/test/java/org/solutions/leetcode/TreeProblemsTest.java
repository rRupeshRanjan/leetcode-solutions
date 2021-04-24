package org.solutions.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.NaryTreeNode;
import org.solutions.leetcode.utils.ListUtils;
import org.solutions.leetcode.utils.TreeUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeProblemsTest {
    static TreeProblems treeProblems;
    static ListUtils listUtils;
    static TreeUtils treeUtils;

    @BeforeAll
    static void setup() {
        treeProblems = new TreeProblems();
        listUtils = new ListUtils();
        treeUtils = new TreeUtils();
    }

    @Test
    void testNaryLevelOrder() {
        Map<NaryTreeNode, List<List<Integer>>> scenarios = new HashMap<>();
        scenarios.put(
                treeUtils.createTreeFromList(Arrays.asList(1, null, 2, 3, 4, null, 5, 6)),
                Arrays.asList(Collections.singletonList(1), Arrays.asList(2, 3, 4), Arrays.asList(5, 6)));
        scenarios.forEach((input, expected) ->
                assertTrue(listUtils.areNestedListsEqual(treeProblems.naryLevelOrder(input), expected)));
    }

    @Test
    void testNaryPreorder() {
        Map<NaryTreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(
                treeUtils.createTreeFromList(Arrays.asList(1, null, 3, 2, 4, null, 5, 6)),
                Arrays.asList(1, 3, 5, 6, 2, 4));
        scenarios.forEach((input, expected) -> Assertions.assertEquals(expected, treeProblems.naryPreorder(input)));
    }

    @Test
    void testNaryPostorder() {
        Map<NaryTreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(
                treeUtils.createTreeFromList(Arrays.asList(1, null, 3, 2, 4, null, 5, 6)),
                Arrays.asList(5, 6, 3, 2, 4, 1));
        scenarios.forEach((input, expected) -> Assertions.assertEquals(expected, treeProblems.naryPostorder(input)));
    }
}