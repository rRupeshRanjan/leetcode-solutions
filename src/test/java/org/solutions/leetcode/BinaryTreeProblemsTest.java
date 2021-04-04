package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.TreeNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeProblemsTest {

    static BinaryTreeProblems binaryTreeProblems;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        binaryTreeProblems = new BinaryTreeProblems();
        testUtils = new TestUtils();
    }

    @Test
    void testRightSideView() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(1, new TreeNode(2, 7, 4), new TreeNode(3, 6, 5)), Arrays.asList(1, 3, 5));
        scenarios.put(new TreeNode(1, new TreeNode(2, null, 4), new TreeNode(3)), Arrays.asList(1, 3, 4));
        scenarios.put(new TreeNode(1, new TreeNode(2, null, 4), null), Arrays.asList(1, 2, 4));
        scenarios.put(new TreeNode(1, new TreeNode(2), null), Arrays.asList(1, 2));
        scenarios.put(new TreeNode(1), Collections.singletonList(1));
        scenarios.put(null, Collections.emptyList());

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.rightSideView(input)));
    }

    @Test
    void testInorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1, 2, 3), Arrays.asList(2, 1, 3));
        scenarios.put(new TreeNode(1, null, 3), Arrays.asList(1, 3));
        scenarios.put(new TreeNode(1, 2, null), Arrays.asList(2, 1));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.inorderTraversal(input)));
    }

    @Test
    void testPreorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1, 2, 3), Arrays.asList(1, 2, 3));
        scenarios.put(new TreeNode(1, null, 3), Arrays.asList(1, 3));
        scenarios.put(new TreeNode(1, 2, null), Arrays.asList(1, 2));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.preorderTraversal(input)));
    }

    @Test
    void testPostorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1, 2, 3), Arrays.asList(2, 3, 1));
        scenarios.put(new TreeNode(1, null, 3), Arrays.asList(3, 1));
        scenarios.put(new TreeNode(1, 2, null), Arrays.asList(2, 1));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.postorderTraversal(input)));
    }

    @Test
    void testFlipMatchVoyage() {
        Map<Pair<TreeNode, int[]>, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new TreeNode(1, 2, null), new int[]{2, 1}), Collections.singletonList(-1));
        scenarios.put(Pair.of(new TreeNode(1, 2, 3), new int[]{1, 3, 2}), Collections.singletonList(1));
        scenarios.put(Pair.of(new TreeNode(1, 2, 3), new int[]{1, 2, 3}), Collections.emptyList());

        scenarios.forEach((input, expected) ->
                assertEquals(expected, binaryTreeProblems.flipMatchVoyage(input.getLeft(), input.getRight())));
    }

    @Test
    void testAverageOfLevels() {
        Map<TreeNode, List<Double>> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(3, new TreeNode(9), new TreeNode(20, 15, 7)), Arrays.asList(3d, 14.5, 11d));
        scenarios.put(new TreeNode(3, new TreeNode(9, 15, 7), new TreeNode(20)), Arrays.asList(3d, 14.5, 11d));
        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1), Collections.singletonList(1d));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.averageOfLevels(input)));
    }
}
