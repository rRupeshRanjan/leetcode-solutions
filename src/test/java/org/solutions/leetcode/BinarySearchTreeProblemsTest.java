package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.dataStructures.TreeNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeProblemsTest {

    static BinarySearchTreeProblems bst;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        bst = new BinarySearchTreeProblems();
        testUtils = new TestUtils();
    }

    @Test
    void testTrimBst() {
        TreeNode input1 = new TreeNode(3);
        assertTrue(testUtils.areTreesEqualByValue(null, bst.trimBst(input1, 2, 2)));

        TreeNode input2 = new TreeNode(1);
        input2.setLeft(new TreeNode(0));
        input2.setRight(new TreeNode(2));
        TreeNode expected2 = new TreeNode(1);
        expected2.setRight(new TreeNode(2));
        assertTrue(testUtils.areTreesEqualByValue(expected2, bst.trimBst(input2, 1, 2)));

        TreeNode temp1 = new TreeNode(0);
        TreeNode temp2 = new TreeNode(2);
        temp2.setLeft(new TreeNode(1));
        temp1.setRight(temp2);
        TreeNode input3 = new TreeNode(3);
        input3.setLeft(temp1);
        input3.setRight(new TreeNode(4));
        TreeNode expected3 = new TreeNode(3);
        expected3.setLeft(temp2);
        assertTrue(testUtils.areTreesEqualByValue(expected3, bst.trimBst(input3, 1, 3)));

    }

    @Test
    void testConvertBST() {
        TreeNode root = new TreeNode(0);
        root.setRight(new TreeNode(1));

        TreeNode expected = new TreeNode(1);
        expected.setRight(new TreeNode(1));

        assertTrue(testUtils.areTreesEqualByValue(bst.convertBST(root), expected));

        root = new TreeNode(3);
        root.setRight(new TreeNode(4));
        TreeNode temp = new TreeNode(2);
        temp.setLeft(new TreeNode(1));
        root.setLeft(temp);

        expected = new TreeNode(7);
        expected.setRight(new TreeNode(4));
        temp = new TreeNode(9);
        temp.setLeft(new TreeNode(10));
        expected.setLeft(temp);

        assertTrue(testUtils.areTreesEqualByValue(bst.convertBST(root), expected));
    }

    @Test
    void testBstFromPreorder() {
        Map<int[], TreeNode> scenarios = new HashMap<>();
        scenarios.put(new int[]{8, 5, 1, 7, 10, 12},
                new TreeNode(8, new TreeNode(5, 1, 7), new TreeNode(10, null, 12)));
        scenarios.put(new int[]{1, 3}, new TreeNode(1, null, new TreeNode(3)));

        scenarios.forEach((input, expected) ->
                assertTrue(testUtils.areTreesEqualByValue(expected, bst.bstFromPreorder(input))));
    }

    @Test
    void testLowestCommonAncestor() {
        Map<Triple<TreeNode, TreeNode, TreeNode>, TreeNode> scenarios = new HashMap<>();

        TreeNode leftRight = new TreeNode(4);
        TreeNode left = new TreeNode(2, new TreeNode(0), leftRight);
        TreeNode right = new TreeNode(8, 7, 9);
        TreeNode root = new TreeNode(6, left, right);

        scenarios.put(Triple.of(root, left, right), root);
        scenarios.put(Triple.of(root, left, leftRight), left);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                bst.lowestCommonAncestor(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testIsValidBST() {
        Map<TreeNode, Boolean> scenarios = new HashMap<>();
        scenarios.put(new TreeNode(2, 1, 3), true);
        scenarios.put(new TreeNode(5, new TreeNode(1), new TreeNode(4, 3, 6)), false);
        scenarios.put(new TreeNode(3), true);

        scenarios.forEach((input, expected) -> assertEquals(expected, bst.isValidBST(input)));
    }

    @Test
    void testSortedListToBST() {
        Map<ListNode, TreeNode> scenarios = new HashMap<>();
        scenarios.put(testUtils.getLinkedList(Arrays.asList(-10, -3, 0, 5, 9)),
                new TreeNode(0, new TreeNode(-3, -10, null), new TreeNode(9, 5, null)));
        scenarios.put(null, null);
        scenarios.put(testUtils.getLinkedList(Collections.singletonList(0)), new TreeNode(0));
        scenarios.put(testUtils.getLinkedList(Arrays.asList(1, 3)), new TreeNode(3, 1, null));

        scenarios.forEach((input, expected) -> assertEquals(expected, bst.sortedListToBST(input)));
    }

    @Test
    void testSortedArrayToBST() {
        Map<int[], TreeNode> scenarios = new HashMap<>();
        scenarios.put(new int[]{-10, -3, 0, 5, 9},
                new TreeNode(0, new TreeNode(-10, null, -3), new TreeNode(5, null, 9)));
        scenarios.put(null, null);
        scenarios.put(new int[]{0}, new TreeNode(0));
        scenarios.put(new int[]{1, 3}, new TreeNode(1, null, 3));

        scenarios.forEach((input, expected) -> assertEquals(expected, bst.sortedArrayToBST(input)));
    }

    @Test
    void testGetAllElements() {
        Map<Pair<TreeNode, TreeNode>, List<Integer>> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new TreeNode(2, 1, 4), new TreeNode(1, 0, 3)),
                Arrays.asList(0, 1, 1, 2, 3, 4));
        scenarios.put(Pair.of(new TreeNode(1, null, 8), new TreeNode(8, 1, null)),
                Arrays.asList(1, 1, 8, 8));

        scenarios.forEach((input, expected) -> assertEquals(expected, bst.getAllElements(input.getLeft(), input.getRight())));
    }

    @Test
    void testInsertIntoBST() {
        Map<Pair<TreeNode, Integer>, TreeNode> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new TreeNode(4, new TreeNode(2, 1, 3), 7), 5),
                new TreeNode(4, new TreeNode(2, 1, 3), new TreeNode(7, 5, null)));
        scenarios.put(Pair.of(new TreeNode(40, new TreeNode(20, 10, 30), new TreeNode(60, 50, 70)), 25),
                new TreeNode(40, new TreeNode(20, 10, new TreeNode(30, 25, null)), new TreeNode(60, 50, 70)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, bst.insertIntoBST(input.getLeft(), input.getRight())));
    }
}
