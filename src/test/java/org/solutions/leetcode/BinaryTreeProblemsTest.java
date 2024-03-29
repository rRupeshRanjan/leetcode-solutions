package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.TreeNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeProblemsTest {

    private static BinaryTreeProblems binaryTreeProblems;
    private static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        binaryTreeProblems = new BinaryTreeProblems();
        testUtils = new TestUtils();
    }

    @Test
    void testRightSideView() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(2, 7, 4), new TreeNode(3, 6, 5)),
                List.of(1, 3, 5));
        scenarios.put(new TreeNode(1, new TreeNode(2, null, 4), new TreeNode(3)),
                List.of(1, 3, 4));
        scenarios.put(new TreeNode(1, new TreeNode(2, null, 4), null), List.of(1, 2, 4));
        scenarios.put(new TreeNode(1, 2, null), List.of(1, 2));
        scenarios.put(new TreeNode(1), List.of(1));
        scenarios.put(null, List.of());

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.rightSideView(input)));
    }

    @Test
    void testInorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(null, List.of());
        scenarios.put(new TreeNode(1, 2, 3), List.of(2, 1, 3));
        scenarios.put(new TreeNode(1, null, 3), List.of(1, 3));
        scenarios.put(new TreeNode(1, 2, null), List.of(2, 1));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.inorderTraversal(input)));
    }

    @Test
    void testPreorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(null, List.of());
        scenarios.put(new TreeNode(1, 2, 3), List.of(1, 2, 3));
        scenarios.put(new TreeNode(1, null, 3), List.of(1, 3));
        scenarios.put(new TreeNode(1, 2, null), List.of(1, 2));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.preorderTraversal(input)));
    }

    @Test
    void testPostorderTraversal() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1, 2, 3), List.of(2, 3, 1));
        scenarios.put(new TreeNode(1, null, 3), List.of(3, 1));
        scenarios.put(new TreeNode(1, 2, null), List.of(2, 1));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.postorderTraversal(input)));
    }

    @Test
    void testFlipMatchVoyage() {
        Map<Pair<TreeNode, int[]>, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new TreeNode(1, 2, null), new int[]{2, 1}), List.of(-1));
        scenarios.put(Pair.of(new TreeNode(1, 2, 3), new int[]{1, 3, 2}), List.of(1));
        scenarios.put(Pair.of(new TreeNode(1, 2, 3), new int[]{1, 2, 3}), Collections.emptyList());

        scenarios.forEach((input, expected) ->
                assertEquals(expected, binaryTreeProblems.flipMatchVoyage(input.getLeft(), input.getRight())));
    }

    @Test
    void testAverageOfLevels() {
        Map<TreeNode, List<Double>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)),
                List.of(3d, 14.5, 11d));
        scenarios.put(new TreeNode(3, new TreeNode(9, 15, 7), 20),
                List.of(3d, 14.5, 11d));
        scenarios.put(null, Collections.emptyList());
        scenarios.put(new TreeNode(1), List.of(1d));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.averageOfLevels(input)));
    }

    @Test
    void testDeepestLeavesSum() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(2, 4, null), new TreeNode(3, null, 6)), 10);
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, null), null), 4);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.deepestLeavesSum(input)));
    }

    @Test
    void testFlatten() {
        TreeNode input = new TreeNode(1, new TreeNode(2, 3, 4), new TreeNode(5, null, 6));
        TreeNode expected = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null,
                new TreeNode(4, null, new TreeNode(5, null, 6)))));

        binaryTreeProblems.flatten(input);
        assertEquals(expected, input);
    }

    @Test
    void testMinCameraCover() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(0, new TreeNode(0, 0, 0), null), 1);
        scenarios.put(new TreeNode(0, new TreeNode(0, new TreeNode(0, null, 0), null), null), 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.minCameraCover(input)));
    }

    @Test
    void testMaxLevelSum() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(7, 7, -8), 0), 2);
        scenarios.put(new TreeNode(1, new TreeNode(7, 7, 8), 0), 3);
        scenarios.put(new TreeNode(989, null,
                new TreeNode(10250, 98693, new TreeNode(-89388, null, -32127))), 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.maxLevelSum(input)));
    }

    @Test
    void testBuildTreeFromPreorderInorder() {
        Map<Pair<int[], int[]>, TreeNode> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}),
                new TreeNode(3, 9, new TreeNode(20, 15, 7)));
        scenarios.put(Pair.of(new int[]{-1}, new int[]{-1}), new TreeNode(-1));

        scenarios.forEach((input, expected) -> assertTrue(testUtils.areTreesEqualByValue(expected,
                binaryTreeProblems.buildTreeFromPreorderInorder(input.getLeft(), input.getRight()))));
    }

    @Test
    void testFindLeaves() {
        Map<TreeNode, List<List<Integer>>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 5), 3),
                List.of(List.of(4, 5, 3), List.of(2), List.of(1)));
        scenarios.put(new TreeNode(1), List.of(List.of(1)));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.findLeaves(input)));
    }

    @Test
    void testFindDuplicateSubtrees() {
        Map<TreeNode, List<TreeNode>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(2, 1, 1), List.of(new TreeNode((1))));
        scenarios.put(new TreeNode(2, new TreeNode(2, 3, null), new TreeNode(2, 3, null)),
                List.of(new TreeNode(3), new TreeNode(2, 3, null)));
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, null),
                        new TreeNode(3, new TreeNode(2, 4, null), 4)),
                List.of(new TreeNode(4), new TreeNode(2, 4, null)));

        scenarios.forEach((input, expected) -> {
            List<TreeNode> duplicateSubtrees = binaryTreeProblems.findDuplicateSubtrees(input);
            for (int i = 0; i < duplicateSubtrees.size(); i++) {
                assertTrue(testUtils.areTreesEqualByValue(duplicateSubtrees.get(i), expected.get(i)));
            }
        });
    }

    @Test
    void testDelNodes() {
        Map<Pair<TreeNode, int[]>, List<TreeNode>> scenarios = new HashMap<>();

        scenarios.put(
                Pair.of(
                        new TreeNode(1, new TreeNode(2, 4, 5), new TreeNode(3, 6, 7)),
                        new int[]{3, 5}),
                List.of(
                        new TreeNode(1, new TreeNode(2, 4, null), null),
                        new TreeNode(6), new TreeNode(7))
        );
        scenarios.put(
                Pair.of(
                        new TreeNode(1, new TreeNode(2, 4, 5), new TreeNode(3, 6, 7)),
                        new int[]{1, 3, 5}),
                List.of(new TreeNode(6), new TreeNode(7), new TreeNode(2, 4, null))
        );
        scenarios.put(
                Pair.of(
                        new TreeNode(1, new TreeNode(2, null, 3), 4),
                        new int[]{3}),
                List.of(new TreeNode(1, 2, 4))
        );


        scenarios.forEach((input, expected) -> {
            List<TreeNode> actual = binaryTreeProblems.delNodes(input.getLeft(), input.getRight());
            assertTrue(actual.size() == expected.size() && expected.containsAll(actual));
        });
    }

    @Test
    void testDiameterOfBinaryTree() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 5), 3), 3);
        scenarios.put(new TreeNode(1, 2, null), 1);
        scenarios.put(null, 0);
        scenarios.put(new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, new TreeNode(4, 1, null), null),
                        new TreeNode(2, null, new TreeNode(1, null, 9))),
                3), 6);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.diameterOfBinaryTree(input)));
    }

    @Test
    void testLargestValues() {
        Map<TreeNode, List<Integer>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, new TreeNode(3, 5, 3), new TreeNode(2, null, 9)),
                List.of(1, 3, 9));
        scenarios.put(new TreeNode(1, 2, 3), List.of(1, 3));
        scenarios.put(new TreeNode(1), List.of(1));
        scenarios.put(new TreeNode(1, null, 2), List.of(1, 2));
        scenarios.put(null, Collections.emptyList());

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.largestValues(input)));
    }

    @Test
    void testHouseRobberIII() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3,
                new TreeNode(2, null, 3),
                new TreeNode(3, null, 1)), 7);
        scenarios.put(new TreeNode(3,
                new TreeNode(4, 1, 3),
                new TreeNode(5, null, 1)), 9);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.houseRobberIII(input)));
    }

    @Test
    void testLowestCommonAncestor() {
        Map<Triple<TreeNode, TreeNode, TreeNode>, TreeNode> scenarios = new HashMap<>();

        TreeNode leftRight = new TreeNode(4);
        TreeNode left = new TreeNode(5, 6, leftRight);
        TreeNode right = new TreeNode(1, 0, 8);
        TreeNode root = new TreeNode(3, left, right);

        scenarios.put(Triple.of(root, left, right), root);
        scenarios.put(Triple.of(root, left, leftRight), left);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                binaryTreeProblems.lowestCommonAncestor(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testLowestCommonAncestorII() {
        Map<Triple<TreeNode, TreeNode, TreeNode>, TreeNode> scenarios = new HashMap<>();

        TreeNode leftRight = new TreeNode(4);
        TreeNode left = new TreeNode(5, 6, leftRight);
        TreeNode right = new TreeNode(1, 0, 8);
        TreeNode root = new TreeNode(3, left, right);

        scenarios.put(Triple.of(root, left, right), root);
        scenarios.put(Triple.of(root, left, leftRight), left);
        scenarios.put(Triple.of(root, left, new TreeNode(11)), null);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                binaryTreeProblems.lowestCommonAncestorII(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testLowestCommonAncestorIV() {
        Map<Pair<TreeNode, TreeNode[]>, TreeNode> scenarios = new HashMap<>();

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node1.setLeft(node2);
        node1.setRight(node3);

        scenarios.put(Pair.of(node1, new TreeNode[]{node4, node5}), node2);
        scenarios.put(Pair.of(node1, new TreeNode[]{node4, node5, node6}), node1);
        scenarios.put(Pair.of(node1, new TreeNode[]{node4, node5, node6, node7}), node1);
        scenarios.put(Pair.of(node1, new TreeNode[]{node1, node2}), node1);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                binaryTreeProblems.lowestCommonAncestorIV(input.getLeft(), input.getRight())));
    }

    @Test
    void testLongestConsecutiveSequence() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1, null,
                new TreeNode(3, 2, new TreeNode(4, null, 5))), 3);
        scenarios.put(new TreeNode(2, null,
                new TreeNode(3, new TreeNode(2, 1, null), null)), 2);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, binaryTreeProblems.longestConsecutiveSequence(input)));
    }

    @Test
    void testLevelOrder() {
        Map<TreeNode, List<List<Integer>>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)),
                List.of(List.of(3), List.of(9, 20), List.of(15, 7)));
        scenarios.put(new TreeNode(1), List.of(List.of(1)));
        scenarios.put(null, List.of());

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.levelOrder(input)));
    }

    @Test
    void testZigzagLevelOrder() {
        Map<TreeNode, List<List<Integer>>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)),
                List.of(List.of(3), List.of(20, 9), List.of(15, 7)));
        scenarios.put(new TreeNode(1), List.of(List.of(1)));
        scenarios.put(null, List.of());

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.zigzagLevelOrder(input)));
    }

    @Test
    void testGoodNodes() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3,
                new TreeNode(1, 3, null),
                new TreeNode(4, 1, 5)), 4);
        scenarios.put(new TreeNode(3, new TreeNode(3, 4, 2), null), 3);
        scenarios.put(new TreeNode(3), 1);
        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.goodNodes(input)));
    }

    @Test
    void testMaxProduct() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(1,
                new TreeNode(2, 4, 5), new TreeNode(3, 6, null)), 110);
        scenarios.put(new TreeNode(1, null, new TreeNode(2, 3, new TreeNode(4, 5, 6))), 90);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.maxProduct(input)));
    }

    @Test
    void testVerticalOrder() {
        Map<TreeNode, List<List<Integer>>> scenarios = new HashMap<>();

        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)),
                List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7)));
        scenarios.put(new TreeNode(3, new TreeNode(9, 4, 0), new TreeNode(8, 1, 7)),
                List.of(List.of(4), List.of(9), List.of(3, 0, 1), List.of(8), List.of(7)));
        scenarios.put(new TreeNode(3, new TreeNode(9, 4, new TreeNode(0, null, 2)),
                        new TreeNode(8, new TreeNode(1, 5, null), 7)),
                List.of(List.of(4), List.of(9, 5), List.of(3, 0, 1), List.of(8, 2), List.of(7)));
        scenarios.put(null, List.of());
        scenarios.put(new TreeNode(1), List.of(List.of(1)));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.verticalOrder(input)));
    }

    @Test
    void testPathSumIII() {
        Map<Pair<TreeNode, Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new TreeNode(10,
                new TreeNode(5, new TreeNode(3, 3, -2), new TreeNode(2, null, 1)),
                new TreeNode(-3, null, 11)), 8), 3);
        scenarios.put(Pair.of(new TreeNode(5,
                new TreeNode(4, new TreeNode(11, 7, 2), null),
                new TreeNode(8, 13, new TreeNode(4, 5, 1))), 22), 3);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, binaryTreeProblems.pathSumIII(input.getLeft(), input.getRight())));
    }

    @Test
    void testIsCousins() {
        Map<Triple<TreeNode, Integer, Integer>, Boolean> scenarios = new HashMap<>();
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, null), 3), 3, 4), false);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, null), 3), 4, 3), false);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, null), 3), 2, 3), false);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, null), 3), 1, 3), false);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, 6),
                new TreeNode(3, 5, 7)), 4, 5), true);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, 5),
                new TreeNode(3, 6, 7)), 4, 6), true);
        scenarios.put(Triple.of(new TreeNode(1, new TreeNode(2, 4, 5),
                new TreeNode(3, 6, 7)), 4, 5), false);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                binaryTreeProblems.isCousins(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testCountNodes() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 5), new TreeNode(3, 6, null)), 6);
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 5), new TreeNode(3, 6, 7)), 7);
        scenarios.put(null, 0);
        scenarios.put(new TreeNode(1), 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.countNodes(input)));
    }

    @Test
    void testInvertTree() {
        Map<TreeNode, TreeNode> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(2, new TreeNode(2, 1, 3), new TreeNode(7, 6, 9)),
                new TreeNode(2, new TreeNode(7, 9, 6), new TreeNode(2, 3, 1)));
        scenarios.put(new TreeNode(2, 3, 1), new TreeNode(2, 1, 3));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.invertTree(input)));
    }

    @Test
    void testSumNumbers() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(1, 2, 3), 25);
        scenarios.put(new TreeNode(4, new TreeNode(9, 5, 1), 0), 1026);
        scenarios.put(new TreeNode(1), 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.sumNumbers(input)));
    }

    @Test
    void testSumOfLeftLeaves() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)), 24);
        scenarios.put(new TreeNode(1), 0);
        scenarios.put(new TreeNode(1, null, 2), 0);
        scenarios.put(new TreeNode(1, new TreeNode(2, null, 3), 4), 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.sumOfLeftLeaves(input)));
    }

    @Test
    void testCheckEqualTree() {
        Map<TreeNode, Boolean> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(5, 10, new TreeNode(10, 2, 3)), true);
        scenarios.put(new TreeNode(1, 2, new TreeNode(10, 2, 20)), false);
        scenarios.put(new TreeNode(0, -1, 1), false);
        scenarios.put(new TreeNode(1, 1, null), true);
        scenarios.put(new TreeNode(0, null, 0), true);
        scenarios.put((new TreeNode(0, new TreeNode(0, null, 0), new TreeNode(0, 0, 0))), true);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.checkEqualTree(input)));
    }

    @Test
    void testVerticalTraversal() {
        Map<TreeNode, List<List<Integer>>> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(3, 9, new TreeNode(20, 15, 7)),
                List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7)));
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 5), new TreeNode(3, 6, 7)),
                List.of(List.of(4), List.of(2), List.of(1, 5, 6), List.of(3), List.of(7)));
        scenarios.put(new TreeNode(1, new TreeNode(2, 4, 6), new TreeNode(3, 5, 7)),
                List.of(List.of(4), List.of(2), List.of(1, 5, 6), List.of(3), List.of(7)));
        scenarios.put(new TreeNode(0, new TreeNode(5, 7, null),
                        new TreeNode(1, null, new TreeNode(2, 3, new TreeNode(4, 6, null)))),
                List.of(List.of(7), List.of(5), List.of(0), List.of(1, 3), List.of(2, 6), List.of(4)));

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.verticalTraversal(input)));
    }

    @Test
    void testFindFrequentTreeSum() {
        Map<TreeNode, int[]> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(5, 2, -3), new int[]{2, -3, 4});
        scenarios.put(new TreeNode(5, 2, -5), new int[]{2});
        scenarios.put(new TreeNode(5, -5, -5), new int[]{-5});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, binaryTreeProblems.findFrequentTreeSum(input)));
    }

    @Test
    void testSumRootToLeaf() {
        Map<TreeNode, Integer> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(1, new TreeNode(0, 0, 1), new TreeNode(1, 0, 1)), 22);
        scenarios.put(new TreeNode(1, new TreeNode(1, 0, 1), new TreeNode(1, 0, 1)), 26);
        scenarios.put(new TreeNode(1, new TreeNode(0, 0, null), new TreeNode(1, 0, 1)), 17);
        scenarios.put(new TreeNode(0), 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, binaryTreeProblems.sumRootToLeaf(input)));
    }
}
