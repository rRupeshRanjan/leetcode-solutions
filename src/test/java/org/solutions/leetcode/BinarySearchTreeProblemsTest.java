package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.TreeNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.HashMap;
import java.util.Map;

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
}