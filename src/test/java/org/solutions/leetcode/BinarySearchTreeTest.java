package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    static BinarySearchTree bst;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        bst = new BinarySearchTree();
        testUtils = new TestUtils();
    }

    @Test
    void testTrimBst() {
        TreeNode input1 = new TreeNode(3);
        assertTrue(testUtils.compareTrees(null, bst.trimBst(input1, 2, 2)));

        TreeNode input2 = new TreeNode(1);
        input2.setLeft(new TreeNode(0));
        input2.setRight(new TreeNode(2));
        TreeNode expected2 = new TreeNode(1);
        expected2.setRight(new TreeNode(2));
        assertTrue(testUtils.compareTrees(expected2, bst.trimBst(input2, 1, 2)));

        TreeNode temp1 = new TreeNode(0);
        TreeNode temp2 = new TreeNode(2);
        temp2.setLeft(new TreeNode(1));
        temp1.setRight(temp2);
        TreeNode input3 = new TreeNode(3);
        input3.setLeft(temp1);
        input3.setRight(new TreeNode(4));
        TreeNode expected3 = new TreeNode(3);
        expected3.setLeft(temp2);
        assertTrue(testUtils.compareTrees(expected3, bst.trimBst(input3, 1, 3)));

    }


}